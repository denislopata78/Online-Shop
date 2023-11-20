package src

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import src.model.*
import src.repo.OrderRepository
import java.util.Objects.equals

@SpringBootApplication
class OnlineShop
fun main(args: Array<String>) {
    runApplication<OnlineShop>(*args)
    val newProduct = Product(
        id = 0,
        name = "333",
        price = 3f,
        description = "string"
    )
    val newProductt = Product(
        id = 1,
        name = "222",
        price = 2f,
        description = "string"
    )

    DataBase.productRepository.addProduct(newProduct)
    DataBase.productRepository.addProduct(newProductt)
}