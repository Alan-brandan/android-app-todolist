package com.example.todoapp.ui.main.SecondFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.ui.main.FirstFragment.FirstViewHolder
import com.example.todoapp.ui.main.Model.Task

class SecondAdapter (private val tasks:List<Task>, private val onTaskSelected:(Int)->Unit): RecyclerView.Adapter<FirstViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return FirstViewHolder(view);
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        holder.render(tasks[position]);
        holder.itemView.setOnClickListener {
            onTaskSelected(position)
        }
    }

    override fun getItemCount(): Int = tasks.size;
}