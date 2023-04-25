package com.example.todoapp.ui.main.SecondFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.ui.main.Model.TaskCategory
import com.example.todoapp.ui.main.Model.Task

lateinit var recycle:RecyclerView; // *****************************




class SecondFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        //    param1 = it.getString(ARG_PARAM1)
       //     param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var asd = inflater.inflate(R.layout.fragment_second, container, false)

        recycle = asd.findViewById(R.id.rvLista2);
        InitUI()
        Log.i("mine","recycler del seg: $recycle , adapter: $secondAdapter" )


        return asd
    }

    private fun InitUI() {
        secondAdapter = SecondAdapter(tasksWork){ onItemSelected(it) }
        recycle.layoutManager = LinearLayoutManager(this.context)
        recycle.adapter= secondAdapter


    }

    private fun onItemSelected(position:Int){
        tasksWork[position].Checked = !tasksWork[position].Checked

        if(tasksWork[position].Checked){
            val currenttask:Task = tasksWork[position]
            val index:Int = tasksWork.indexOf(tasksWork[position])

            tasksWork.removeAt(index)
            tasksWork.add(currenttask)
        }

        UpdateTaskList();
    }

    public fun UpdateTaskList(){
        secondAdapter.notifyDataSetChanged()
    }


    companion object {

        val tasksWork = mutableListOf(
            Task("escribir reporte","10 paginas", TaskCategory.work),
            Task("publicar reporte","antes del lunes", TaskCategory.work),
            Task("rediseñar homepage","", TaskCategory.work)
        )

        lateinit var  secondAdapter: SecondAdapter


        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
            //        putString(ARG_PARAM1, param1)
             //       putString(ARG_PARAM2, param2)
                }
            }

    }

}