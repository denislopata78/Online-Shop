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
}