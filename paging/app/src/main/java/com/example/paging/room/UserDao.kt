package com.example.paging.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM User ORDER BY name COLLATE NOCASE ASC")
    fun allUserByName():PagingSource<Int,User>
    @Insert
    fun insert(user:List<User>)
    @Insert
    fun insert(user:User)
    @Delete
    fun remove(user:User)
}