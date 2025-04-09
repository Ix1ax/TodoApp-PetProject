package ru.ixlax.TodoWebApp.mappers;

import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.dto.request.TaskRequest;
import ru.ixlax.TodoWebApp.dto.response.TaskResponse;
import ru.ixlax.TodoWebApp.dto.response.TasksResponse;
import ru.ixlax.TodoWebApp.models.task.Task;

@Component
public class TaskMapper {

    public Task toTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setDescription(taskRequest.getDescription());
        task.setPriority(taskRequest.getPriority());
        task.setDateOfEnd(taskRequest.getDateOfEnd());

        return task;
    }

    public TasksResponse toTasksResponse(Task task) {

        if(task == null) {
            return null;
        }

        return new TasksResponse(
                task.getId(),
                task.getTaskName(),
                task.getDescription(),
                task.getPriority(),
                task.getDateOfEnd()
        );
    }

    public TaskResponse toTaskResponse(Task task) {
        if(task == null) {
            return null;
        }

        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getDescription(),
                task.getPriority(),
                task.getUpdateAt(),
                task.getDateOfEnd(),
                null
//                Optional.ofNullable(task.getComments())
//                        .orElse(Collections.emptyList())
//                        .stream()
//                        .map(Comment::getId)
//                        .toList()

        );
    }


}
