package org.rhinodata.rhinobi.query.plan;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.rhinodata.rhinobi.metadata.domain.Dataset;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.analysis.*;
import org.rhinodata.rhinobi.query.common.QueryConstants;

import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-23
 */
public record StatementToSql(QueryContext queryContext) {

  public String toSql(Statement statement) {
    return statement.accept(new Visitor(queryContext), null);
  }

  @Data
  class SqlSpec {
    private StringBuffer selectStr = new StringBuffer();
    private StringBuffer fromStr = new StringBuffer();
    private StringBuffer whereStr = new StringBuffer();
    private StringBuffer groupByStr = new StringBuffer();
    private StringBuffer orderByStr = new StringBuffer();

    String toSql() {
      return new StringBuffer().append(selectStr).append(fromStr).append(whereStr).append(groupByStr).append(orderByStr).toString();
    }
  }

  class Visitor extends StatementVisitor<String, StatementToSql.SqlSpec> {

    private QueryContext queryContext;

    Visitor(QueryContext queryContext) {
      this.queryContext = queryContext;
    }

    @Override
    public String visitQueryStatement(QueryStatement queryStatement, StatementToSql.SqlSpec parent){
      StatementToSql.SqlSpec sqlSpec = new StatementToSql.SqlSpec();
      queryStatement.children().forEach(statement -> {
        statement.accept(this,sqlSpec);
      });
      String sql = sqlSpec.toSql();
      if (Objects.nonNull(parent)){
        parent.getFromStr().append(sql);
      }
      return sql;
    }

    @Override
    public String visitScanStatement(ScanStatement scanStatement, StatementToSql.SqlSpec parent) {
      String datasetUuid = scanStatement.getDatasetUuid();
      Dataset dataset = queryContext.getQueryBeans().datasetService().getDataset(datasetUuid);
      parent.getFromStr()
              .append(QueryConstants.FROM)
              .append(QueryConstants.LEFT_BRACKET)
              .append(dataset.getCode())
              .append(QueryConstants.RIGHT_BRACKET)
              .append(QueryConstants.TABLE_ALIAS);
      return parent.toSql();
    }

    @Override
    public String visitProjectStatement(ProjectStatement projectStatement, StatementToSql.SqlSpec parent) {
      parent.getSelectStr().append(buildSelectStr(projectStatement));
      parent.getGroupByStr().append(buildGroupByStr(projectStatement));
      return parent.toSql();
    }

    private String buildSelectStr(ProjectStatement projectStatement) {
      StringBuffer selectStr = new StringBuffer(QueryConstants.SELECT);
      projectStatement.forEach(
              item -> {
                selectStr.append(item.getExpr());
                if (StrUtil.isNotBlank(item.getAlias())) {
                  selectStr.append(QueryConstants.AS).append(item.getAlias());
                }
                selectStr.append(QueryConstants.COMMA);
              });
      if (StrUtil.equals(
              String.valueOf(selectStr.charAt(selectStr.length() - 1)), QueryConstants.COMMA)) {
        selectStr.deleteCharAt(selectStr.length() - 1);
      }
      return selectStr.toString();
    }

    private String buildGroupByStr(ProjectStatement projectStatement) {
      StringBuffer groupByStr = new StringBuffer(QueryConstants.GROUP_BY);
      projectStatement.forEach(
              item -> {
                if (item.isGroupBy()){
                  groupByStr.append(item.getExpr()).append(QueryConstants.COMMA);
                }
              });
      if (StrUtil.equals(
              String.valueOf(groupByStr.charAt(groupByStr.length() - 1)), QueryConstants.COMMA)) {
        groupByStr.deleteCharAt(groupByStr.length() - 1);
      } else {
        return "";
      }
      return groupByStr.toString();
    }

    public String visitFilterStatement(FilterStatement filterStatement,StatementToSql.SqlSpec parent){
      parent.whereStr.append(QueryConstants.WHERE).append(filterStatement.getPredicate().accept(this,parent));
      return  parent.whereStr.toString();
    }

    @Override
    public String visitConditionStatement(ConditionStatement conditionStatement, SqlSpec context) {
      StringBuffer conditionStr = new StringBuffer();
      SymbolReference reference = conditionStatement.getReference();
      conditionStr
              .append(reference.getExpr())
              .append(QueryConstants.SPACE)
              .append(conditionStatement.getOpType().getCode())
              .append(QueryConstants.SPACE);
      switch (reference.getDataType()){
        case STRING -> {
          conditionStr
                  .append(QueryConstants.SINGLE_QUOTE)
                  .append(conditionStatement.getValue())
                  .append(QueryConstants.SINGLE_QUOTE);
          break;
        }
        default -> {
          conditionStr.append(conditionStatement.getValue());
        }
      }

      return conditionStr.toString();
    }

    @Override
    public String visitRelationalStatement(RelationalStatement relationalStatement, SqlSpec context) {
      StringBuffer relationStr = new StringBuffer();
      for(int i = 0;i<relationalStatement.getStatementList().size();i++){
        PredicateStatement statement = relationalStatement.getStatementList().get(i);
        if (statement instanceof RelationalStatement){
          relationStr.append(QueryConstants.LEFT_BRACKET);
        }
        relationStr.append(statement.accept(this,context));
        if (statement instanceof RelationalStatement){
          relationStr.append(QueryConstants.RIGHT_BRACKET);
        }
        if(i+1<relationalStatement.getStatementList().size()){
          relationStr.append(QueryConstants.SPACE).append(relationalStatement.getRelationalType().getCode()).append(QueryConstants.SPACE);
        }
      }
      return relationStr.toString();
    }

    public String visitJoinStatement(JoinStatement joinStatement, StatementToSql.SqlSpec parent){
      StringBuffer joinStr = new StringBuffer();

      return joinStr.toString();
    }
  }
}


