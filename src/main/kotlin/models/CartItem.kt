package models

class CartItem(val product: Product, quantity: Int = 1) {
    var quantity: Int = quantity
        set(value) {
            field = value
            totalPrice = field * this.product.price
        }

    var totalPrice: Float = quantity * this.product.price
}