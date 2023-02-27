package org.rhinodata.rhinobi.query.plan.rewrite;

import com.google.common.collect.ImmutableList;
import org.rhinodata.rhinobi.query.analysis.Statement;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * @author chenye
 * @date 2023-02-23
 */
public class StatementRewrite {

    private final List<Rewrite> rewrites;

    public StatementRewrite(Set<Rewrite> rewrites) {
        this.rewrites = ImmutableList.copyOf(requireNonNull(rewrites, "rewrites is null"));
    }

    public Statement rewrite(Statement statement) {
        for (Rewrite rewrite : rewrites) {
            statement = rewrite.rewrite(statement);
        }
        return statement;
    }

    public interface Rewrite {
        Statement rewrite(Statement statement);
    }
}
