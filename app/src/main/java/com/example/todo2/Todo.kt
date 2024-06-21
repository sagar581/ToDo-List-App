package com.example.todo2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todosTable")
class Todo (
    @ColumnInfo(name = "title")val todoTitle :String,
    @ColumnInfo(name = "description")val todoDescription :String,
    @ColumnInfo(name = "timestamp")val timeStamp :String)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}