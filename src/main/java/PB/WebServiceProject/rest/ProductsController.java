package PB.WebServiceProject.rest;

import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ProductsController {

    private final ProductsService productsService;

    //    @Operation(description = "Add new product with category")
//    @PostMapping("/addProductsAndCategory")
//    public ProductsDTO addProductsWithCategory(@Parameter(description = "add new products and category", example = "Nokia 3310, Smartphone")
//                                               @Valid @RequestBody ProductsDTO productsDTO) {
//        return productsService.addProductsAndCategory(productsDTO);
//    }
    @Operation(description = "Add new product")
    @PostMapping("/product")
    public ProductsDTO addProduct(@Parameter(description = "Add new product", example = "Nokia 3210")
                                  @Valid @RequestBody ProductsDTO productsDTO) {
        return productsService.addProducts(productsDTO);
    }


    @Operation(description = "Delete product by id")
    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@Parameter(description = "delete product by id", example = "1")
                                        @PathVariable(name = "id") Long id) {
        productsService.deleteProducts(id);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "edit product")
    @PutMapping("/product/{id}")
    public ProductsDTO editProduct(@Parameter(description = "edit product by id", example = "1")
                                   @PathVariable(name = "id") Long id,
                                   @Valid @RequestBody ProductsDTO productsDTO) {
        return productsService.editProducts(id, productsDTO);
    }

    @Operation(description = "Get products")
    @GetMapping("/products")
    public List<ProductsDTO> getProducts(@Parameter(description = "get products by name", example = "Nokia 3210")
                                         @RequestParam(name = "name", required = false) String name,
                                         @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                         @Parameter(description = "get products by maxPrice", example = "10000")
                                         @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        return productsService.getProducts(name, minPrice, maxPrice);
    }
//
//
//    @Operation(description = "Get all products")
//    @GetMapping("/products")
//    public List<ProductsDTO> findProductsByName(@Parameter(description = "find products by name", example = "Nokia 3210")
//                                                @RequestParam(name = "name", required = false) String name) {
//        return productsService.findProductsByName(name);
//    }
//

//
//
//    @Operation(description = "find product by id")
//    @GetMapping("/product/{id}")
//    public ResponseEntity findProductsById(@Parameter(description = "find product by id", example = "1")
//                                           @PathVariable(name = "id") Long id) {
//        Optional<ProductsDTO> productsById = productsService.findProductsById(id);
//        if (productsById.isPresent()) {
//            return ResponseEntity.ok(productsById.get());
//
//        }
//        return ResponseEntity.noContent().build();
//    }
//
}
