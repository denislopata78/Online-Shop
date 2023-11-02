package repo

import DataBase
import models.CartItem
import models.User

class UserRepository {
    fun addItemToCart(cartItem: CartItem) {
        DataBase.authenticationService.activeUser.cart.add(cartItem)
    }
    fun removeItemFromCart(cartItem: CartItem) {
        DataBase.authenticationService.activeUser.cart.removeIf { it.product.id == cartItem.product.id }
    }
    fun updateCartItems(cartItem: CartItem) {
        for (item in DataBase.authenticationService.activeUser.cart) {
            if (item.product.id == cartItem.product.id) {
                item.totalPrice = cartItem.totalPrice
                item.quantity = cartItem.quantity
            }
        }
    }
    fun addUser(user: User) {
        DataBase.users.add(user)
    }
    fun removeUser(user: User) {
        DataBase.users.removeIf { it.login == user.login }
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

    fun getUser(user: User): User? {
        return DataBase.users.find { it.login == user.login }
    }
}

