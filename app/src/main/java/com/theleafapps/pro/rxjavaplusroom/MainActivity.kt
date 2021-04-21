package com.theleafapps.pro.rxjavaplusroom

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity
import com.theleafapps.pro.rxjavaplusroom.ui.StudentViewModel
import com.theleafapps.pro.rxjavaplusroom.ui.adapter.StudentRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: StudentViewModel
    private lateinit var addStudentFab: FloatingActionButton
    private lateinit var studentRecyclerAdapter: StudentRecyclerAdapter
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var studentProgressBar: ProgressBar

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    private val editClickListener: (data: StudentEntity) -> Unit = {
        showEditStudentDialog(it)
    }

    private val deleteClickListener: (data: StudentEntity) -> Unit = {
        showDeleteStudentDialog(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentProgressBar = findViewById(R.id.pbStudent)
        studentRecyclerView = findViewById(R.id.student_rv)
        studentRecyclerAdapter = StudentRecyclerAdapter(editClickListener, deleteClickListener)
        addStudentFab = findViewById(R.id.addStudent)

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(StudentViewModel::class.java)

        // observer the live data
        observers()

        addStudentFab.setOnClickListener {
            showAddStudentDialog()
        }

        // RecyclerView
        studentRecyclerView.apply {
            adapter = studentRecyclerAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun observers() {
        viewModel.isLoading.observe(this, Observer {
            studentProgressBar.visibility = if(it) View.VISIBLE else View.GONE
        })

        viewModel.isSuccess.observe(this, Observer {

        })

        viewModel.isError.observe(this, Observer {

        })

        viewModel.studentList.observe(this, Observer {
            studentRecyclerAdapter.setList(it)

            studentRecyclerView.post {
                studentRecyclerView.scrollToPosition(0)
            }
        })
    }

    private fun showAddStudentDialog() {
        val dialog = MaterialDialog(this)
            .cornerRadius(8f)
            .cancelable(false)
            .customView(R.layout.student_view_dialog)

        val customView = dialog.getCustomView()
        val delete_conf_text = customView.findViewById<TextView>(R.id.delete_std_conf_tv)
        delete_conf_text.visibility = View.GONE

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

    private fun showEditStudentDialog(data: StudentEntity) {
        val dialog = MaterialDialog(this)
            .cornerRadius(8f)
            .cancelable(false)
            .customView(R.layout.student_view_dialog)

        val customView = dialog.getCustomView()
        // get the view
        val name = customView.findViewById<TextInputEditText>(R.id.studentName)
        val age = customView.findViewById<TextInputEditText>(R.id.studentAge)
        val subject = customView.findViewById<TextInputEditText>(R.id.studentSubject)
        val delete_conf_text = customView.findViewById<TextView>(R.id.delete_std_conf_tv)

        // set the value
        name.setText(data.studentName)
        age.setText(data.age.toString())
        subject.setText(data.subject)
        delete_conf_text.visibility = View.GONE

        viewModel.studentId.value = data.id

        dialog.positiveButton {
            viewModel.studentName.value = name.text.toString()
            val tempAge = age.text.toString()
            viewModel.studentAge.value = tempAge.toInt()
            viewModel.studentSubject.value = subject.text.toString()

            viewModel.update()
        }
        dialog.negativeButton {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDeleteStudentDialog(data: StudentEntity) {
        val dialog = MaterialDialog(this)
            .cornerRadius(8f)
            .cancelable(false)
            .customView(R.layout.student_view_dialog)

        val customView = dialog.getCustomView()
        // get the view
        val name = customView.findViewById<TextInputEditText>(R.id.studentName)
        val age = customView.findViewById<TextInputEditText>(R.id.studentAge)
        val subject = customView.findViewById<TextInputEditText>(R.id.studentSubject)
        val delete_conf_text = customView.findViewById<TextView>(R.id.delete_std_conf_tv)

        name.visibility = View.GONE
        age.visibility = View.GONE
        subject.visibility = View.GONE
        delete_conf_text.visibility = View.VISIBLE

        // set the value
        name.setText(data.studentName)
        age.setText(data.age.toString())
        subject.setText(data.subject)

        viewModel.studentId.value = data.id

        dialog.positiveButton {
            viewModel.studentName.value = name.text.toString()
            val tempAge = age.text.toString()
            viewModel.studentAge.value = tempAge.toInt()
            viewModel.studentSubject.value = subject.text.toString()

            viewModel.delete()
        }
        dialog.negativeButton {
            dialog.dismiss()
        }
        dialog.show()
    }
}