package org.rhinodata.rhinobi.query.plan;

import lombok.Getter;
import org.rhinodata.rhinobi.metadata.domain.Column;
import org.rhinodata.rhinobi.metadata.domain.Dataset;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.analysis.*;

import java.util.*;

/**
 * @author chenye
 * @date 2023-02-17
 */
public record QueryPlanner(QueryContext queryContext) {

    public PlanNode plan(Statement statement) {
        StatementPlannerVisitor visitor = new StatementPlannerVisitor(queryContext);
        return statement.accept(visitor, null);
    }

    class StatementPlanContext{
        @Getter
        Set<String> datasourceNameSet = new HashSet<>();
    }

    class StatementPlannerVisitor extends StatementVisitor<PlanNode, StatementPlanContext> {
        QueryContext queryContext;
        StatementToSql statementToSql;
        StatementPlannerVisitor(QueryContext queryContext){
            this.queryContext = queryContext;
            statementToSql = new StatementToSql(queryContext);
        }


        @Override
        public PlanNode visitProjectStatement(ProjectStatement projectStatement,StatementPlanContext context){

            boolean useSqlPlanNode = true;
            String datasourceName = null;
            List<PlanNode> childrenPlanNode = new ArrayList<>();
            for(Statement statement : projectStatement.children()){
                PlanNode planNode = statement.accept(this,null);
                childrenPlanNode.add(planNode);
                if (planNode instanceof  ReCalculateNode){
                    useSqlPlanNode = false;
                }else if (planNode instanceof  SqlPlanNode){
                    datasourceName = planNode.unwrap(SqlPlanNode.class).getDatasourceName();
                }
            }
            Projects projects = new Projects();
            projectStatement.getItems().forEach(item -> {
                projects.addProject(new Projects.Project(item.expr(),item.alias()));
            });
            String sql = new StatementToSql(queryContext).toSql(projectStatement);

            if (useSqlPlanNode){
                SqlPlanNode sqlPlanNode = new SqlPlanNode(projects,sql,datasourceName);
            }else{
//                ReCalculateNode reCalculateNode = new ReCalculateNode();
            }
            return null;
        }


        public PlanNode visitJoinStatement(JoinStatement joinStatement,StatementPlanContext context){

            boolean useSqlPlanNode = true;
            List<PlanNode> childrenPlanNode = new ArrayList<>();
            for(Statement statement : joinStatement.children()){
                PlanNode planNode  = statement.accept(this,context);
                if (planNode instanceof ReCalculateNode){
                    useSqlPlanNode = false;
                }
            }
//            if (useSqlPlanNode){
//                String sql = statementToSql.toSql(joinStatement);
//                String datasourceName = context.getDatasourceNameSet().stream().findFirst().orElse("");
//                SqlPlanNode sqlPlanNode = new SqlPlanNode(null,sql,datasourceName);
//            }
//            JoinPlanNode joinPlanNode = new JoinPlanNode()

            return null;
        }

        @Override
        public PlanNode visitScanStatement(ScanStatement scanStatement, StatementPlanContext context) {
            String datasetUuid = scanStatement.getDatasetUuid();

            String datasourceName = scanStatement.getDatasourceName();
            String sql = new StatementToSql(queryContext).toSql(scanStatement);
            Dataset dataset = queryContext.getQueryBeans().datasetService().getDataset(datasetUuid);
            List<Column> columnList = dataset.getColumns();
            Projects projects = new Projects();
            columnList.forEach(column -> {
                projects.addProject(new Projects.Project(column.getCode(),column.getName()));
            });
            SqlPlanNode sqlPlanNode = new SqlPlanNode(projects,datasourceName,sql);
            context.datasourceNameSet.add(datasourceName);
            return sqlPlanNode;
        }

    }
}
