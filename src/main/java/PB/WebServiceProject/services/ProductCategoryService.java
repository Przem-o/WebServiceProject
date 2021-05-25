package PB.WebServiceProject.services;


import PB.WebServiceProject.entities.ProductCategoryEntity;
import PB.WebServiceProject.entities.ProductsEntity;
import PB.WebServiceProject.repository.ProductCategoryRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.repository.cache.ProductCache;
import PB.WebServiceProject.rest.dto.ProductCategoryDTO;
import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductCategoryService {

    private final ProductsRepository productsRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCache productCache;



    public ProductCategoryDTO addCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = EntityDtoMapper.mapProdCatToEntity(productCategoryDTO);
        ProductCategoryEntity save = productCategoryRepository.save(productCategoryEntity);
        ProductCategoryDTO productCategoryDTO1 = EntityDtoMapper.mapProdCatToDto(save);
        return productCategoryDTO1;

    }

}





