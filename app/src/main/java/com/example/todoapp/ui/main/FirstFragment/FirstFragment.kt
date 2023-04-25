package com.example.todoapp.ui.main.FirstFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentFirstBinding
import com.example.todoapp.ui.main.Model.TaskCategory
import com.example.todoapp.ui.main.Model.Task
import com.example.todoapp.ui.main.PageViewModel

class FirstFragment : Fragment() {

    lateinit var recycle:RecyclerView; // *****************************

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val root = binding.root


        recycle = root.findViewById(R.id.rvLista);
        InitUI()
        return root
    }

    companion object {

        val tasksPersonal = mutableListOf(
            Task("Enviar invitaciones","antes de fin de mes", TaskCategory.personal),
            Task("Cena familiar","4/10 20hs", TaskCategory.personal)
        )

         lateinit var  categoriesAdapter: FirstAdapter


        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): FirstFragment {
            return FirstFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private fun InitUI() {
        categoriesAdapter = FirstAdapter(tasksPersonal) { onItemSelected(it) }
        recycle.layoutManager = LinearLayoutManager(this.context)
        recycle.adapter= categoriesAdapter


    }

    private fun onItemSelected(position:Int){
        tasksPersonal[position].Checked = !tasksPersonal[position].Checked

        if(tasksPersonal[position].Checked){
            val currenttask:Task = tasksPersonal[position]
            val index:Int = tasksPersonal.indexOf(tasksPersonal[position])

            tasksPersonal.removeAt(index)
            tasksPersonal.add(currenttask)
        }

        UpdateTaskList();
    }

    public fun UpdateTaskList(){
        categoriesAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}