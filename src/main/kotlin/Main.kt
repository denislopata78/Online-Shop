import models.*

fun main(args: Array<String>) {
    println("Hello World! Hello Denis! Hello Feduk")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val cartItem = CartItem(Product(1, "test", 100f))
    cartItem.quantity = 3

    val user = User("123", "123", UserRole.CUSTOMER, "123", "123", "123", "123")
    val list = mutableListOf<CartItem>()
    list.add(cartItem)

    val order = Order(1, user, list )

    for (item in order.products) {
        println(item.product.name)
    }
    println(order.totalPrice)
    //commit message: v 0.5:
    //added new models, old models were refactored, added two enums
}