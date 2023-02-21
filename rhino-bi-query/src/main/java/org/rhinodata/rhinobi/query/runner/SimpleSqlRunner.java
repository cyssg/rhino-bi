package org.rhinodata.rhinobi.query.runner;

import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.common.QueryConstants;
import org.rhinodata.rhinobi.query.plan.From;
import org.rhinodata.rhinobi.query.plan.PlanNode;
import org.rhinodata.rhinobi.query.plan.PlanVisitor;
import org.rhinodata.rhinobi.query.plan.Projects;

/**
 * @author chenye
 * @date 2023-02-20
 */
public class SimpleSqlRunner implements QueryRunner {


    private final StringBuffer selectCharacter;
    private final StringBuffer fromCharacter;
    private final StringBuffer whereCharacter;
    private final StringBuffer groupByCharacter;
    private final StringBuffer orderByCharacter;
    private final StringBuffer limitCharacter;
    private final String sql;

    public SimpleSqlRunner(PlanNode planNode) {
        this.selectCharacter = new StringBuffer(QueryConstants.SELECT);
        this.fromCharacter = new StringBuffer(QueryConstants.FROM);
        this.whereCharacter = new StringBuffer();
        this.groupByCharacter = new StringBuffer();
        this.orderByCharacter = new StringBuffer();
        limitCharacter = new StringBuffer();
        this.sql = genSql(planNode);

    }

    private String genSql(PlanNode planNode) {
        planNode.children.forEach(p->{
            p.accept(new SimpleSqlPlanVisitor(), this);
        });
        return selectCharacter.append(fromCharacter).toString();
    }

    @Override
    public void exec(QueryContext queryContext) {

    }


    class SimpleSqlPlanVisitor extends PlanVisitor<Void, SimpleSqlRunner> {

        @Override
        public Void visitProjects(Projects projects, SimpleSqlRunner context) {
            if (projects.size() > 0) {
                projects.forEach(p -> {
                    selectCharacter
                            .append(p.getOriginal())
                            .append(QueryConstants.AS)
                            .append(p.getOriginal())
                            .append(QueryConstants.COMMA);
                });
                selectCharacter.deleteCharAt(selectCharacter.length() - 1);
            }
            return null;
        }

        public Void visitFrom(From from, SimpleSqlRunner context) {
            fromCharacter
                    .append(QueryConstants.LEFT_BRACKET)
                    .append(from.getCode())
                    .append(QueryConstants.RIGHT_BRACKET)
                    .append(QueryConstants.AS)
                    .append(from.getAlias());
            return null;
        }

    }

}
