package src.repo

import src.DataBase
import src.model.CartItem
import src.model.User

class UserRepository {
    fun addItemToCart(cartItem: CartItem, userLogin: String): Boolean {
        val currentUser = getUser(userLogin)
        return if (currentUser != null) {
            currentUser.cart.add(cartItem)
            true
        } else
            false
    }
    fun removeItemFromCart(cartItem: CartItem, userLogin: String): Boolean {
        val currentUser = getUser(userLogin)
        return if (currentUser != null) {
            currentUser.cart.removeIf { it.product.id == cartItem.product.id }
            true
        } else
            false
    }
    fun updateCartItems(cartItem: CartItem, userLogin: String): Boolean {
        val currentUser = getUser(userLogin)
        return if (currentUser != null) {
            for (item in currentUser.cart) {
                if (item.product.id == cartItem.product.id) {
                    item.totalPrice = cartItem.totalPrice
                    item.quantity = cartItem.quantity
                }
            }
            true
        } else
            false
    }

    fun addUser(user: User) : Boolean {
        DataBase.users.add(user)
        return true
    }
    fun removeUser(userLogin: String) {
        DataBase.users.removeIf { it.login == userLogin }
    }

    fun updateUser(user: User) {
        for(item in DataBase.users) {
            if(item.login == user.login) {
                item.name = user.name
                item.surname = user.surname
                item.lastName = user.name
                item.phoneNumber = user.phoneNumber
                item.role = user.role
                item.cart = user.cart //toMutableList()
            }
        }
    }

    fun getUser(userLogin: String): User? {
        return DataBase.users.find { it.login == userLogin }
    }
}

