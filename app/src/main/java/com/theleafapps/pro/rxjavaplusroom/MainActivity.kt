package com.theleafapps.pro.rxjavaplusroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.theleafapps.pro.rxjavaplusroom.ui.StudentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : StudentViewModel
    private lateinit var addStudentFab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addStudentFab = findViewById(R.id.addStudent)

        viewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(StudentViewModel::class.java)

        // observer the live data
        observers()

        addStudentFab.setOnClickListener{
            showStudentDialog()
        }
    }

    private fun observers(){
        viewModel.isLoading.observe(this,{

        })

        viewModel.isSuccess.observe(this,{

        })

        viewModel.isError.observe(this,{

        })

        viewModel.studentList.observe(this,{

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
        }
        dialog.negativeButton {
            dialog.dismiss()
        }
        dialog.show()
    }
}