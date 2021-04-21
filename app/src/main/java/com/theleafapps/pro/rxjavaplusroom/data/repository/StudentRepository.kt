package com.theleafapps.pro.rxjavaplusroom.data.repository

import com.theleafapps.pro.rxjavaplusroom.data.local.db.DBService
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity

class StudentRepository(private val dbService: DBService) {

    fun insert(studentEntity: StudentEntity) = dbService.studentDao().insert(studentEntity)

    fun update(studentEntity: StudentEntity) = dbService.studentDao().update(studentEntity)

    fun delete(studentEntity: StudentEntity) = dbService.studentDao().delete(studentEntity)

    fun getAllStudent() = dbService.studentDao().getAllStudents()

    fun searchStudentByName(name: String) = dbService.studentDao().searchStudentByName(name)
}