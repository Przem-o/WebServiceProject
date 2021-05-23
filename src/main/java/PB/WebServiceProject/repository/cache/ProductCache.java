package PB.WebServiceProject.repository.cache;

import PB.WebServiceProject.cacheConfig.CacheConfig;
import PB.WebServiceProject.rest.dto.ProductsDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductCache {

    @CachePut(key = "#productsDTO.id", cacheManager = CacheConfig.PRODUCTS_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.PRODUCTS_RESPONSE_CACHE_NAME)
    public ProductsDTO saveProductsResponseInCache(ProductsDTO productsDTO) {
        return productsDTO;
    }
    @Cacheable(key = "#id", cacheManager = CacheConfig.PRODUCTS_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.PRODUCTS_RESPONSE_CACHE_NAME)
    public Optional<ProductsDTO> getProductsResponse(Long id) {
        return Optional.empty();
    }
    @CacheEvict(key = "#id", cacheManager = CacheConfig.PRODUCTS_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.PRODUCTS_RESPONSE_CACHE_NAME)
    public void deleteProductsResponseFromCache(Long id) {
    }
}
