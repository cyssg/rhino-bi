package org.rhinodata.rhinobi.query.common;

/**
 * @author chenye
 * @date 2023-02-20
 */
public interface QueryConstants {


    String SPACE = " ";
    String SELECT = "select" + SPACE;
    String AS = SPACE + "as" + SPACE;
    String FROM = SPACE + "from" + SPACE;
    String WHERE = SPACE + "where" + SPACE;
    String ORDER_BY = SPACE + "order by" + SPACE;
    String GROUP_BY = SPACE + "group by" + SPACE;
    String INNER_JOIN = SPACE + "inner join" + SPACE;
    String LEFT_JOIN = SPACE + "left join" + SPACE;
    String RIGHT_JOIN = SPACE + "right join" + SPACE;
    String FULL_JOIN = SPACE + "full outer join" + SPACE;
    String ON = SPACE + "on" + SPACE;
    String SINGLE_QUOTE = "'";
    String DOUBLE_QUOTE = "\"";
    String SPECIAL_QUOTE = "`";
    String COMMA = ",";
    String LEFT_BRACKET = "(";
    String RIGHT_BRACKET = ")";

}
