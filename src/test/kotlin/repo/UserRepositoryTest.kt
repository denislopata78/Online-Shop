package repo

import src.DataBase.user
import src.DataBase.userRepository
import src.DataBase.users
import src.model.CartItem
import src.model.Product
import src.model.User
import src.model.UserRole
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class UserRepositoryTest {
    @Test
    fun testAddItemToCart() {
        val product = Product(1, "sds", 12.0f)
        val cartItem = CartItem(product, 2)
        val cart = user.cart
        userRepository.addItemToCart(cartItem)

        assertAll(
            { assert(cart.size == 1) },
            { assert(cart[0] == cartItem) },
            { assertEquals(cart[0].quantity, cartItem.quantity) },
            { assertEquals(cart[0].totalPrice, cartItem.totalPrice) },
            { assertEquals(cart[0].product, cartItem.product) }
        )
    }

    @Test
    fun testRemoveItemFromCart() {
        val product = Product(1, "sds", 12.0f)
        val product1 = Product(2, "sds", 12.0f)
        val cartItem1 = CartItem(product, 2)
        val cartItem2 = CartItem(product1, 3)

        userRepository.addItemToCart(cartItem1)
        userRepository.addItemToCart(cartItem2)
        userRepository.removeItemFromCart(cartItem1)

        assertAll(
            { assert(user.cart.size == 1) },
            { assertEquals(user.cart[0], cartItem2) }
        )
    }

    @Test
    fun testUpdateCartItems() {
        val product = Product(1, "sds", 12.0f)
        val cartItem1 = CartItem(product, 2)
        val cartItem2 = CartItem(product, 3)
        userRepository.addItemToCart(cartItem1)
        userRepository.updateCartItems(cartItem2)
        assertEquals(user.cart[0].quantity, 3)
    }

    @Test
    fun testAddUser() {
        var usr = User("first","123", UserRole.CUSTOMER, "", "", "", "")
        userRepository.addUser(usr)
        assertAll(
            { assertEquals(users.size, 1) },
            { assertEquals(users[0], usr) }
        )

        users.clear()
    }

    @Test
    fun testRemoveUser() {
        users.clear()

        var usr = User("first","123", UserRole.CUSTOMER, "", "", "", "")
        userRepository.addUser(usr)
        userRepository.removeUser(usr)
        assertAll(
            { assertEquals(0, users.count()) },
        )
    }

    @Test
    fun testUpdateUser() {
        val usr = User("first","123", UserRole.CUSTOMER, "", "", "", "")
        userRepository.addUser(usr)
        var usr1 = User("first","132", UserRole.CUSTOMER, "32", "", "", "")
        userRepository.updateUser(usr1)
        assertEquals(users[0].name, "32")

        users.clear()
    }

    @Test
    fun testGetUser() {
        var usr = User("first","123", UserRole.CUSTOMER, "", "", "", "")
        var usr1 = User("second","123", UserRole.CUSTOMER, "", "", "", "")
        userRepository.addUser(usr)
        userRepository.addUser(usr1)
        assertEquals(userRepository.getUser(usr), usr)
    }
}