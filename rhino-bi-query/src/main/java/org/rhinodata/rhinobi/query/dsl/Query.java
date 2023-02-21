package org.rhinodata.rhinobi.query.dsl;

import lombok.Getter;

import java.util.Date;

/**
 * @author chenye
 * @date 2023-02-03
 */

@Getter
public class Query {


    /**
     * 一次查询上下文的统一标识ID
     */
    private final String queryId;

    /**
     * 查询的用户
     */
    private final String queryUser;

    /**
     * 执行的查询内容
     */
    private final Dql Dql;

    /**
     * 查询开始时间
     */
    private final Date beginTime;


    public Query(String queryId, String queryUser, Dql dql) {
        this.queryId = queryId;
        this.queryUser = queryUser;
        Dql = dql;
        this.beginTime = new Date();
    }


}
