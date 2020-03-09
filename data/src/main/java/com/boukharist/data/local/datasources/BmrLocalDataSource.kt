package com.boukharist.data.local.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boukharist.data.local.models.BmrDto
import com.boukharist.data.local.models.UserDto

@Dao
interface BmrLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(bmrDto: BmrDto)

    @Query("SELECT * FROM bmr limit 1")
    fun getBmrById(bmrRequestHash: Int): BmrDto?
}