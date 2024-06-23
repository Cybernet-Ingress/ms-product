package service

import az.javidan.msproduct.dao.entity.ProductEntity
import az.javidan.msproduct.dao.repository.ProductRepository
import az.javidan.msproduct.exception.NotFoundException
import az.javidan.msproduct.model.enums.ProductStatus
import az.javidan.msproduct.service.abstraction.SubscribeService
import az.javidan.msproduct.service.concrete.SubscribeServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class SubscribeServiceHandlerTest extends Specification{

    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    SubscribeService subscribeService
    ProductRepository productRepository

    def setup() {
        productRepository = Mock()
        subscribeService = new SubscribeServiceHandler(productRepository)
    }

    def "TestUpdateSubscribe success"() {
        given:
        def id = random.nextLong()
        def subscribe = random.nextBoolean()
        def product = random.nextObject(ProductEntity)
        product.setStatus(ProductStatus.DELETED)

        when:
        subscribeService.updateSubscribe(id, subscribe)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.of(product)
        1 * productRepository.save({ ProductEntity savedProduct ->
            savedProduct.subscribe == subscribe &&
                    savedProduct.updatedAt != null
        })
    }

    def "TestUpdateSubscribe error product not found"() {
        given:
        def id = random.nextLong()
        def subscribe = random.nextBoolean()

        when:
        subscribeService.updateSubscribe(id, subscribe)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.empty()
        thrown(NotFoundException)
    }
}
