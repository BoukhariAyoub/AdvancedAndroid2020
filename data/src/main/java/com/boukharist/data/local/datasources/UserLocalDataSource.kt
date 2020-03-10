package com.boukharist.data.local.datasources

import androidx.room.*
import com.boukharist.data.local.models.UserDto
import com.boukharist.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserLocalDataSource {

    @Transaction
    fun setLoggedInUser(user: UserDto) {
        deleteAll()
        save(user)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: UserDto)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * FROM users limit 1")
    fun findCurrentUser(): Flow<UserDto?>

}
