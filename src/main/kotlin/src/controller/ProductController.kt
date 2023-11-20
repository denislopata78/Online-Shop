package src.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import src.DataBase
import src.model.Product

@RestController
class ProductController {
    @PutMapping("/add/product")
    fun addProduct(@RequestBody product: Product) : ResponseEntity<String> {
        return if(DataBase.productRepository.addProduct(product)) {
            ResponseEntity.status(HttpStatus.CREATED).body("Product added")
        } else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding a product!");
    }

    @PostMapping("/update/product")
    fun updateProduct(@RequestBody product: Product) : ResponseEntity<String> {
        return if(DataBase.productRepository.updateProduct(product)) {
            ResponseEntity.status(HttpStatus.OK).body("The product has been updated")
        } else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product update error!");
    }

    @GetMapping("/get/product")
    fun getProduct(@RequestParam("id") id: Int): ResponseEntity<Product> {
        val retrievedProduct = DataBase.productRepository.getProduct(id)
        return if(DataBase.productRepository.getProduct(id) != null) {
            ResponseEntity.status(HttpStatus.OK).body(retrievedProduct)
        } else ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retrievedProduct);
    }

    @DeleteMapping("/delete/product")
    fun removeProduct(@RequestParam("id") id: Int): ResponseEntity<String> {
        return if(DataBase.productRepository.removeProduct(id)) {
            ResponseEntity.status(HttpStatus.OK).body("Product removed")
        } else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product deletion error!");
    }

    @GetMapping("/get/productList")
    fun getProductList(): ResponseEntity<MutableList<Product>> {
        return ResponseEntity.status(HttpStatus.OK).body(DataBase.productRepository.getProductList())
    }
}