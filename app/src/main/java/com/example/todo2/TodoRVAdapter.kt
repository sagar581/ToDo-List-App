package com.example.todo2
import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo2.MainActivity
class TodoRVAdapter(
    val context: Context,
    val todoClickDeleteInterface: TodoClickDeleteInterface,
    val todoClickInterface: TodoClickInterface
) :
    RecyclerView.Adapter<TodoRVAdapter.ViewHolder>() {

    private val allTodos = ArrayList<Todo>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val todoTV = itemView.findViewById<TextView>(R.id.idTVTodo)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_rv_item,
            parent,
            false
        )
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.todoTV.setText(allTodos.get(position).todoTitle)
        holder.dateTV.setText("Last Updated : " + allTodos.get(position).timeStamp)
        holder.deleteIV.setOnClickListener {

            todoClickDeleteInterface.onDeleteIconClick(allTodos.get(position))
        }


        holder.itemView.setOnClickListener {
            todoClickInterface.onTodoClick(allTodos.get(position))
        }
    }

    override fun getItemCount(): Int {

        return allTodos.size
    }

    fun updateList(newList: List<Todo>) {

        allTodos.clear()

        allTodos.addAll(newList)

        notifyDataSetChanged()
    }
}

interface TodoClickDeleteInterface {

    fun onDeleteIconClick(todo: Todo)

}


interface TodoClickInterface {

    fun onTodoClick(todo: Todo)
}
