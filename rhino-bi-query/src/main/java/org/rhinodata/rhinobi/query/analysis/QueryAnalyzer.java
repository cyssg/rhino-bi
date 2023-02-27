package org.rhinodata.rhinobi.query.analysis;

import cn.hutool.core.lang.Assert;
import lombok.Getter;
import org.rhinodata.rhinobi.metadata.domain.Dataset;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.dsl.*;

import java.util.List;
import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-22
 */
public record QueryAnalyzer(QueryContext queryContext) {

    public Statement analyze(Query query) {
        Dql dql = query.getDql();
        return dql.accept(new QueryStatementVisitor(queryContext),  null);
    }

    @Getter
    class QueryAnalyzerHelper{
        private  ProjectStatement projectStatement;
        private  Statement fromStatement;
        private  WhereStatement whereStatement;
        private  OrderByStatement orderByStatement;

        public void setStatement(Statement statement){
            if (statement instanceof ScanStatement){
                this.fromStatement = statement;
            }
            if (statement instanceof ProjectStatement){
                if(Objects.isNull(this.projectStatement)){
                    this.projectStatement = statement.unwrap(ProjectStatement.class);
                }else{
                    this.projectStatement.merge(statement.unwrap(ProjectStatement.class));
                }
            }
        }
    }






    class QueryStatementVisitor extends DqlVisitor<Statement, Void> {
        private final QueryContext queryContext;
        QueryStatementVisitor(QueryContext queryContext) {
            this.queryContext = queryContext;
        }

        public Statement visitRatioDql(RatioDql ratioDql, Void parent) {
            SingleDql source = ratioDql.getSingleDql();
            List<RatioDql.Ratio> targets = ratioDql.getTargets();
            Statement sourceStatement = source.accept(this,null);

//            JoinStatement joinStatement = new JoinStatement(
//                    ratioDql.getDql().accept(this, null),
//                    ratioDql.getDql().accept(this, null),
//                    JoinStatement.JoinType.FULL_OUTER
//            );
//            return joinStatement;
            return null;

        }

        public Statement visitSingleDql(SingleDql singleDql, Void c) {
            QueryAnalyzerHelper helper = new QueryAnalyzerHelper();
            singleDql.children().forEach(dql -> {
                helper.setStatement(dql.accept(this,null));
           });
           QueryStatement queryStatement = new QueryStatement(
                   helper.getProjectStatement(),
                   helper.getFromStatement(),
                   helper.getWhereStatement(),
                   helper.getOrderByStatement()
           );
            return queryStatement;
        }

        public Statement visitDatasetSpec(DatasetSpec datasetSpec, Void c) {
            Dataset dataset = queryContext.getQueryBeans().datasetService().getDataset(datasetSpec.getDatasetUuid());
            Assert.notNull(dataset, "数据集不存在 ： {}", datasetSpec.getDatasetUuid());
            ScanStatement scanStatement = new ScanStatement(datasetSpec.getDatasetUuid(), dataset.getName(), dataset.getCode(), dataset.getDatasourceName());
            return scanStatement;
        }

        public Statement visitDimensionSpec(DimensionSpec dimensionSpec, Void c) {
            ProjectStatement projectStatement = new ProjectStatement();
            dimensionSpec.forEach(expression -> {
                projectStatement.addItem(new ProjectStatement.Item(expression.getExpr(),expression.getAlias(),true));
            });
            return projectStatement;
        }

        public Statement visitMetricSpec(MetricSpec metricSpec, Void c) {
            ProjectStatement projectStatement = new ProjectStatement();
            metricSpec.forEach(expression -> {
                projectStatement.addItem(new ProjectStatement.Item(expression.getExpr(),expression.getAlias(),false));
            });
            return projectStatement;
        }
    }
}
