package com.albert.infinitespirit.addtasks.domain

import com.albert.infinitespirit.addtasks.data.TaskRepository
import com.albert.infinitespirit.addtasks.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository){
    suspend operator fun invoke(taskModel: TaskModel)=taskRepository.update(taskModel)
}