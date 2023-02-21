package org.rhinodata.rhinobi.query.plan;

import org.rhinodata.rhinobi.dataset.domain.Dataset;
import org.rhinodata.rhinobi.query.QueryBeans;
import org.rhinodata.rhinobi.query.dsl.*;

import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-04
 */
public record QueryAnalyzer(QueryBeans queryBeans) {

    public PlanNode analyze(Query query) {
        Dql dql = query.getDql();
        return dql.accept(new QueryPlannerDqlVisitor(queryBeans), null);
    }

    public class QueryPlannerDqlVisitor extends DqlVisitor<PlanNode, PlanNode> {
        private final QueryBeans queryBeans;

        public QueryPlannerDqlVisitor(QueryBeans queryBeans) {
            this.queryBeans = queryBeans;
        }

        public PlanNode visitSingleDql(SingleDql singleDql, PlanNode planNode) {
            QueryBody queryBody = new QueryBody();
            DatasetSpec datasetSpec = singleDql.getDatasetSpec();

            singleDql.children().stream().forEach(child -> {
                child.accept(this, queryBody);
            });
            return queryBody;
        }

        public PlanNode visitDatasetSpec(DatasetSpec datasetSpec, PlanNode planNode) {
            String uuid = datasetSpec.getDatasetUuid();
            Dataset dataset = queryBeans.datasetService().getDataset(uuid);
            From from = new From(dataset.getCode(), dataset.getName());
            planNode.addChild(from);
            return planNode;
        }

        public PlanNode visitDimensionSpec(DimensionSpec dimensionSpec, PlanNode planNode) {
            Projects projects = planNode.getProjects();
            if (Objects.isNull(projects)) {
                projects = new Projects();
                planNode.addChild(projects);
            }
            projects.addProject(dimensionSpec.getDimensionCode());

            return planNode;
        }

        public PlanNode visitMetricSpec(MetricSpec metricSpec, PlanNode planNode) {
            Projects projects = planNode.getProjects();
            if (Objects.isNull(projects)) {
                projects = new Projects();
                planNode.addChild(projects);
            }
            projects.addProject(metricSpec.getMetricCode());
            return planNode;
        }
    }


}
