package PB.WebServiceProject.services;


import PB.WebServiceProject.entities.ProductCategoryEntity;
import PB.WebServiceProject.entities.ProductsEntity;
import PB.WebServiceProject.repository.ProductCategoryRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.repository.cache.ProductCache;

import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.util.EntityDtoMapper;
import org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCache productCache;


    public Optional<ProductsDTO> findProductsById(Long id) {
//        Optional<ProductsDTO> product = productCache.getProductResponse(id);
//        if (product.isPresent()) {
//            return Optional.of(product.get());
//        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException interruptedException) {
//        }
        ProductsEntity productsEntity = productsRepository.findById(id).get();
        ProductsDTO productsDTO = EntityDtoMapper.mapProductsToDto(productsEntity);
//         productCache.saveProductsResponseInCache(productsDTO);
        return Optional.of(productsDTO);
    }

    public List<ProductsDTO> findProductsByName(String name) {
        return findByName(name).stream()
                .map(EntityDtoMapper::mapProductsToDto)
                .collect(Collectors.toList());
    }

    private List<ProductsEntity> findByName(String name) {
        if (StringUtils.isBlank(name)) {
            List<ProductsEntity> allProducts = productsRepository.findAll();
            return allProducts;
        } else {
            List<ProductsEntity> byNameProducts = productsRepository.findByName(name);
            return byNameProducts;
        }
    }

    public ProductsDTO addProducts(ProductsDTO productsDTO) {
        ProductsEntity productsEntity = EntityDtoMapper.mapProductsToEntity(productsDTO);
//        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
//        productCategoryEntity.setId(productsDTO.getId());
//        productsEntity.setName(productsDTO.getName());
//        productsEntity.setPrice(productsDTO.getPrice());
        ProductsEntity save = productsRepository.save(productsEntity);
//        ProductCategoryEntity save1 = productCategoryRepository.save(productCategoryEntity);
        ProductsDTO productsDTO1 = EntityDtoMapper.mapProductsToDto(save);
        return productsDTO1;
    }

//    public ProductsDTO addProductsToCategory(Long productsId, Long productCategoryId) {
//        ProductsEntity productsEntity = productsRepository.findById(productsId).get();
//        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findById(productCategoryId).get();
//        productsEntity.setProductCategoryEntity(productCategoryEntity);
//        productCategoryEntity.getProductsEntitySet().add(productsEntity); //dodawanie SETa z produktami do kategorii
//        ProductsEntity save = productsRepository.save(productsEntity);
//        ProductsDTO productsDTO = EntityDtoMapper.mapProductsToDto(save);
//        return productsDTO;
//    }

    public void deleteProducts(Long id) {
        productsRepository.deleteById(id);
        // productCache.deleteProductsResponseFromCache(id);
    }

    public ProductsDTO editProducts(Long id, ProductsDTO productsDTO) {
        Optional<ProductsEntity> productsById = productsRepository.findById(id);
        if (productsById.isPresent()) {
            ProductsEntity productsEntity = productsById.get();
            productsEntity.setName(productsDTO.getName());
            productsEntity.setPrice(productsDTO.getPrice());
            ProductsEntity save = productsRepository.save(productsEntity);
            ProductsDTO productsDTO1 = EntityDtoMapper.mapProductsToDto(save);
            //    productCache.saveProductsResponseInCache(productsDTO1);
            return productsDTO1;
        } else {
            ProductsEntity productsEntity = EntityDtoMapper.mapProductsToEntity(productsDTO);
            ProductsEntity save = productsRepository.save(productsEntity);
            ProductsDTO productsDTO1 = EntityDtoMapper.mapProductsToDto(save);
            return productsDTO1;
        }
    }

    public List<ProductsDTO> getProducts(String name, Integer minPrice, Integer maxPrice) {
        return productsRepository.findAll().stream()
                .filter(productsEntity -> name == null || productsEntity.getName().equals(name))
                .filter(productsEntity -> minPrice == null || productsEntity.getPrice() >= minPrice)
                .filter(productsEntity -> maxPrice == null || productsEntity.getPrice() <= maxPrice)
                .map(EntityDtoMapper::mapProductsToDto)
                .collect(Collectors.toList());
    }

    public ProductsDTO addProductsAndCategory(ProductsDTO productsDTO) {
        ProductsEntity productsEntity = EntityDtoMapper.mapProductsToEntity(productsDTO);
        ProductCategoryEntity newProductCategoryEntity = EntityDtoMapper.mapProdCatToEntity(productsDTO.getProductCategoryDTO());       //tworzenie nowej kategorii newProductCategoryEntity
        if(productsDTO.getProductCategoryDTO().getId() !=null) {
            Optional<ProductCategoryEntity> productById = productCategoryRepository.findById(productsDTO.getProductCategoryDTO().getId());          // szukanie czy już taki productCategoryEntity istnieje
            if (productById.isPresent()) {
                newProductCategoryEntity = productById.get();
            }
        }//jeśli istnieje to nadpisujemy nowy newProductCategoryEntity znalezionym
        productsEntity.setProductCategoryEntity(newProductCategoryEntity);
        //newProductCategoryEntity.getProductsEntitySet().add(productsEntity);
        Set<ProductsEntity> productsEntityHashSet = new HashSet<>();
        productsEntityHashSet.add(productsEntity);
        newProductCategoryEntity.setProductsEntitySet(productsEntityHashSet);
        productCategoryRepository.save(newProductCategoryEntity);
        ProductsEntity save = productsRepository.save(productsEntity);


//        productCache.saveProductsInCache(productsDTO1);
        ProductsDTO productsDTO1 = EntityDtoMapper.mapProductsToDto(save);
        return productsDTO1;
//     else{
//
//
//            ProductsEntity productsEntity1 = EntityDtoMapper.mapProductsToEntity(productsDTO);
//            productsEntity1.setProductCategoryEntity(EntityDtoMapper.mapProdCatToEntity(productsDTO.getProductCategoryDTO()));
//            ProductsEntity save = productsRepository.save(productsEntity1);
//            ProductsDTO productsDTO2 = EntityDtoMapper.mapProductsToDto(save);
//            //    productCache.saveProductsResponseInCache(productsDTO2);
//            return productsDTO1;
//        }

    }
}






