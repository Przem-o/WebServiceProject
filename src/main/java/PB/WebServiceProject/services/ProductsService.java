package PB.WebServiceProject.services;


import PB.WebServiceProject.entities.ProductCategoryEntity;
import PB.WebServiceProject.entities.ProductsEntity;

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
    //   private final ProductCategoryRepository productCategoryRepository;
    private final ProductCache productCache;

//    public ProductsDTO addProductsAndCategory(ProductsDTO productsDTO) { //nokia / RTV
//        ProductsEntity productsEntity = EntityDtoMapper.mapProductsToEntity(productsDTO);
//        ProductCategoryEntity productCategoryEntity = EntityDtoMapper.mapProdCatToEntity(productsDTO.getProductCategoryDTO());
//        if (productsDTO.getProductCategoryDTO().getId() != null) { //jeśli wpisane w www id ProductCategory nie jest null
//            Optional<ProductCategoryEntity> productCategoryById = productCategoryRepository.findById(productsDTO.getProductCategoryDTO().getId());//szukanie czy już taki productCategoryEntity istnieje w bazie
//            if (productCategoryById.isPresent()) {
//                productCategoryEntity = productCategoryById.get(); //jesli istnieje już w bazie taki productCategoryById to ten nowy wyżej stworzony w 30 lini productCategoryEntity nadpisz tym z bazy productCategoryById
//            }
//        } // konsultacje 10 cz1 46 min
//        productsEntity.setProductCategoryEntity(productCategoryEntity); //jeśli productCategory podany w www jest null to ustaw produktowi kategorię podaną w DTO np.Smartphone
//        Set<ProductsEntity> productsEntityHashSet = new HashSet<>();
//        productsEntityHashSet.add(productsEntity);// do seta daję produkt nokia z kategorią Smartphone
//        productCategoryEntity.setProductsEntitySet(productsEntityHashSet); // kategorii Smartphone ustawiam listę produktów
//        productCategoryRepository.save(productCategoryEntity);
//        ProductsEntity save = productsRepository.save(productsEntity);
//        ProductsDTO productsDTO1 = EntityDtoMapper.mapProductsToDto(save);
//        // productCache.saveProductsResponseInCache(productsDTO1);
//        return productsDTO1;
//
//    }


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
//        productCache.deleteProductsResponseFromCache(id);
    }

    public ProductsDTO editProducts(Long id, ProductsDTO productsDTO) {
        Optional<ProductsEntity> productsById = productsRepository.findById(id);
        if (productsById.isPresent()) {
            ProductsEntity productsEntity = productsById.get();
            productsEntity.setId(productsDTO.getId());
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
                .filter(productsEntity -> name == null || productsEntity.getName().equalsIgnoreCase(name))
                .filter(productsEntity -> minPrice == null || productsEntity.getPrice() >= minPrice)
                .filter(productsEntity -> maxPrice == null || productsEntity.getPrice() <= maxPrice)
                .map(EntityDtoMapper::mapProductsToDto)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> findProductsByName(String name) {
        return findByName(name).stream()
                .map(EntityDtoMapper::mapProductsToDto)
                .collect(Collectors.toList());
    }

    private List<ProductsEntity> findByName(String name) { //metoda pomocnicza do w/w
        if (StringUtils.isBlank(name)) {
            List<ProductsEntity> allProducts = productsRepository.findAll();
            return allProducts;
        } else {
            List<ProductsEntity> byNameProducts = productsRepository.findProductsByName(name);
            return byNameProducts;
        }
    }

//    public Optional<ProductsDTO> findProductsById(Long id) {
////        Optional<ProductsDTO> product = productCache.getProductResponse(id);
////        if (product.isPresent()) {
////            return Optional.of(product.get());
////        }
////        try {
////            Thread.sleep(5000);
////        } catch (InterruptedException interruptedException) {
////        }
//
//
//        ProductsEntity productsEntity = productsRepository.findById(id).get();
//        ProductsDTO productsDTO = EntityDtoMapper.mapProductsToDto(productsEntity);
////        productCache.saveProductsResponseInCache(productsDTO);
//        return Optional.of(productsDTO);
//    }
//
    public List<ProductsDTO> findProductsById(Long id) {
        return productsRepository.findAll().stream()
                .filter(productsEntity -> productsEntity.getId().equals(id))
                .map(EntityDtoMapper::mapProductsToDto)
                .collect(Collectors.toList());
    }

}







