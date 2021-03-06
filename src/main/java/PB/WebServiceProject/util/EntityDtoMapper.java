package PB.WebServiceProject.util;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.rest.dto.*;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class EntityDtoMapper {

    public static ClientDTO mapClientToDto(ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
//        ClientDTO clientDTO = ClientDTO.builder().build();
        BeanUtils.copyProperties(clientEntity, clientDTO);
        if (clientEntity.getAddressEntity() != null) {
            clientDTO.setAddress(EntityDtoMapper.mapAddressToDto(clientEntity.getAddressEntity()));
        }
        return clientDTO;
    }

    public static ClientEntity mapClientToEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
//        ClientEntity clientEntity = ClientEntity.builder().build();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        clientEntity.setAddressEntity(EntityDtoMapper.mapAddressToEntity(clientDTO.getAddress()));
        return clientEntity;
    }

    public static AddressDTO mapAddressToDto(AddressEntity addressEntity) {
        AddressDTO addressDTO = new AddressDTO();
//        AddressDTO addressDTO = AddressDTO.builder().build();
        BeanUtils.copyProperties(addressEntity, addressDTO);
        return addressDTO;
    }

    public static AddressEntity mapAddressToEntity(AddressDTO addressDTO) {
        AddressEntity addressEntity = new AddressEntity();
//        AddressEntity addressEntity = AddressEntity.builder().build();
        BeanUtils.copyProperties(addressDTO, addressEntity);
        return addressEntity;
    }

    public static ProductsDTO mapProductsToDto(ProductsEntity productsEntity) {
        ProductsDTO productsDTO = new ProductsDTO();
        BeanUtils.copyProperties(productsEntity, productsDTO);
        if (productsEntity.getProductCategoryEntity() != null) {
            productsDTO.setProductCategoryDTO(EntityDtoMapper.mapProdCatToDto(productsEntity.getProductCategoryEntity()));
        }
        return productsDTO;

    }

    public static ProductsEntity mapProductsToEntity(ProductsDTO productsDTO) {
        ProductsEntity productsEntity = new ProductsEntity();
        BeanUtils.copyProperties(productsDTO, productsEntity);
        return productsEntity;
    }

    public static ProductCategoryDTO mapProdCatToDto(ProductCategoryEntity productCategoryEntity) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        BeanUtils.copyProperties(productCategoryEntity, productCategoryDTO);
//        if(productCategoryEntity.getProductsEntitySet() !=null){
//            productCategoryDTO.setProductsDTOSet(productCategoryEntity.getProductsEntitySet().stream()
//                    .map(EntityDtoMapper::mapProductsToDto)
//                    .collect(Collectors.toSet()));
//        }
        return productCategoryDTO;
    }

    public static ProductCategoryEntity mapProdCatToEntity(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        BeanUtils.copyProperties(productCategoryDTO, productCategoryEntity);
        return productCategoryEntity;
    }

    public static OrdersDTO mapOrdersToDto(OrdersEntity ordersEntity) {
        OrdersDTO ordersDTO = new OrdersDTO();
        BeanUtils.copyProperties(ordersEntity, ordersDTO);
//        ordersDTO.setId(ordersDTO.getId());
//        ordersDTO.setDate(ordersDTO.getDate());
//        ordersDTO.setPrice(ordersDTO.getPrice());
//        ordersDTO.setStatus(ordersDTO.getStatus());
        if(ordersEntity.getClientEntity() != null){
            ordersDTO.setOrderDetailsEntitySet(ordersEntity.getOrderDetailsEntitySet().stream()
                    .map(EntityDtoMapper::mapOrderDetailsToDto)
                    .collect(Collectors.toSet()));
        }
        return ordersDTO;

    }
    public static OrdersEntity mapOrdersToEntity(OrdersDTO ordersDTO){
        OrdersEntity ordersEntity = new OrdersEntity();
        BeanUtils.copyProperties(ordersDTO, ordersEntity);
        return ordersEntity;
    }

    public static OrderDetailsDTO mapOrderDetailsToDto(OrderDetailsEntity orderDetailsEntity){
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        BeanUtils.copyProperties(orderDetailsEntity, orderDetailsDTO);
        return orderDetailsDTO;
    }
}




