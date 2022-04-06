package PB.WebServiceProject.rest;

import PB.WebServiceProject.rest.dto.ProductCategoryDTO;
import PB.WebServiceProject.services.ProductCategoryService;
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
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Operation(description = "Add new category")
    @PostMapping("/category")
    public ProductCategoryDTO addCategory(@Parameter(description = "add new category", example = "RTV")
                                          @Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.addCategory(productCategoryDTO);
    }

    @Operation(description = "Edit category by id")
    @PutMapping("/category/{id}")
    public ProductCategoryDTO editCategory(@Parameter(description = "select category id", example = "1")
                                           @PathVariable(name = "id") Long id,
                                           @Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.editCategory(id, productCategoryDTO);
    }

    @Operation(description = "Delete category by id")
    @DeleteMapping("/category/{id}")
    public ResponseEntity deleteCategory(@Parameter(description = "select category id", example = "1")
                                         @PathVariable(name = "id") Long id) {
        productCategoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

//    @Operation(description = "Find category by id")
//    @GetMapping("/category/{id}")
//    public ResponseEntity findCategoryById(@Parameter(description = "find category by id", example = "1")
//                                           @PathVariable(name = "id") Long id) {
//        Optional<ProductCategoryDTO> productCategoryById = productCategoryService.findProductCategoryById(id);
//        if (productCategoryById.isPresent()) {
//            return ResponseEntity.ok(productCategoryById.get());
//
//        }
//        return ResponseEntity.noContent().build();
//    }

    @Operation(description = "Get category by name")
    @GetMapping("/categories")
    public List<ProductCategoryDTO> findCategoryByName(@Parameter(description = "find category by name", example = "RTV")
                                                       @RequestParam(name = "name", required = false) String name) {
        return productCategoryService.findCategory(name);
    }

}
