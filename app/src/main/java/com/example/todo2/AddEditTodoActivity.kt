package com.example.todo2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditTodoActivity : AppCompatActivity() {

    lateinit var todoTitleEdt: EditText
    lateinit var todoEdt: EditText
    lateinit var saveBtn: Button

    lateinit var viewModal: TodoViewModal
    var todoID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_todo)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TodoViewModal::class.java)

        todoTitleEdt = findViewById(R.id.idEdtTodoName)
        todoEdt = findViewById(R.id.idEdtTodoDesc)
        saveBtn = findViewById(R.id.idBtn)

        val todoType = intent.getStringExtra("todoType")
        if (todoType.equals("Edit")) {

            val todoTitle = intent.getStringExtra("todoTitle")
            val todoDescription = intent.getStringExtra("todoDescription")
            todoID = intent.getIntExtra("todoId", -1)
            saveBtn.setText("Update Todo")
            todoTitleEdt.setText(todoTitle)
            todoEdt.setText(todoDescription)
        } else {
            saveBtn.setText("Save Todo")
        }

        saveBtn.setOnClickListener {

            val todoTitle = todoTitleEdt.text.toString()
            val todoDescription = todoEdt.text.toString()

            if (todoType.equals("Edit")) {
                if (todoTitle.isNotEmpty() && todoDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedTodo =
                        Todo(todoTitle, todoDescription, currentDateAndTime)
                    updatedTodo.id = todoID
                    viewModal.updateTodo(updatedTodo)
                    Toast.makeText(this, "Todo Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (todoTitle.isNotEmpty() && todoDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())

                    viewModal.addTodo(Todo(todoTitle, todoDescription, currentDateAndTime))
                    Toast.makeText(this, "$todoTitle Added", Toast.LENGTH_LONG).show()
                }
            }

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}
