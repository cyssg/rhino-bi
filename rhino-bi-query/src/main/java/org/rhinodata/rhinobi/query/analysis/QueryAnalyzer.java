package org.rhinodata.rhinobi.query.analysis;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rhinodata.rhinobi.metadata.domain.Column;
import org.rhinodata.rhinobi.metadata.domain.Dataset;
import org.rhinodata.rhinobi.metadata.domain.Dimension;
import org.rhinodata.rhinobi.metadata.domain.Metric;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.QueryException;
import org.rhinodata.rhinobi.query.dsl.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenye
 * @date 2023-02-22
 */
public record QueryAnalyzer(QueryContext queryContext) {

    public Statement analyze(Query query) {
        Dql dql = query.getDql();
        return dql.accept(new QueryStatementVisitor(queryContext),  null);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class QueryAnalyzerHelper{
        private  Dataset dataset;
    }






    class QueryStatementVisitor extends DqlVisitor<Statement, QueryAnalyzerHelper> {
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

        @Override
        public Statement visitSingleDql(SingleDql singleDql, QueryAnalyzerHelper parent) {

            Dataset dataset = queryContext.getQueryBeans().datasetService().getDataset(singleDql.getDataset().getUuid());
            QueryAnalyzerHelper self = new QueryAnalyzerHelper(dataset);
            Assert.notNull(dataset, "数据集不存在 ： {}", singleDql.getDataset().getUuid());
            ScanStatement scanStatement = new ScanStatement(singleDql.getDataset().getUuid(), dataset.getName(), dataset.getCode(), dataset.getDatasourceName());

            ProjectStatement projectStatement = new ProjectStatement();

            singleDql.getDimension().forEach(item -> {
                Dimension dimension = null;
                if (StrUtil.isNotBlank(item.getName())){
                    dimension = dataset.getDimension(item.getName());
                }else{
                    dimension = queryContext.getQueryBeans().datasetService().getDimension(item.getUuid());
                }
                Assert.notNull(dimension,"不存在的维度 - {}",item);
                projectStatement.addItem(new ProjectStatement.Item(
                        dimension.getUuid(),
                        dimension.getName(),
                        dimension.getCode(),
                        StrUtil.isBlank(item.getAlias())?dimension.getName():item.getAlias(),
                        dimension.getDataType(),
                        true)
                );
            });

            singleDql.getMetric().forEach(item->{
                Metric metric = null;
                if(StrUtil.isNotBlank(item.getName())){
                    metric = dataset.getMetric(item.getName());
                }else{
                    metric = queryContext.getQueryBeans().datasetService().getMetric(item.getUuid());
                }
                Assert.notNull(metric,"不存在的指标 - {}",item);
                projectStatement.addItem(new ProjectStatement.Item(
                        metric.getUuid(),
                        metric.getName(),
                        metric.getCode(),
                        StrUtil.isBlank(item.getAlias())?metric.getName():item.getAlias(),
                        metric.getDataType(),
                        false)
                );
            });


            FilterStatement filterStatement = (FilterStatement) singleDql.getFilter().accept(this, self);

            QueryStatement queryStatement = new QueryStatement(
                    projectStatement,
                    scanStatement,
                    filterStatement,
                    null
            );
            return queryStatement;
        }

        @Override
        protected Statement visitFilterSpec(FilterSpec filterSpec, QueryAnalyzerHelper helper) {
            return new FilterStatement(buildPredicate(filterSpec.getPredicate(),helper));
        }

        private PredicateStatement buildPredicate(FilterSpec.FilterPredicate filterPredicate,QueryAnalyzerHelper helper){
            if (filterPredicate instanceof FilterSpec.FilterCondition){
                FilterSpec.FilterCondition condition =  (FilterSpec.FilterCondition)filterPredicate;
                SymbolReference symbolReference = buildSymbolReference(helper.getDataset(), condition.getItem());
                ConditionStatement conditionStatement = new ConditionStatement(
                        symbolReference,
                        condition.getOp(),
                        condition.getValue()
                );
                return conditionStatement;
            }else if(filterPredicate instanceof  FilterSpec.FilterRelational){
                FilterSpec.FilterRelational relational = (FilterSpec.FilterRelational)filterPredicate;
                List<FilterSpec.FilterPredicate> predicates = relational.getPredicates();
                List<PredicateStatement> statementList = predicates.stream().map(p -> buildPredicate(p, helper)).collect(Collectors.toList());
                RelationalStatement relationalStatement = new RelationalStatement(relational.getRel(),statementList);
               return relationalStatement;
            }else{
                throw new QueryException(StrUtil.format("filterPredicate 无法解析 - {}",filterPredicate));
            }
        }

        private SymbolReference buildSymbolReference(Dataset dataset, ReferenceSpec reference){
            Column column = null;
            if (StrUtil.isNotBlank(reference.getUuid())){
                 column = this.queryContext.getQueryBeans().datasetService().getColumn(reference.getUuid());

            }else if (StrUtil.isNotBlank(reference.getName())){
                 column = dataset.getColumn(reference.getName());
            }
            Assert.notNull(column,"不存在的column dataset - {} , reference - {}",dataset.getName(),reference);
            return new SymbolReference(
                    column.getUuid(),
                    column.getName(),
                    StrUtil.isNotBlank(reference.getExpr())?reference.getExpr():column.getCode(),
                    StrUtil.isNotBlank(reference.getAlias())?reference.getAlias() : column.getName(),
                    column.getDataType()
            );
        }
    }
}