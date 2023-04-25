package com.example.todoapp.ui.main.Model

data class Task(val name:String, val desc:String, val category: TaskCategory, var Checked:Boolean = false) {
}