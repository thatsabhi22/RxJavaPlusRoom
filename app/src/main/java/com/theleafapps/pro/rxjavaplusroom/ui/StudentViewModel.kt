package com.theleafapps.pro.rxjavaplusroom.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.theleafapps.pro.rxjavaplusroom.data.local.db.DBService
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity
import com.theleafapps.pro.rxjavaplusroom.data.repository.StudentRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class StudentViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "StudentViewModel"
    companion object{
        private val TAG = "StudentViewModel"
    }

    private val studentRepository = StudentRepository(DBService.getInstance(application))
    private val compositeDisposable = CompositeDisposable()


    private val studentId: MutableLiveData<Int> = MutableLiveData()
    private val studentName: MutableLiveData<String> = MutableLiveData()
    private val studentAge: MutableLiveData<Int> = MutableLiveData()
    private val studentSubject: MutableLiveData<String> = MutableLiveData()

    fun insert(){
        compositeDisposable.add(
            studentRepository.insert(createStudentEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "Insert : $it")
                    },
                    {
                        Log.e(TAG, it.toString())
                    }
                )
        )
    }

    fun update(){
        compositeDisposable.add(
            studentRepository.update(createStudentEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "Insert : $it")
                    },
                    {
                        Log.e(TAG, it.toString())
                    }
                )
        )
    }

    private fun createStudentEntity(): StudentEntity {
        return StudentEntity(
            studentName = studentName.value.toString(),
            age = studentAge.value!!,
            subject = studentSubject.value.toString()
        )
    }
}