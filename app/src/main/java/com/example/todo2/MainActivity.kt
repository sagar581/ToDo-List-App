package com.example.todo2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TodoClickInterface, TodoClickDeleteInterface {

    lateinit var viewModal: TodoViewModal
    lateinit var todosRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    public fun func():String{
        return "sdgfd"
    }
    
    private fun logoutUser(username: String) {
        SharedPreferencesHelper.saveUserLoginStatus(this, false,username)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var username = SharedPreferencesHelper.getUsername()
       //logout button listener
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logoutUser(username)
        }

        todosRV = findViewById(R.id.todosRV)
        addFAB = findViewById(R.id.idFAB)

        todosRV.layoutManager = LinearLayoutManager(this)

        val todoRVAdapter = TodoRVAdapter(this, this, this)

        todosRV.adapter = todoRVAdapter


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TodoViewModal::class.java)

        viewModal.allTodos.observe(this, Observer { list ->
            list?.let {

                todoRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {

            val intent = Intent(this@MainActivity, AddEditTodoActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onTodoClick(todo: Todo) {

        val intent = Intent(this@MainActivity, AddEditTodoActivity::class.java)
        intent.putExtra("todoType", "Edit")
        intent.putExtra("todoTitle", todo.todoTitle)
        intent.putExtra("todoDescription", todo.todoDescription)
        intent.putExtra("todoId", todo.id)
        startActivity(intent)
        this.finish()
    }
    override fun onDeleteIconClick(todo: Todo) {

        viewModal.deleteTodo(todo)

        Toast.makeText(this, "${todo.todoTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}
