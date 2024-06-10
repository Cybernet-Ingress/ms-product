package service

import az.javidan.ms_product.dao.entity.ProductEntity
import az.javidan.ms_product.dao.repository.ProductRepository
import az.javidan.ms_product.exception.NotFoundException
import az.javidan.ms_product.model.enums.ProductStatus
import az.javidan.ms_product.model.request.CreateProductRequest
import az.javidan.ms_product.service.abstraction.ProductService
import az.javidan.ms_product.service.concrete.ProductServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification
import static az.javidan.ms_product.mapper.ProductMapper.PRODUCT_MAPPER

class ProductServiceHandlerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    ProductService productService

    ProductRepository productRepository

    def setup() {
        productRepository = Mock()
        productService = new ProductServiceHandler(productRepository)
    }

    def "TestCreateProduct"() {

        given:
        def request = random.nextObject(CreateProductRequest)
        def entity = PRODUCT_MAPPER.buildProductEntity(request)

        when:
        productService.createProduct(request)

        then:
        1 * productRepository.save(entity)
    }

    def "TestGetProduct success"() {

        given:
        def id = random.nextLong()
        def entity = random.nextObject(ProductEntity)
        def expected = PRODUCT_MAPPER.buildProductEntity(request)

        when:
        def actual = productService.getProduct(id)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.of(entity)
        actual.id == expected.id
        actual.name == expected.name
        actual.description == expected.description
        actual.price == expected.price
        actual.rating == expected.rating

    }

    def "TestGetProduct error product not found"() {
        given:
        def id = random.nextLong()

        when:
        productService.getProduct(id)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.empty()
        thrown(NotFoundException)
    }
}
