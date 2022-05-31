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

    public OrderDetailsDTO addOrderDetails(Long productId, Long ordersId, OrderDetailsDTO orderDetailsDTO) {
        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
        Optional<OrdersEntity> ordersEntity = ordersRepository.findById(ordersId);
        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
        orderDetailsEntity.setOrdersEntity(ordersEntity.get());
        orderDetailsEntity.setProductsEntity(productsEntity.get());
        OrderDetailsEntity save = ordersDetailsRepository.save(orderDetailsEntity);
        return EntityDtoMapper.mapOrderDetailsToDto(save);

    }
//    public OrderDetailsDTO addProductAndOrdersToOrderDetails(Long productId, Long ordersId, OrderDetailsDTO orderDetailsDTO){
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
        List<OrderDetailsEntity> orderDetailsEntitySet = ordersEntity.get().getOrderDetailsEntitySet();


        return orderDetailsEntitySet.stream().map(EntityDtoMapper::mapOrderDetailsToDto).collect(Collectors.toList());

    }

    public List<OrderDetailsDTO> getOrderedProducts(Long ordersId) { // poprawna metoda
        Optional<OrdersEntity> ordersEntity = ordersRepository.findById(ordersId);
        if (ordersEntity.isEmpty()) {
            return new ArrayList<>();
        }
        List<OrderDetailsEntity> orderDetailsEntitySet = ordersEntity.get().getOrderDetailsEntitySet();
        return orderDetailsEntitySet
                .stream()
                .map(EntityDtoMapper::mapOrderDetailsToDto)
                .collect(Collectors.toList());

    }
//    public List<OrderDetailsDTO> getOrderedProductsFromOrderDetails(Long ordersId) {
//        Optional<OrderDetailsEntity> orderDetailsEntity = ordersDetailsRepository.findById(ordersId);
//        if (orderDetailsEntity.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        Set<OrderDetailsEntity> orderDetailsEntitySet = orderDetailsEntity.get().getOrdersEntity().getOrderDetailsEntitySet();
//        return orderDetailsEntitySet
//                .stream()
//                .map(EntityDtoMapper::mapOrderDetailsToDto)
//                .collect(Collectors.toList());
//
//    }

    public void deleteOrderDetails(Long id) {
        ordersDetailsRepository.deleteById(id);
    }

    //    public OrderDetailsDTO editOrderDetails(Long orderDetailsId, OrderDetailsDTO orderDetailsDTO){
//        Optional<OrderDetailsEntity> orderDetailsEntity = ordersDetailsRepository.findById(orderDetailsId);
//        Optional<OrderDetailsEntity> orderDetailsEntity1 = ordersDetailsRepository.findById(orderDetailsDTO.getProductsDTO().getId());
////        if(orderDetailsEntity.isPresent()){
//        orderDetailsEntity.get().setId(orderDetailsDTO.getId());
//        orderDetailsEntity.get().setQuantity(orderDetailsDTO.getQuantity());
//        orderDetailsEntity.get().setProductsEntity(EntityDtoMapper.mapProductsToEntity(orderDetailsDTO.getProductsDTO()));
////            orderDetailsEntity.get().setOrdersEntity(EntityDtoMapper.mapOrdersToEntity(orderDetailsDTO.get);
//            OrderDetailsEntity save = ordersDetailsRepository.save(orderDetailsEntity.get());
//            OrderDetailsDTO orderDetailsDTO1 = EntityDtoMapper.mapOrderDetailsToDto(save);
//            return orderDetailsDTO1;
//
//    }
    public OrderDetailsDTO editOrderedProduct(Long orderId, OrderDetailsDTO orderDetailsDTO) {
        Optional<OrdersEntity> ordersEntity = ordersRepository.findById(orderId);
        if (ordersEntity.isPresent()) {
            ordersEntity.get();
            OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
//            orderDetailsEntity.setId(orderDetailsDTO.getId());
            orderDetailsEntity.setQuantity(orderDetailsDTO.getQuantity());
            orderDetailsEntity.setProductsEntity(EntityDtoMapper.mapProductsToEntity(orderDetailsDTO.getProductsDTO()));
            orderDetailsEntity.setOrdersEntity(ordersEntity.get());
            OrderDetailsEntity save = ordersDetailsRepository.save(orderDetailsEntity);
            OrderDetailsDTO orderDetailsDTO1 = EntityDtoMapper.mapOrderDetailsToDto(save);
            return orderDetailsDTO1;
        }
        throw new NoSuchElementException("id doesn't exist ");
    }
}
