package src.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class HealthCheckController {
    @GetMapping("/")
    fun index(@RequestParam("name") name: String) = "Hello, $name!"

    @GetMapping("/{name}")
    fun getExample(@PathVariable("name") name: String): String {
        // Ваша логика обработки данных и формирования ответа
        val responseBody = "Привет, $name!"

        // Возвращаем ResponseEntity с телом ответа и статусом OK
        return responseBody
    }
}