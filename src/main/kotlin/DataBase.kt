import models.Order
import models.Product
import models.User
import models.UserRole
import repo.OrderRepository
import repo.ProductRepository
import repo.UserRepository
import services.AuthenticationService

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