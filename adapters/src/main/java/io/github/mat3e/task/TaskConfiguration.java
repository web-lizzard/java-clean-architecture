package io.github.mat3e.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.mat3e.DomainEventPublisher;

@Configuration
class TaskConfiguration {
    @Bean
    TaskFacade taskFacade(TaskRepository taskRepository, DomainEventPublisher publisher) {
        return new TaskFacade(new TaskFactory(), taskRepository, publisher);
    }
}
