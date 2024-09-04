package io.github.mat3e.task.vo;

import java.time.Instant;
import java.time.ZonedDateTime;

import io.github.mat3e.DomainEvent;

public class TaskEvent implements DomainEvent {
    public enum State {
        DONE, UNDONE, UPDATED, DELETED
    }

    private final TaskSourceId id;
    private final Instant occurredOn;
    private final Data data;
    private final State state;

    public TaskEvent(TaskSourceId id, State state, Data data) {
        this.id = id;
        this.occurredOn = Instant.now();
        this.data = data;
        this.state = state;
    }

    public TaskSourceId getSourceId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public State getState() {
        return state;
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    public static class Data {
        private ZonedDateTime deadline;
        private String description;
        private String additionalComment;

        public Data(ZonedDateTime deadline, String description, String additionalComment) {
            this.deadline = deadline;
            this.description = description;
            this.additionalComment = additionalComment;
        }

        public ZonedDateTime getDeadline() {
            return deadline;
        }

        public String getDescription() {
            return description;
        }

        public String getAdditionalComment() {
            return additionalComment;
        }

    }

}