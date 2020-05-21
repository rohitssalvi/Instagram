package com.thetec.instagram.ui.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thetec.instagram.ui.data.local.db.entity.Dummy

@Database(entities = [Dummy::class],exportSchema = false,version = 1)
abstract class DatabaseService : RoomDatabase() {

}