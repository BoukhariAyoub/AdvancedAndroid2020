package com.boukharist.data.local.datasources

import androidx.room.*
import com.boukharist.data.local.models.UserDto
import com.boukharist.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserLocalDataSource {

    @Transaction
    open suspend fun setLoggedInUser(user: UserDto) {
        deleteAll()
        save(user)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun save(user: UserDto)

    @Query("DELETE FROM users")
    abstract fun deleteAll()

    @Query("SELECT * FROM users")
    abstract fun findCurrentUser(): Flow<List<UserDto>>
}
