package com.gobinda.kotlin.multiplatfom.mobile.sample.data
import com.gobinda.kotlin.multiplatfom.mobile.sample.db.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    fun getUser(email : String) : Flow<Result<User>>

    suspend fun insertUser(user : User)

    suspend fun updateUser(user: User)

}