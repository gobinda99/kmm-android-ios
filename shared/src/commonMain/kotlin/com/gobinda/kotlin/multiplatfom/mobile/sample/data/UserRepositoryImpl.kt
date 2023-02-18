package com.gobinda.kotlin.multiplatfom.mobile.sample.data

import com.gobinda.kotlin.multiplatfom.mobile.sample.db.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
     val localData: UserDataSource,
     val remoteData: UserDataSource,
    val  dispatcher: CoroutineDispatcher = Dispatchers.Default
) : UserRepository {

    override fun getUser(email : String): Flow<Result<User>> {
        return localData.getUser(email)
    }

    override suspend fun insertUser(user: User)  {
       return localData.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}