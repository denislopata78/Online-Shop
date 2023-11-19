package src.model

class Order(
    val id: Int,
    val user: User,
    val products: List<CartItem>
) {
    var status: OrderStatus = OrderStatus.WAITING_SHIPPING
    var totalPrice: Float = 0f

    init {
        for (item in products) {
            totalPrice += item.totalPrice
        }
    }
}