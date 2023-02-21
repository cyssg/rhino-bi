package org.rhinodata.rhinobi.query.plan;

/**
 * @author chenye
 * @date 2023-02-06
 */

public class Expression {

    private String expr;

    public Expression(String expr){
        this.expr = expr;
    }

    public String toString(){
        return expr;
    }
}
