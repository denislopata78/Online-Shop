package repo

import src.DataBase
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import src.model.*

class OrderRepositoryTest {

    @Test
    fun addNewOrder() {
        val testUser = User(
            "testUser",
            "testPsw",
            UserRole.CUSTOMER,
            "testName",
            "testSurname",
            "testLastName",
            "12345"
        )

        val authenticationService = DataBase.authenticationService
        authenticationService.registerUser(testUser)
        authenticationService.authorizationUser(testUser.login, testUser.password)

        val userRepo = DataBase.userRepository
        val cartItem1 = CartItem(Product(1, "product1", 100f, "1"))
        val cartItem2 = CartItem(Product(2, "product2", 200f, "2"))
        val cartItem3 = CartItem(Product(3, "product3", 300f, "3"))

        userRepo.addItemToCart(cartItem1)
        userRepo.addItemToCart(cartItem2)
        userRepo.addItemToCart(cartItem3)

        val newOrder = Order(1, testUser, authenticationService.activeUser.cart)
        DataBase.orderRepository.addNewOrder(newOrder)
        val addedOrder = DataBase.orders.find { it.id == 1 }

        assertAll(
            { assertNotNull(addedOrder) },
            { assertEquals(addedOrder!!.user, testUser)},
            { assertEquals(addedOrder!!.products[0], cartItem1)},
            { assertEquals(addedOrder!!.products[1], cartItem2)},
            { assertEquals(addedOrder!!.products[2], cartItem3)},
            { assertEquals(addedOrder!!.products[2], cartItem3)},
            { assertEquals(addedOrder!!.totalPrice, cartItem1.totalPrice + cartItem2.totalPrice + cartItem3.totalPrice)}
        )

        DataBase.orders.clear()
        DataBase.users.clear()
    }

    @Test
    fun getUserOrders() {
        val testUser = User(
            "testUser",
            "testPsw",
            UserRole.CUSTOMER,
            "testName",
            "testSurname",
            "testLastName",
            "12345"
        )

        val authenticationService = DataBase.authenticationService
        authenticationService.registerUser(testUser)
        authenticationService.authorizationUser(testUser.login, testUser.password)

        val userRepo = DataBase.userRepository
        val cartItem = CartItem(Product(1, "product1", 100f, "1"))

        userRepo.addItemToCart(cartItem)

        val newOrder1 = Order(1, testUser, authenticationService.activeUser.cart)
        val newOrder2 = Order(2, testUser, authenticationService.activeUser.cart)
        val newOrder3 = Order(3, testUser, authenticationService.activeUser.cart)

        DataBase.orderRepository.addNewOrder(newOrder1)
        DataBase.orderRepository.addNewOrder(newOrder2)
        DataBase.orderRepository.addNewOrder(newOrder3)

        val addedOrders = DataBase.orderRepository.getUserOrders(testUser)

        assertAll(
            { assertEquals(addedOrders[0], newOrder1)},
            { assertEquals(addedOrders[1], newOrder2)},
            { assertEquals(addedOrders[2], newOrder3)},
        )

        DataBase.orders.clear()
        DataBase.users.clear()
    }

    @Test
    fun changeOrderStatus() {
        val testUser = User(
            "testUser",
            "testPsw",
            UserRole.CUSTOMER,
            "testName",
            "testSurname",
            "testLastName",
            "12345"
        )

        val authenticationService = DataBase.authenticationService
        authenticationService.registerUser(testUser)
        authenticationService.authorizationUser(testUser.login, testUser.password)

        val userRepo = DataBase.userRepository
        val cartItem = CartItem(Product(1, "product1", 100f, "1"))

        userRepo.addItemToCart(cartItem)

        DataBase.orderRepository.addNewOrder(Order(1, testUser, authenticationService.activeUser.cart))

        DataBase.orderRepository.changeOrderStatus(1, OrderStatus.SHIPPING)

        val addedOrder = DataBase.orders.find { it.id == 1 }

        assertEquals(addedOrder?.status, OrderStatus.SHIPPING)

        DataBase.orders.clear()
        DataBase.users.clear()
    }
}