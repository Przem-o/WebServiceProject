package PB.WebServiceProject.util;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.rest.dto.*;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

public class EntityDtoMapper {

    public static ClientDTO mapClientToDto(ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
//        ClientDTO clientDTO = ClientDTO.builder().build();// metoda spring data do tworzenia obiektów
        BeanUtils.copyProperties(clientEntity, clientDTO);//metoda kopiuje tylko pola o tej samej nazwie i typie oprócz obiektów np Address i dotyczy tylko relacji oneToOne
        if (clientEntity.getAddressEntity() != null) { // jeśli nie jest nullem to go przepisz/wstaw
            clientDTO.setAddressDTO(EntityDtoMapper.mapAddressToDto(clientEntity.getAddressEntity()));
        }
        return clientDTO;
    }

    public static ClientEntity mapClientToEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
//        ClientEntity clientEntity = ClientEntity.builder().build();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        clientEntity.setAddressEntity(EntityDtoMapper.mapAddressToEntity(clientDTO.getAddressDTO()));
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
        if (productsEntity.getProductCategoryEntity() != null) {
            productsEntity.setProductCategoryEntity(EntityDtoMapper.mapProdCatToEntity(productsDTO.getProductCategoryDTO()));
        }
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

    public static OrdersDTO mapOrdersToDto(OrdersEntity ordersEntity) {
        OrdersDTO ordersDTO = new OrdersDTO();
        BeanUtils.copyProperties(ordersEntity, ordersDTO);
//        ordersDTO.setId(ordersEntity.getId());
//        ordersDTO.setDate(ordersEntity.getDate());
//        ordersEntity.setDate(ordersDTO.getDate());
//        ordersDTO.setPrice(ordersEntity.getPrice());
//        ordersDTO.setStatus(ordersEntity.getStatus());
//        if (ordersEntity.getClientEntity() != null) {
//            ordersDTO.setOrderDetailsDTOSet(ordersEntity.getOrderDetailsEntitySet()
//                    .stream()
//                    .map(EntityDtoMapper::mapOrderDetailsToDto)
//                    .collect(Collectors.toSet()));
//        }
        return ordersDTO;

    }

    public static OrdersEntity mapOrdersToEntity(OrdersDTO ordersDTO) {
        OrdersEntity ordersEntity = new OrdersEntity();
        BeanUtils.copyProperties(ordersDTO, ordersEntity);
//        ordersEntity.setId(ordersDTO.getId());
//        ordersEntity.setDate(ordersDTO.getDate());
//        ordersEntity.setDate(OffsetDateTime.now());
//        ordersEntity.setPrice(ordersDTO.getPrice());
//        ordersEntity.setStatus(ordersDTO.getStatus());
        return ordersEntity;
    }

    public static OrderDetailsDTO mapOrderDetailsToDto(OrderDetailsEntity orderDetailsEntity) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        BeanUtils.copyProperties(orderDetailsEntity, orderDetailsDTO);
//        orderDetailsDTO.setProductsDTO(EntityDtoMapper.mapProductsToDto(orderDetailsEntity.getProductsEntity()));
//        orderDetailsDTO.setOrdersDTO(EntityDtoMapper.mapOrdersToDto(orderDetailsEntity.getOrdersEntity()));
        if (orderDetailsEntity.getProductsEntity() != null) {
            orderDetailsDTO.setProductsDTO(EntityDtoMapper.mapProductsToDto(orderDetailsEntity.getProductsEntity()));
        }
        return orderDetailsDTO;
    }

    public static OrderDetailsEntity mapOrderDetailsToEntity(OrderDetailsDTO orderDetailsDTO) {
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        BeanUtils.copyProperties(orderDetailsDTO, orderDetailsEntity);
        return orderDetailsEntity;
    }
}




