import models.CartItem
import models.Order
import models.Product
import models.User
import repo.AuthentificationService

object DataBase {
    val authentificationService: AuthentificationService? = null
    val users: List<User> = mutableListOf()
    val orders: List<Order> = mutableListOf()
    val products: List<Product> = mutableListOf()
}