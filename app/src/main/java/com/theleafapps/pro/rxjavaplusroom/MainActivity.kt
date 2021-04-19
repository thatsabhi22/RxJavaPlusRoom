package com.theleafapps.pro.rxjavaplusroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.theleafapps.pro.rxjavaplusroom.ui.StudentViewModel
import com.theleafapps.pro.rxjavaplusroom.ui.adapter.StudentRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : StudentViewModel
    private lateinit var addStudentFab : FloatingActionButton
    private lateinit var studentRecyclerAdapter : StudentRecyclerAdapter
    private lateinit var studentRecyclerView : RecyclerView

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentRecyclerView = findViewById(R.id.student_rv)
        studentRecyclerAdapter = StudentRecyclerAdapter()
        addStudentFab = findViewById(R.id.addStudent)

        viewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(StudentViewModel::class.java)

        // observer the live data
        observers()

        addStudentFab.setOnClickListener{
            showStudentDialog()
        }

        // RecyclerView
        studentRecyclerView.apply {
            adapter = studentRecyclerAdapter
            layoutManager = linearLayoutManager
        }

    }

    private fun observers(){
        viewModel.isLoading.observe(this, Observer{

        })

        viewModel.isSuccess.observe(this, Observer{

        })

        viewModel.isError.observe(this, Observer{

        })

        viewModel.studentList.observe(this, Observer{
            studentRecyclerAdapter.setList(it)
        })
    }

    private fun showStudentDialog(){
        val dialog = MaterialDialog(this)
            .cornerRadius(8f)
            .cancelable(false)
            .customView(R.layout.student_view_dialog)

        val customView = dialog.getCustomView()
        dialog.positiveButton {
            val name = customView.findViewById<TextInputEditText>(R.id.studentName)
            val age = customView.findViewById<TextInputEditText>(R.id.studentAge)
            val subject = customView.findViewById<TextInputEditText>(R.id.studentSubject)

            viewModel.studentName.value = name.text.toString()
            val tempAge = age.text.toString()
            viewModel.studentAge.value = tempAge.toInt()
            viewModel.studentSubject.value = subject.text.toString()

            viewModel.insert()
        }
        dialog.negativeButton {
            dialog.dismiss()
        }
        dialog.show()
    }
}