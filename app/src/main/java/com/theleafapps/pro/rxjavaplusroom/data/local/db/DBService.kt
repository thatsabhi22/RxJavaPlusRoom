package com.theleafapps.pro.rxjavaplusroom.data.local.db

import androidx.room.Database
import com.theleafapps.pro.rxjavaplusroom.data.local.dao.StudentDao
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity

@Database(entities = [StudentEntity::class],version = 1, exportSchema = false)
abstract class DBService {

    abstract fun studentDao() : StudentDao
}