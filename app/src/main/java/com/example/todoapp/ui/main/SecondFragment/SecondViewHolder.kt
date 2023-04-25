package com.example.todoapp.ui.main.SecondFragment

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.ui.main.Model.Task

class SecondViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val tvTaskName: TextView = view.findViewById(R.id.tvTaskname)
    private val tvTaskDesc: TextView = view.findViewById(R.id.tvTaskdesc)
    private val cbCompleted: CheckBox = view.findViewById(R.id.cbCompleted)


    fun render(task: Task){
        tvTaskName.text= task.name
        tvTaskDesc.text=task.desc
        cbCompleted.isChecked = task.Checked


        if(cbCompleted.isChecked){
            tvTaskName.paintFlags = tvTaskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tvTaskDesc.paintFlags = tvTaskDesc.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTaskName.paintFlags = tvTaskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            tvTaskDesc.paintFlags = tvTaskDesc.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

    }
}