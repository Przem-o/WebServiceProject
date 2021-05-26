package PB.WebServiceProject.services;
import PB.WebServiceProject.entities.ProductCategoryEntity;

import PB.WebServiceProject.repository.ProductCategoryRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.repository.cache.ProductCache;
import PB.WebServiceProject.rest.dto.ProductCategoryDTO;

import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCache productCache;


    public Optional<ProductCategoryDTO> findProductCategoryById(Long id) {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findById(id).get();
        ProductCategoryDTO productCategoryDTO = EntityDtoMapper.mapProdCatToDto(productCategoryEntity);
        return Optional.of(productCategoryDTO);
    }

    public ProductCategoryDTO addCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = EntityDtoMapper.mapProdCatToEntity(productCategoryDTO);
        ProductCategoryEntity save = productCategoryRepository.save(productCategoryEntity);
        ProductCategoryDTO productCategoryDTO1 = EntityDtoMapper.mapProdCatToDto(save);
        return productCategoryDTO1;
    }

    public List<ProductCategoryDTO> findCategory(String name){
        return findProductCategoryByName(name).stream()
                .map(EntityDtoMapper::mapProdCatToDto)
                .collect(Collectors.toList());
    }

    public void deleteCategory(Long id){
        productCategoryRepository.deleteById(id);
    }

    public ProductCategoryDTO editCategory(Long id, ProductCategoryDTO productCategoryDTO) {
        Optional<ProductCategoryEntity> byId = productCategoryRepository.findById(id);
        if (byId.isPresent()) {
            ProductCategoryEntity productCategoryEntity = byId.get();
            productCategoryEntity.setId(productCategoryDTO.getId());
            productCategoryEntity.setProductcategory(productCategoryDTO.getProductCategory());
            ProductCategoryEntity save = productCategoryRepository.save(productCategoryEntity);
            return EntityDtoMapper.mapProdCatToDto(save);
        } else {
            ProductCategoryEntity productCategoryEntity = EntityDtoMapper.mapProdCatToEntity(productCategoryDTO);
            ProductCategoryEntity save = productCategoryRepository.save(productCategoryEntity);
            return EntityDtoMapper.mapProdCatToDto(save);
       }
    }

    private List<ProductCategoryEntity> findProductCategoryByName(String name) {
        if (StringUtils.isBlank(name)) {
            return productCategoryRepository.findAll();
        } else {
            return productCategoryRepository.findByproductcategory(name);
        }
    }
}












