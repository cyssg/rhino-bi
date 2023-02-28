package org.rhinodata.rhinobi.query.plan;

import lombok.Data;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.analysis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-17
 */
public record QueryPlanner(QueryContext queryContext) {

    public PlanNode plan(Statement statement) {
        StatementPlannerVisitor visitor = new StatementPlannerVisitor(queryContext);
        return statement.accept(visitor, null);
    }

    @Data
    class StatementPlanHelper{
        String datasourceName;
        Projects projects;
        boolean needRecalculate;
    }

    class StatementPlannerVisitor extends StatementVisitor<PlanNode, StatementPlanHelper> {
        QueryContext queryContext;
        StatementToSql statementToSql;
        StatementPlannerVisitor(QueryContext queryContext){
            this.queryContext = queryContext;
            statementToSql = new StatementToSql(queryContext);
        }


        public PlanNode visitQueryStatement(QueryStatement queryStatement,StatementPlanHelper parent){
            PlanNode planNode = null;
            StatementPlanHelper helper = new StatementPlanHelper();
            queryStatement.children().forEach(statement -> {
                statement.accept(this,helper);
            });
            if (helper.isNeedRecalculate()){
                parent.setNeedRecalculate(true);
                planNode = new RecalculatePlanNode(helper.projects);
            }else{
                planNode = new SqlPlanNode(helper.projects,statementToSql.toSql(queryStatement), helper.datasourceName);
            }
            return planNode;

        }

        @Override
        public PlanNode visitProjectStatement(ProjectStatement projectStatement,StatementPlanHelper parent){
            Projects projects = new Projects();
            projectStatement.getItems().forEach(item -> {
                projects.addProject(new Projects.Project(item.expr(),item.alias()));
            });
            parent.setProjects(projects);
            return null;
        }

        @Override
        public PlanNode visitScanStatement(ScanStatement scanStatement, StatementPlanHelper parent) {
            String datasourceName = scanStatement.getDatasourceName();
            parent.setDatasourceName(datasourceName);
            parent.setNeedRecalculate(false);
            return null;
        }


        public PlanNode visitJoinStatement(JoinStatement joinStatement,StatementPlanHelper context){

            boolean useSqlPlanNode = true;
            List<PlanNode> childrenPlanNode = new ArrayList<>();
            for(Statement statement : joinStatement.children()){
                PlanNode planNode  = statement.accept(this,context);
                if (planNode instanceof RecalculatePlanNode){
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


    }
}
