package az.javidan.msproduct.controller;


import az.javidan.msproduct.model.request.ProductUpdateRequestDto;
import az.javidan.msproduct.model.request.CreateProductRequest;
import az.javidan.msproduct.model.request.PageCriteria;
import az.javidan.msproduct.model.request.ProductCriteria;
import az.javidan.msproduct.model.response.PageableResponse;
import az.javidan.msproduct.model.response.ProductResponse;
import az.javidan.msproduct.service.abstraction.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody CreateProductRequest request) {
        productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequestDto productUpdateDto) {
        productService.updateProduct(id, productUpdateDto);
    }

    @GetMapping
    public PageableResponse<ProductResponse> getAllProducts(PageCriteria pageCriteria, ProductCriteria productCriteria) {
        return productService.getAllProducts(productCriteria, pageCriteria);
    }

}
