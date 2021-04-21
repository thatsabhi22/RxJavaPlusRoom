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

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val studentId: MutableLiveData<Int> = MutableLiveData()
    val studentName: MutableLiveData<String> = MutableLiveData()
    val studentAge: MutableLiveData<Int> = MutableLiveData()
    val studentSubject: MutableLiveData<String> = MutableLiveData()
    val studentList: MutableLiveData<List<StudentEntity>> = MutableLiveData()

    init {
        getAllStudents()
    }

    fun insert(){
        // show progress bar
        isLoading.value = true

        compositeDisposable.add(
            studentRepository.insert(createStudentEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "Insert : $it")
                        // hide progressbar
                        isLoading.value = false

                        // notify success
                        isSuccess.value = true
                    },
                    {
                        Log.e(TAG, it.toString())
                        isError.value = it.message
                        isLoading.value = false
                    }
                )
        )
    }

    fun update(){
        // show progress bar
        isLoading.value = true

        compositeDisposable.add(
            studentRepository.update(createUpdateStudentEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "Update : $it")
                        // hide progressbar
                        isLoading.value = false

                        // notify success
                        isSuccess.value = true
                    },
                    {
                        Log.e(TAG, it.toString())
                        isLoading.value = false
                        isError.value = it.message
                    }
                )
        )
    }

    fun delete(){
        // show progress bar
        isLoading.value = true
        compositeDisposable.add(
            studentRepository.delete(createDeleteStudentEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "Delete : $it")
                        // hide progressbar
                        isLoading.value = false

                        // notify success
                        isSuccess.value = true
                    },
                    {
                        Log.e(TAG, it.toString())
                        isLoading.value = false
                        isError.value = it.message
                    }
                )
        )
    }

    private fun getAllStudents(){
        // show progress bar
        isLoading.value = true

        compositeDisposable.add(
            studentRepository.getAllStudent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "Students : $it")
                        studentList.value = it
                        // hide progressbar
                        isLoading.value = false

                        // notify success
                        isSuccess.value = true
                    },
                    {
                        Log.e(TAG, it.toString())
                        isLoading.value = false
                        isError.value = it.message
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

    private fun createUpdateStudentEntity(): StudentEntity {
        return StudentEntity(
            id = studentId.value!!,
            studentName = studentName.value.toString(),
            age = studentAge.value!!,
            subject = studentSubject.value.toString()
        )
    }

    private fun createDeleteStudentEntity(): StudentEntity {
        return StudentEntity(
            id = studentId.value!!,
            studentName = studentName.value.toString(),
            age = studentAge.value!!,
            subject = studentSubject.value.toString()
        )
    }
}