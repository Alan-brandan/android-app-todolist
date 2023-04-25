package com.example.todoapp

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.todoapp.ui.main.SectionsPagerAdapter
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.main.Model.TaskCategory
import com.example.todoapp.ui.main.FirstFragment.FirstFragment

import com.example.todoapp.ui.main.Model.Task
import com.example.todoapp.ui.main.SecondFragment.SecondFragment

class MainActivity : AppCompatActivity() {

    private lateinit var fabAddTask:FloatingActionButton


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        InitComponent();
        InitListeners()

    }



    private fun InitComponent() {
        fabAddTask=findViewById(R.id.fabAddTask)
    }
    private fun InitListeners() {
        fabAddTask.setOnClickListener {
            ShowDialog();
        }
    }

    public fun UpdateTaskList(){
        FirstFragment.categoriesAdapter.notifyDataSetChanged()
        SecondFragment.secondAdapter.notifyDataSetChanged()
    }


    private fun ShowDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask:Button = dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val etTaskdesc:EditText = dialog.findViewById(R.id.etTaskdesc)
        val rgCategory:RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentText= etTask.text.toString();
            if(currentText.isNotEmpty()){

                val currentDescription = etTaskdesc.text.toString()
                val selectedId = rgCategory.checkedRadioButtonId
                val selectedrb:RadioButton = rgCategory.findViewById(selectedId)

                when(selectedrb.text){
                    "Personal" -> FirstFragment.tasksPersonal.add(Task(currentText,currentDescription,
                        TaskCategory.personal))
                    else -> SecondFragment.tasksWork.add(Task(currentText,currentDescription,
                        TaskCategory.work))
                }
                UpdateTaskList()
                dialog.hide()
            }
        }

        dialog.show();
    }

}