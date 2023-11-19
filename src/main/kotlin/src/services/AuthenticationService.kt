package services

import src.model.UserRole
import src.DataBase
import src.model.User
import src.repo.UserRepository

class AuthenticationService (val userRepository: UserRepository, var activeUser: User)
{
    fun registerUser(user: User) {
        userRepository.addUser(user)
    }
    fun authorizationUser(login: String, password: String): Boolean {
        val findUser = DataBase.users.find { it.login == login }
        if(findUser != null) {
            if(findUser.password == password) {
                activeUser = findUser
                return true
            }
        }
        return false
    }
    fun logoutUser() {
        userRepository.updateUser(activeUser)
        activeUser = User("guest", "", UserRole.CUSTOMER, "", "", "", "")
    }
}