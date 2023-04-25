package com.example.todoapp.ui.main.Model

sealed class TaskCategory {

    object personal: TaskCategory()
    object work: TaskCategory()
}
