package io.github.mat3e.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import io.github.mat3e.task.vo.TaskEvent;

@Service
class ProjectEventListener {
    private final ProjectFacade facade;

    public ProjectEventListener(ProjectFacade facade) {
        this.facade = facade;
    }

    @EventListener
    public void on(TaskEvent event) {
        facade.handle(event);
    }
}
