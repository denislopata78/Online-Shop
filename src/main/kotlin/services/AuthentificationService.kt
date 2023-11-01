package services

import models.User
import models.UserRole
import repo.UserRepository

class AuthentificationService (val userRepository: UserRepository, var activeUser: User)
{
    fun registerUser(user: User) {
        userRepository.addUser(user)
    }
    fun authorizationUser(user: User) {
        val findUser = userRepository.getUser(user)
        if(findUser != null) {
            if(findUser.password == user.password) {
                activeUser = findUser
            }
        }
    }
    fun logoutUser() {
        userRepository.updateUser(activeUser)
        activeUser = User("guest", "", UserRole.CUSTOMER, "", "", "", "")
    }
}