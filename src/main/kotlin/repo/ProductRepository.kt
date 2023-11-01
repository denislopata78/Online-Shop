package repo

import models.Product
class ProductRepository {
    fun addProduct(product: Product) {
        DataBase.products.add(product)
    }
    fun updateProduct(product: Product) {
        for(item in DataBase.products) {
            if(item.id == product.id) {
                item.name = product.name
                item.description = product.description
                item.price = product.price
            }
        }
    }
    fun getProduct(id: Int) {
        DataBase.products.find { it.id == id }
    }
    fun removeProduct(id: Int) {
        DataBase.products.removeIf { it.id == id }
    }
    fun getProductList(): MutableList<Product> {
        return DataBase.products
    }
}
