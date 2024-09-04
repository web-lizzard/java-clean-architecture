package io.github.mat3e.project;

class ProjectStepSnapshot {
    private int id;
    private String description;
    private int daysToProjectDeadline;
    private boolean hasCorrespondingTask;
    private boolean correspondingTaskDone;

    public ProjectStepSnapshot() {
    }

    ProjectStepSnapshot(final int id, final String description, final int daysToProjectDeadline,
            boolean hasCorrespondingTask,
            boolean correspondingTaskDone) {
        this.id = id;
        this.description = description;
        this.daysToProjectDeadline = daysToProjectDeadline;
        this.correspondingTaskDone = correspondingTaskDone;
        this.hasCorrespondingTask = hasCorrespondingTask;
    }

    int getId() {
        return this.id;
    }

    String getDescription() {
        return this.description;
    }

    int getDaysToProjectDeadline() {
        return this.daysToProjectDeadline;
    }

    boolean hasCorrespondingTask() {
        return hasCorrespondingTask;
    }

    boolean isCorrespondingTaskDone() {
        return correspondingTaskDone;
    }
}
