package com.theleafapps.pro.rxjavaplusroom.data.local.dao

import androidx.room.*
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface StudentDao {

    @Insert
    fun insert(studentEntity: StudentEntity): Single<Long>

    @Update
    fun update(studentEntity: StudentEntity): Single<Int>

    @Delete
    fun delete(studentEntity: StudentEntity): Single<Int>

    @Query("SELECT * FROM students ORDER BY id DESC")
    fun getAllStudents():Observable<List<StudentEntity>>
}