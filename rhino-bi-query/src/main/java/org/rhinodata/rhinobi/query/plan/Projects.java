package org.rhinodata.rhinobi.query.plan;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chenye
 * @date 2023-02-06
 */
public class Projects implements Iterable<Projects.Project> {

    private final List<Project> projectList = new ArrayList<>();

    public Projects addProject(Project project) {
        this.projectList.add(project);
        return this;
    }

    public Projects addProject(String projectCode) {
        Project project = new Project(projectCode);
        this.projectList.add(project);
        return this;
    }

    public int size() {
        return projectList.size();
    }


    @NotNull
    @Override
    public Iterator<Project> iterator() {
        return projectList.stream().iterator();
    }

    @Override
    public void forEach(Consumer<? super Project> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Project> spliterator() {
        return Iterable.super.spliterator();
    }


    @Builder
    @EqualsAndHashCode
    @ToString
    public static class Project {
        private final String original;
        private final String alias;

        public Project(String original, String alias) {
            this.original = original;
            this.alias = alias;
        }

        public Project(String name) {
            this(name, name);
        }

        public String getOriginal() {
            return original;
        }

        public String getAlias() {
            return alias;
        }
    }
}
