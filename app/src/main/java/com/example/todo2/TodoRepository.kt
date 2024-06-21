package com.example.todo2

import androidx.lifecycle.LiveData

class TodoRepository(private val todosDao: TodosDao) {

    val allTodos: LiveData<List<Todo>> = todosDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todosDao.insert(todo)
    }

    suspend fun delete(todo: Todo){
        todosDao.delete(todo)
    }

    suspend fun update(todo: Todo){
        todosDao.update(todo)
    }
}
