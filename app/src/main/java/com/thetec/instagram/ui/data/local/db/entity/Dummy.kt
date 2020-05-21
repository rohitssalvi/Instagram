package com.thetec.instagram.ui.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Dummy(@PrimaryKey(autoGenerate = true) val id:Int, val name:String)