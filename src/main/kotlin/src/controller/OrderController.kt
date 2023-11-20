package src.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import src.DataBase
import src.model.Order
import src.model.OrderStatus

@RestController
class OrderController {
    data class UpdateOrderStatusDto (val orderID: Int, val orderStatus: OrderStatus)

    @PostMapping("/add/newOrder")
    fun addNewOrder(@RequestBody order: Order): ResponseEntity<String> {
        return if (DataBase.orderRepository.addNewOrder(order))
            ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully")
        else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order data error");
    }

    @GetMapping("/get/userOrders")
    fun getUserOrders(@RequestParam("userLogin") userLogin: String): ResponseEntity<List<Order>> =
        ResponseEntity.status(HttpStatus.OK).body(DataBase.orderRepository.getUserOrders(userLogin))

    @PostMapping("/update/orderStatus")
    fun updateOrderStatus(@RequestBody orderStatusDto: UpdateOrderStatusDto): ResponseEntity<String> {
        return if (DataBase.orderRepository.updateOrderStatus(orderStatusDto.orderID, orderStatusDto.orderStatus))
            ResponseEntity.status(HttpStatus.OK).body("Order status updated successfully")
        else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order id error");
    }
}