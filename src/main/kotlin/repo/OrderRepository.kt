package repo

import models.Order
import models.OrderStatus
import models.User

class OrderRepository {
    fun addNewOrder(order: Order) {
        DataBase.orders.add(order)
    }
    fun getUserOrders(user: User): List<Order> {
        val filteredOrders = DataBase.orders.filter { user.login == it.user.login }
        return filteredOrders;
    }

    fun changeOrderStatus(idOrder: Int, orderStatus: OrderStatus) {
        for (item in DataBase.orders) {
            if(item.id == idOrder) {
                item.status = orderStatus
            }
        }
    }
}