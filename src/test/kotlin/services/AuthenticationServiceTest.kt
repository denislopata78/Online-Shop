package services

import DataBase
import models.User
import models.UserRole
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import repo.UserRepository

class AuthenticationServiceTest {

    @Test
    fun registerUser() {
        /*val userRepo = UserRepository()
        val testUser = User(
            "testUser",
            "testPsw",
            UserRole.CUSTOMER,
            "testName",
            "testSurname",
            "testLastName",
            "12345"
        )
        val authenticationService = AuthenticationService(userRepo, testUser)

        authenticationService.registerUser(newUser)*/
        val newUser = User(
            "newUser",
            "newPsw",
            UserRole.CUSTOMER,
            "newUser",
            "newUser",
            "newUser",
            "newUser"
        )

        val registeredUser = DataBase.users.find { it.login == "newUser" }

        assertAll("Register new user",
            { assertNotNull(registeredUser) },
            { assertEquals(registeredUser?.login, "newUser")},
            { assertEquals(registeredUser?.password, "newPsw")},
            { assertEquals(registeredUser?.role, UserRole.CUSTOMER)},
            { assertEquals(registeredUser?.name, "newUser")},
            { assertEquals(registeredUser?.surname, "newUser")},
            { assertEquals(registeredUser?.lastName, "newUser")},
            { assertEquals(registeredUser?.phoneNumber, "newUser")},
        )
    }

    @Test
    fun authorizationUser() {
        val guest = User("guest", "", UserRole.CUSTOMER, "", "", "", "")
        val newUser = User(
            "newUser",
            "newPsw",
            UserRole.CUSTOMER,
            "newUser",
            "newUser",
            "newUser",
            "newUser"
        )
        val userRepo = UserRepository()

        val authenticationService = AuthenticationService(userRepo, guest)
        authenticationService.registerUser(newUser)

        /*assertAll("User authorization",
            { assertTrue(authenticationService.authorizationUser())}
        )*/
    }

    @Test
    fun logoutUser() {
    }
}