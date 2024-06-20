package service

import az.javidan.msproduct.dao.entity.ProductEntity
import az.javidan.msproduct.dao.repository.ProductRepository
import az.javidan.msproduct.exception.NotFoundException
import az.javidan.msproduct.model.enums.ProductStatus
import az.javidan.msproduct.model.request.CreateProductRequest
import az.javidan.msproduct.service.abstraction.ProductService
import az.javidan.msproduct.service.concrete.ProductServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static az.javidan.msproduct.mapper.ProductMapper.PRODUCT_MAPPER

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
        actual == expected

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

    def "TestDeleteProduct"() {

        given:
        def id = random.nextLong()
        def product = random.nextObject(ProductEntity)

        when:
        productService.deleteProduct(id)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.of(product)
        1 * productRepository.save(product)

    }

    def "TestUpdateProduct"() {
        given:
        def id = random.nextLong()
        def productUpdateRequestDto = random.nextObject(ProductUpdateRequestDto)
        def product = random.nextObject(ProductEntity)

        when:
        productService.updateProduct(id, productUpdateRequestDto)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.of(product)
        1 * productRepository.save({ ProductEntity savedProduct ->
            savedProduct.name == productUpdateRequestDto.name &&
                    savedProduct.description == productUpdateRequestDto.description &&
                    savedProduct.price == productUpdateRequestDto.price &&
                    savedProduct.subscribe == productUpdateRequestDto.subscribe
        })
    }

    def "TestUpdateProduct error product not found"() {
        given:
        def id = random.nextLong()
        def productUpdateRequestDto = random.nextObject(ProductUpdateRequestDto)

        when:
        productService.updateProduct(id, productUpdateRequestDto)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.empty()
        thrown(NotFoundException)
    }
}
