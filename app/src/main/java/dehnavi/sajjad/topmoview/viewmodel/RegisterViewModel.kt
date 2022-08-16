package dehnavi.sajjad.topmoview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.topmoview.model.register.BodyRegister
import dehnavi.sajjad.topmoview.model.register.ResponseRegister
import dehnavi.sajjad.topmoview.repository.RegisterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel() {

    val registerUser = MutableLiveData<ResponseRegister>()
    val loading = MutableLiveData<Boolean>()

    fun registerUser(bodyRegister: BodyRegister) = viewModelScope.launch {
        loading.postValue(true)
        val responseRegister = repository.registerUser(bodyRegister)
        if (responseRegister.isSuccessful) {
            registerUser.postValue(responseRegister.body())
        }
        loading.postValue(false);
    }
}