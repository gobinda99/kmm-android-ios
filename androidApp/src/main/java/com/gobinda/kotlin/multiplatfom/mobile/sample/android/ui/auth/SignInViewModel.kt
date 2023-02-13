package com.gobinda.kotlin.multiplatfom.mobile.sample.android.ui.auth

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.R
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.utils.TripleState
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.utils.isValidEmail
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.utils.isValidPassword
import com.gobinda.kotlin.multiplatfom.mobile.sample.android.utils.store
import com.gobinda.kotlin.multiplatfom.mobile.sample.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.definition.indexKey

data class SignInState(
    val email: TripleState<String> = TripleState(""),
    val pass: TripleState<String> = TripleState(""),
    val loading: Boolean = false,
    val success: Boolean = false,
    val message: String? = null,
)

class SignInViewModel constructor(
    private val repository: UserRepository,
    private val pref : SharedPreferences,
    savedStateHandle: SavedStateHandle,
    private val resorces : Resources
) : ViewModel(), KoinComponent {


    private var _uiState = MutableStateFlow(SignInState())

    val uiState: StateFlow<SignInState>
        get() = _uiState

    fun validate() {
        viewModelScope.launch {
            var newState = uiState.value
            with(uiState.value) {
                if (email.value.isBlank()) {
                    newState =
                        copy(email = email.copy(isError = true, message = R.string.email_empty))
                } else if (!email.value.isValidEmail) {
                    newState =
                        copy(email = email.copy(isError = true, message = R.string.email_incorrect))
                }
                if (pass.value.isBlank()) {
                    newState =
                        newState.copy(
                            pass = pass.copy(
                                isError = true,
                                message = R.string.password_empty
                            )
                        )
                } else if (!pass.value.isValidPassword) {
                    newState =
                        newState.copy(
                            pass = pass.copy(
                                isError = true,
                                message = R.string.password_should
                            )
                        )
                }
            }
            _uiState.value = newState
            if (!(newState.email.isError || newState.pass.isError)) {
                newState = newState.copy(loading = true)
                _uiState.value = newState
                /*viewModelScope.launch {
                    delay(5000)
                    newState = newState.copy(loading = false, success = true)
                    _uiState.value = newState

                }*/

                repository.getUser(_uiState.value.email.value)
                    .map {
                        if (it.getOrNull() != null) {
                            newState = newState.copy(loading = false, success = true)
                            _uiState.value = newState
                            pref.store("logIn", true)
                        } else {
                            newState = newState.copy(
                                loading = false,
                                success = false,
                                message = resorces.getString(R.string.not_record_found)
                            )
                            _uiState.value = newState
                        }

                    }
                    .stateIn(
                        scope = viewModelScope,
                    )
            }
        }

    }

    fun setEmail(_email: String) {
        with(_uiState.value) {
            _uiState.value = copy(email = email.copy(_email, isError = false), message = null)
        }
    }

    fun setPass(_pass: String) {
        with(_uiState.value) {
            _uiState.value = copy(pass = pass.copy(_pass, isError = false), message = null)
        }
    }

    fun anyUserLoggedIn() : Boolean = pref.getBoolean("logIn", false)

    fun logout()  = pref.store("logIn", false)
}