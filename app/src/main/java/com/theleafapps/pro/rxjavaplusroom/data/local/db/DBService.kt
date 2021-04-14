package com.theleafapps.pro.rxjavaplusroom.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.theleafapps.pro.rxjavaplusroom.data.local.dao.StudentDao
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity

@Database(entities = [StudentEntity::class],version = 1, exportSchema = false)
abstract class DBService : RoomDatabase(){

    abstract fun studentDao() : StudentDao

    companion object {

        @Volatile
        private var INSTANCE: DBService? = null

        fun getInstance(context:Context): DBService {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DBService::class.java,
                        "student_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}