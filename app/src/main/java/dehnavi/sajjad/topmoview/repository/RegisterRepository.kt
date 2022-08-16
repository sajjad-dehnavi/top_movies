package dehnavi.sajjad.topmoview.repository

import dehnavi.sajjad.topmoview.api.ApiService
import dehnavi.sajjad.topmoview.model.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun registerUser(bodyRegister: BodyRegister) = apiService.registerUser(bodyRegister)
}