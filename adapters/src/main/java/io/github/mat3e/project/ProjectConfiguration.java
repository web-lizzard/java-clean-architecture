package io.github.mat3e.project;

import io.github.mat3e.task.TaskFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectConfiguration {
    @Bean
    ProjectFacade projectFacade(
            ProjectRepository projectRepository,
            TaskFacade taskFacade) {
        return new ProjectFacade(
                new ProjectFactory(),
                projectRepository,
                taskFacade);
    }
}
