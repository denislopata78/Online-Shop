package services

import src.model.UserRole
import src.DataBase
import src.model.User
import src.repo.UserRepository

class AuthenticationService
{
    fun registerUser(user: User) {
        DataBase.userRepository.addUser(user)
    }
    fun authorizationUser(login: String, password: String): Boolean {
        val findUser = DataBase.users.find { it.login == login }
        if(findUser != null) {
            if(findUser.password == password) {
                return true
            }
        }
        return false
    }
}