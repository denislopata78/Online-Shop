package repo

import src.DataBase
import src.model.Product
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import src.repo.ProductRepository

class ProductRepositoryTest {

    @Test
    fun testAddProduct() {
        val productRepository = ProductRepository()
        DataBase.products.clear()

        val product = Product(1, "testName", 123f)
        productRepository.addProduct(product)
        val productFromDb = productRepository.getProduct(product.id)

        assertAll(
            { assertNotNull(productFromDb) },
            { assertEquals(productRepository.getProductList().count(), 1) },
            { assertEquals(productFromDb?.id, product.id) },
            { assertEquals(productFromDb?.name, product.name) },
            { assertEquals(productFromDb?.price, product.price) }
        )
    }

    @Test
    fun testUpdateProduct() {
        val productRepository = ProductRepository()
        DataBase.products.clear()

        val productBase = Product(1, "testName", 123f)
        val productNew = Product(1, "newTestName", 124f)
        productRepository.addProduct(productBase)
        productRepository.updateProduct(productNew)
        val productFromDb = productRepository.getProduct(productBase.id)

        assertAll(
            { assertNotNull(productFromDb) },
            { assertEquals(productRepository.getProductList().count(), 1) },
            { assertEquals(productFromDb?.id, productNew.id) },
            { assertEquals(productFromDb?.name, productNew.name) },
            { assertEquals(productFromDb?.price, productNew.price) }
        )
    }

    @Test
    fun testGetProduct() {
        val productRepository = ProductRepository()
        DataBase.products.clear()

        val product = Product(1, "testName", 123f)
        productRepository.addProduct(product)
        val productFromDb = productRepository.getProduct(product.id)

        assertAll(
            { assertNotNull(productFromDb) },
            { assertEquals(productRepository.getProductList().count(), 1) },
            { assertEquals(productFromDb?.id, product.id) },
            { assertEquals(productFromDb?.name, product.name) },
            { assertEquals(productFromDb?.price, product.price) }
        )
    }

    @Test
    fun testRemoveProduct() {
        val productRepository = ProductRepository()
        DataBase.products.clear()

        val product = Product(1, "testName", 123f)
        productRepository.addProduct(product)
        val productCountBeforeDelete = productRepository.getProductList().count()
        productRepository.removeProduct(product.id)
        val productCountAfterDelete = productRepository.getProductList().count()

        assertAll(
            { assertEquals(productCountBeforeDelete, 1) },
            { assertEquals(productCountAfterDelete, 0) },
        )
    }

    @Test
    fun testGetProductList() {
        val productRepository = ProductRepository()
        DataBase.products.clear()

        val product1 = Product(1, "testName1", 123f)
        val product2 = Product(2, "testName2", 123456f)
        productRepository.addProduct(product1)
        productRepository.addProduct(product2)
        val productListFromDb = productRepository.getProductList()

        assertAll(
            { assertNotNull(productListFromDb) },
            { assertEquals(productListFromDb.count(), 2) },
            { assertEquals(productListFromDb.find { it.id == product1.id }, product1) },
            { assertEquals(productListFromDb.find { it.id == product2.id }, product2) },
        )
    }
}