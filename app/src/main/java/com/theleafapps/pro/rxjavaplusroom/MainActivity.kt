package com.theleafapps.pro.rxjavaplusroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.theleafapps.pro.rxjavaplusroom.ui.StudentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(StudentViewModel::class.java)

        // observer the live data
        observers()
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
}