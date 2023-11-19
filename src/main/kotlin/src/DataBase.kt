package src

import services.AuthenticationService
import src.model.Order
import src.model.Product
import src.model.User
import src.model.UserRole
import src.repo.OrderRepository
import src.repo.ProductRepository
import src.repo.UserRepository

object DataBase {
    val userRepository = UserRepository()
    val orderRepository = OrderRepository()
    val productRepository = ProductRepository()
    val user = User("guest", "", UserRole.CUSTOMER, "", "", "", "")
    val authenticationService: AuthenticationService = AuthenticationService(userRepository, user)
    var users: MutableList<User> = mutableListOf()
    var orders: MutableList<Order> = mutableListOf()
    var products: MutableList<Product> = mutableListOf()
}