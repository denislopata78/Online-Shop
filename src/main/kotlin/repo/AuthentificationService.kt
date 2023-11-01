package repo

import models.Order
import models.Product
import models.User

class AuthentificationService (val userRepository: UserRepository, var activeUser: User? = null)
{
    fun registerUser(user: User) {
    }
    fun authorizationUser(user: User) {
    }
    fun logoutUser() {
    }
}