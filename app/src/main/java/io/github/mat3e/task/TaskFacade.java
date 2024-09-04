package io.github.mat3e.task;

import io.github.mat3e.DomainEventPublisher;
import io.github.mat3e.task.dto.TaskDto;
import io.github.mat3e.task.vo.TaskCreator;
import io.github.mat3e.task.vo.TaskEvent;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class TaskFacade {
    private final TaskFactory taskFactory;
    private final TaskRepository taskRepository;
    private final DomainEventPublisher publisher;

    TaskFacade(final TaskFactory taskFactory, final TaskRepository taskRepository, DomainEventPublisher publisher) {
        this.taskFactory = taskFactory;
        this.taskRepository = taskRepository;
        this.publisher = publisher;
    }

    public List<TaskDto> saveAll(Collection<TaskDto> tasks) {
        return taskRepository.saveAll(
                tasks.stream().map(dto -> taskFactory.from(dto))
                        .collect(toList()))
                .stream().map(this::toDto)
                .collect(toList());
    }

    public List<TaskDto> createAll(Set<TaskCreator> tasks) {
        return taskRepository.saveAll(
                tasks.stream().map(Task::createFrom)
                        .collect(toList()))
                .stream().map(this::toDto)
                .collect(toList());
    }

    TaskDto save(TaskDto toSave) {
        return toDto(taskRepository.save(
                taskRepository.findById(toSave.getId()).map(existingTask -> {
                    if (existingTask.getSnapshot().getDone() != toSave.isDone()) {
                        publisher.publish(existingTask.toggle());
                    }
                    publisher.publish(existingTask.updateInfo(
                            toSave.getDescription(),
                            toSave.getDeadline(),
                            toSave.getAdditionalComment()));
                    return existingTask;
                }).orElseGet(() -> taskFactory.from(toSave))));
    }

    void delete(int id) {
        taskRepository.findById(id).ifPresent(task -> {
            TaskSnapshot snapshot = task.getSnapshot();
            taskRepository.deleteById(snapshot.getId());
            publisher.publish(new TaskEvent(snapshot.getSourceId(), TaskEvent.State.DELETED, new TaskEvent.Data(
                    snapshot.getDeadline(), snapshot.getDescription(), snapshot.getAdditionalComment())));
        });

    }

    private TaskDto toDto(Task task) {
        var snap = task.getSnapshot();
        return TaskDto.builder()
                .withId(snap.getId())
                .withDescription(snap.getDescription())
                .withDone(snap.getDone())
                .withDeadline(snap.getDeadline())
                .withAdditionalComment(snap.getAdditionalComment())
                .build();
    }

}
