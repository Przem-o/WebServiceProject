package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.repository.OrdersDetailsRepository;
import PB.WebServiceProject.repository.OrdersRepository;
import PB.WebServiceProject.repository.ProductsRepository;

import PB.WebServiceProject.rest.dto.OrderDetailsDTO;
import PB.WebServiceProject.rest.dto.OrdersDTO;
import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderDetailsService {


    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;
    private final OrdersDetailsRepository ordersDetailsRepository;

//    // produkt z kategoria
//    public OrderDetailsDTO addProductsToOrderDetails(OrderDetailsDTO orderDetailsDTO) {
//        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
//        ProductsEntity productsEntity = EntityDtoMapper.mapProductsToEntity(orderDetailsDTO.getProductsDTO());
////        Optional<ProductsEntity> byId = productsRepository.findById(orderDetailsDTO.getProductsDTO().getId());
//        orderDetailsEntity.setProductsEntity(productsEntity);
//        HashSet<OrderDetailsEntity> orderDetailsEntityHashSet = new HashSet<>();
////        productsEntity.getOrderDetailsEntitySet().add(orderDetailsEntity);
//        productsEntity.setOrderDetailsEntitySet(orderDetailsEntityHashSet);
//        productsRepository.save(productsEntity);
//        OrderDetailsEntity save = ordersDetailsRepository.save(orderDetailsEntity);
//        OrderDetailsDTO orderDetailsDTO1 = EntityDtoMapper.mapOrderDetailsToDto(save);
//        return orderDetailsDTO1;
//
//    }

//    public OrderDetailsDTO addOrderDetails(OrderDetailsDTO orderDetailsDTO) {
//        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
//        Optional<OrdersEntity> ordersEntity = ordersRepository.findById(orderDetailsDTO.getOrdersDTO().getId());
//        Optional<ProductsEntity> productsEntity = productsRepository.findById(orderDetailsDTO.getProductsDTO().getId());
//        orderDetailsEntity.setOrdersEntity(ordersEntity.get());
//        orderDetailsEntity.setProductsEntity(productsEntity.get());
//        OrderDetailsEntity save = ordersDetailsRepository.save(orderDetailsEntity);
//        return EntityDtoMapper.mapOrderDetailsToDto(save);
//
//    }

    public List<OrderDetailsDTO> getOrderDetails(Long orderDetailsId) {
        return ordersDetailsRepository.findAll().stream()
                .filter(orderDetailsEntity -> orderDetailsId == null || orderDetailsEntity.getId().equals(orderDetailsId))
                .map(EntityDtoMapper::mapOrderDetailsToDto)
                .collect(Collectors.toList());
    }

    public List<OrderDetailsDTO> getOrderDetail(Long ordersId) {
        Optional<OrdersEntity> ordersEntity = ordersRepository.findById(ordersId);
        if (ordersEntity.isEmpty()) {
            return new ArrayList<>();
        }
        Set<OrderDetailsEntity> orderDetailsEntitySet = ordersEntity.get().getOrderDetailsEntitySet();


        return orderDetailsEntitySet.stream().map(EntityDtoMapper::mapOrderDetailsToDto).collect(Collectors.toList());

    }
    public List<OrderDetailsDTO> getOrderedProducts(Long ordersId){
        Optional<OrdersEntity> ordersEntity = ordersRepository.findById(ordersId);
        if (ordersEntity.isEmpty()){
            return new ArrayList<>();
        }
        Set<OrderDetailsEntity> orderDetailsEntitySet = ordersEntity.get().getOrderDetailsEntitySet();
        return orderDetailsEntitySet.stream()
                .map(EntityDtoMapper::mapOrderDetailsToDto)
                .collect(Collectors.toList());

    }


    public void deleteOrderDetails(Long id) {
        ordersDetailsRepository.deleteById(id);
    }

}
