package com.nrkt.departmentprojectcrud.mapper;

import com.nrkt.departmentprojectcrud.dto.request.TaskRequest;
import com.nrkt.departmentprojectcrud.dto.response.TaskResponse;
import com.nrkt.departmentprojectcrud.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponse taskEntityToTaskResponse(Task task);

    @Mapping(target = "project", ignore = true)
    Task taskRequestToTaskEntity(TaskRequest taskRequest);

    List<TaskResponse> taskEntityToProjectTaskList(List<Task> tasks);
}
