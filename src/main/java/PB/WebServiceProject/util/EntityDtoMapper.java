package PB.WebServiceProject.util;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.rest.dto.*;
import org.springframework.beans.BeanUtils;

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
        return productCategoryDTO;
    }
    public static ProductCategoryEntity mapProdCatToEntity(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        BeanUtils.copyProperties(productCategoryDTO, productCategoryEntity);
        return productCategoryEntity;
    }

//
//    public static OrdersDTO mapToDto(OrdersEntity ordersEntity) {
//        OrdersDTO ordersDTO = new OrdersDTO();
//        BeanUtils.copyProperties(ordersEntity, ordersDTO);
//        return ordersDTO;
//    }
//
//    public static OrdersEntity mapToEntity(OrdersDTO ordersDTO) {
//        OrdersEntity ordersEntity = new OrdersEntity();
//        BeanUtils.copyProperties(ordersDTO, ordersEntity);
//        return ordersEntity;
//    }
//
//    public static OrderDetailsDTO mapToDto(OrderDetailsEntity orderDetailsEntity) {
//        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
//        BeanUtils.copyProperties(orderDetailsEntity, orderDetailsDTO);
//        return orderDetailsDTO;
//    }
//
//    public static OrderDetailsEntity mapToEntity(OrderDetailsDTO orderDetailsDTO) {
//        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
//        BeanUtils.copyProperties(orderDetailsDTO, orderDetailsEntity);
//        return orderDetailsEntity;
//    }
//
//    public static ProductCategoryDTO mapToDto(ProductCategoryEntity productCategoryEntity) {
//        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
//        BeanUtils.copyProperties(productCategoryEntity, productCategoryDTO);
//        return productCategoryDTO;
//    }
//    public static ProductCategoryEntity mapToEntity(ProductCategoryDTO productCategoryDTO) {
//        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
//        BeanUtils.copyProperties(productCategoryDTO, productCategoryEntity);
//        return productCategoryEntity;
//    }

}




