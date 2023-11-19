package src.repo

import src.model.Order
import src.model.OrderStatus
import src.model.User
import src.DataBase

class OrderRepository {
    fun addNewOrder(order: Order) {
        DataBase.orders.add(order)
    }
    fun getUserOrders(user: User): List<Order> {
        return DataBase.orders.filter { user.login == it.user.login };
    }

    fun changeOrderStatus(idOrder: Int, orderStatus: OrderStatus) {
        for (item in DataBase.orders) {
            if(item.id == idOrder) {
                item.status = orderStatus
            }
        }
    }
}