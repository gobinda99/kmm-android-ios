package com.gobinda.kotlin.multiplatfom.mobile.sample.data.source.local.sql

import com.gobinda.kotlin.multiplatfom.mobile.sample.data.UserDataSource
import com.gobinda.kotlin.multiplatfom.mobile.sample.db.SampleDb
import com.gobinda.kotlin.multiplatfom.mobile.sample.db.User
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserLocalDataSource constructor(
    db: SampleDb,
    val dispatcher: CoroutineDispatcher = Dispatchers.Default
) :
    UserDataSource {

    val dao = db.userQueries

    override fun getUser(email: String): Flow<Result<User>> {
        return dao.selectByEmail(email).asFlow()
            .map {
            val user = it.executeAsOneOrNull()
            if(user != null)  {
                Result.success(user)
            } else {
                Result.failure(Exception())
            }
        }.flowOn(dispatcher)

    }

    override suspend fun insertUser(user: User) {
        /* withContext(dispatcher) {
            with(user) {
                dao.insertUser(email, name, password)
            }
        }*/
        dao.transactionWithContext(dispatcher){
            with(user){
                dao.insertUser(email, name, password)
            }
        }
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

}