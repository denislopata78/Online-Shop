package services

import src.DataBase
import src.model.User
import src.model.UserRole
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assumptions.*

class AuthenticationServiceTest {

    @Test
    fun registerUser() {
        val newUser = User(
            "newUser",
            "newPsw",
            UserRole.CUSTOMER,
            "newUser",
            "newUser",
            "newUser",
            "newUser"
        )

        DataBase.authenticationService.registerUser(newUser)
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
    fun authorizationUserCorrectLoginData() {
        val newUser = User(
            "newUser",
            "newPsw",
            UserRole.CUSTOMER,
            "newUser",
            "newUser",
            "newUser",
            "newUser"
        )
        DataBase.authenticationService.registerUser(newUser)

        assertAll(
            { assertTrue(DataBase.authenticationService.authorizationUser(newUser.login, newUser.password))},
            { assertFalse(DataBase.authenticationService.authorizationUser("Invalid", "Invalid")) }
        )
    }

    @Test
    fun logoutUser() {
        val newUser = User(
            "newUser",
            "newPsw",
            UserRole.CUSTOMER,
            "newUser",
            "newUser",
            "newUser",
            "newUser"
        )

        DataBase.authenticationService.registerUser(newUser)
        DataBase.authenticationService.authorizationUser(newUser.login, newUser.password)
        assumeTrue(DataBase.authenticationService.activeUser.login == newUser.login)

        DataBase.authenticationService.logoutUser()

        assertTrue(DataBase.authenticationService.activeUser.login == "guest")
    }
}