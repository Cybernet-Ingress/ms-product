package service

import az.javidan.msproduct.dao.entity.ProductEntity
import az.javidan.msproduct.dao.repository.ProductRepository
import az.javidan.msproduct.exception.NotFoundException
import az.javidan.msproduct.model.enums.ProductStatus
import az.javidan.msproduct.service.abstraction.RatingService
import az.javidan.msproduct.service.concrete.RatingServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class RatingServiceHandlerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    RatingService ratingService
    ProductRepository productRepository

    def setup() {
        productRepository = Mock()
        ratingService = new RatingServiceHandler(productRepository)
    }

    def "TestUpdateRating success"() {
        given:
        def id = random.nextLong()
        def rating = new BigDecimal("4.5")
        def product = random.nextObject(ProductEntity)
        product.setStatus(ProductStatus.DELETED)

        when:
        ratingService.updateRating(id, rating)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.of(product)
        1 * productRepository.save({ ProductEntity savedProduct ->
            savedProduct.rating == rating &&
                    savedProduct.updatedAt != null
        })
    }

    def "TestUpdateRating error product not found"() {
        given:
        def id = random.nextLong()
        def rating = new BigDecimal("4.5")

        when:
        ratingService.updateRating(id, rating)

        then:
        1 * productRepository.findByIdAndStatusNot(id, ProductStatus.DELETED) >> Optional.empty()
        thrown(NotFoundException)
    }
}
