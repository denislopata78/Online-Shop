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
        val responseBody = "Привет, $name!"

        return responseBody
    }
}