package com.example.todo2

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TodosDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo : Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("Select * from todosTable order by id ASC")
    fun getAllTodos(): LiveData<List<Todo>>

    @Update
    suspend fun update(todo: Todo)

}