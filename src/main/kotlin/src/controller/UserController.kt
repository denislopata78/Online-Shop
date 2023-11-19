package src.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import src.DataBase
import src.model.User
import src.model.requestModel.UserCartItem

@RestController
class UserController {
    @PostMapping("/")
    fun loginUser(@RequestBody user: User): ResponseEntity<String> {
        return if (DataBase.authenticationService.authorizationUser(user.login, user.password)) {
            ResponseEntity.ok("User authorized")
        } else ResponseEntity.status(401).body("Ошибка аутентификации: неверные учетные данные");
    }

    @PostMapping("/registration")
    fun registerUser(@RequestBody user: User): ResponseEntity<String> {
        return if (DataBase.authenticationService.registerUser(user)) {
            ResponseEntity.ok("User registered")
        } else ResponseEntity.status(401).body("Ошибка регистрации");
    }

    @PutMapping("/add/itemToCart")
    fun addItemToCart(@RequestBody userCartItem: UserCartItem): ResponseEntity<String> {
        return if(DataBase.userRepository.addItemToCart(userCartItem.cartItem, userCartItem.user)) {
            ResponseEntity.ok("All good")
        } else
            ResponseEntity.status(401).body("Ошибка добавления");
    }

    @DeleteMapping("/add/itemToCart")
    fun deleteItemToCart(@RequestBody userCartItem: UserCartItem): ResponseEntity<String> {
        return if(DataBase.userRepository.removeItemFromCart(userCartItem.cartItem, userCartItem.user)) {
            ResponseEntity.ok("All good")
        } else
            ResponseEntity.status(401).body("Ошибка удаления");
    }

    @PostMapping("/add/itemToCart")
    fun changeItemToCart(@RequestBody userCartItem: UserCartItem): ResponseEntity<String> {
        return if(DataBase.userRepository.updateCartItems(userCartItem.cartItem, userCartItem.user)) {
            ResponseEntity.ok("All good")
        } else
            ResponseEntity.status(401).body("Ошибка обновления");
    }
}