import models.*
import repo.OrderRepository

fun main(args: Array<String>) {
    println("Hello World! Hello Denis! Hello Feduk")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val cartItem = CartItem(Product(1, "test", 100f))
    cartItem.quantity = 3

    val user = User("123", "123", UserRole.CUSTOMER, "123", "123", "123", "123")
    val user1 = User("222", "123", UserRole.CUSTOMER, "123", "123", "123", "123")
    val list = mutableListOf<CartItem>()
    list.add(cartItem)

    val order = Order(1, user, list )
    val order1 = Order(2, user1, list )
    val order2 = Order(3, user, list )

    val OrderRepository = OrderRepository()

    DataBase.orders.add(order)
    DataBase.orders.add(order1)
    DataBase.orders.add(order2)

    for(item in OrderRepository.getUserOrders(user)) {
        println(item.id)
    }

    for (item in order.products) {
        println(item.product.name)
    }

    println(order.totalPrice)
    //commit message: v 0.6:
    //logic for repositories and auth service was written
}