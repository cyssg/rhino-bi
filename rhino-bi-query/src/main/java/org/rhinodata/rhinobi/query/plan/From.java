package org.rhinodata.rhinobi.query.plan;

/**
 * @author chenye
 * @date 2023-02-20
 */
public class From extends PlanNode {

    private final String code;
    private final String alias;


    protected From(String code, String alias) {
        this.code = code;
        this.alias = alias;
    }

    public String getCode() {
        return code;
    }

    public String getAlias() {
        return alias;
    }
}
