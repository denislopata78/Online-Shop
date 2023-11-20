package src.repo

import src.model.Order
import src.model.OrderStatus
import src.model.User
import src.DataBase
import javax.swing.text.StyledEditorKit.BoldAction

class OrderRepository {
    fun addNewOrder(order: Order):Boolean {
        return if (DataBase.orders.isNotEmpty()) {
            val newOrder = Order(DataBase.orders.maxOf { it.id } + 1, order.user, order.products)
            DataBase.orders.add(newOrder)
        } else
            DataBase.orders.add(order)
    }

    fun getUserOrders(userLogin: String): List<Order> {
        return DataBase.orders.filter { it.user.login == userLogin};
    }

    fun updateOrderStatus(idOrder: Int, orderStatus: OrderStatus):Boolean {
        for (item in DataBase.orders) {
            if(item.id == idOrder) {
                item.status = orderStatus
                return true
            }
        }
        return false
    }
}