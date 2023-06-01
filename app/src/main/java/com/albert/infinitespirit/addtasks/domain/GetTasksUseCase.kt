package com.albert.infinitespirit.addtasks.domain

import com.albert.infinitespirit.addtasks.data.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.tasks
}