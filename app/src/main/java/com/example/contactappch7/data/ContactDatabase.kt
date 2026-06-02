package com.example.contactappch7.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class],version = 1, exportSchema = false)

abstract class ContactDatabase: RoomDatabase(){

    abstract fun contactDao(): ContactDao

    companion object{
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            return INSTANCE ?: synchronized(lock = this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    klass = ContactDatabase::class.java,
                    name = "contact_database"
                ).build()
                INSTANCE = instance

                instance
            }

        }
    }
}