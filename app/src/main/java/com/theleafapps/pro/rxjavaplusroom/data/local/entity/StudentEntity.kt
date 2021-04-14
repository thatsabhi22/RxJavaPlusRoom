package com.theleafapps.pro.rxjavaplusroom.data.local.entity

import androidx.room.Entity

@Entity(tableName = "students")
data class StudentEntity (
        @PrimaryKey(autoGenerate = true)
        val id:Long = 0L,
        @ColumnInfo(name="student_name")
        val studentName:String,
        @ColumnInfo(name="age")
        val age:Int,
        @ColumnInfo(name="subject")
        val subject:String
)
