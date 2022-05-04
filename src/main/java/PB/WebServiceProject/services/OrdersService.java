package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.repository.OrdersDetailsRepository;
import PB.WebServiceProject.repository.OrdersRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.rest.dto.OrderDetailsDTO;
import PB.WebServiceProject.rest.dto.OrdersDTO;;
import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final ClientRepository clientRepository;
    private final ProductsRepository productsRepository;
    private final OrdersDetailsRepository ordersDetailsRepository;
    private final OrdersRepository ordersRepository;

    public OrdersDTO addOrder(OrdersDTO ordersDTO, Long clientId) {
        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(ordersDTO);
        Optional<ClientEntity> client = clientRepository.findById(clientId);
        ordersEntity.setDate(ordersDTO.getDate());
        ordersEntity.setPrice(ordersDTO.getPrice());
        ordersEntity.setStatus(ordersDTO.getStatus());
        ordersEntity.setClientEntity(client.get());
        OrdersEntity save = ordersRepository.save(ordersEntity);
        OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(save);
        return ordersDTO1;
    }
//    public List<OrdersDTO> addOrders(Long clientId,  OrdersDTO ordersDTO){
//        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
//        if(clientEntity.isEmpty()){
//            return new ArrayList<>();
//        }
////        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
////        if (productsEntity.isEmpty()){
////            return new ArrayList<>();
////        }
//        clientEntity.get().getOrdersEntitySet().add(EntityDtoMapper.mapOrdersToEntity(ordersDTO));
//        ClientEntity save = clientRepository.save(clientEntity.get());
//        Set<OrdersEntity> ordersEntitySet = save.getOrdersEntitySet();
//        return ordersEntitySet.stream()
//                .map(EntityDtoMapper::mapOrdersToDto)
//                .collect(Collectors.toList());
//}

//    public OrdersDTO addOrders(OrderDetailsDTO orderDetailsDTO, Long clientId, Long productId){
//        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(orderDetailsDTO.getOrdersDTO());
//        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
//        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
//        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
//
//        ordersEntity.setClientEntity(clientEntity.get());
//
//        clientEntity.get().getOrdersEntitySet().add(ordersEntity);
//        orderDetailsEntity.setProductsEntity(productsEntity.get());
//        ordersRepository.save()
//
//        orderDetailsEntity.setProductsEntity(productsEntity.get());
////        ordersEntity.getOrderDetailsEntitySet().add(orderDetailsEntity);
//        ordersEntity.setClientEntity(clientEntity.get());
//        OrdersEntity save = ordersRepository.save(ordersEntity);
//        OrdersDTO ordersDTO = EntityDtoMapper.mapOrdersToDto(save);
//        return ordersDTO;
//    }

    public List<OrdersDTO> getOrders(Long id, Integer minPrice, Integer maxPrice) {
        return ordersRepository.findAll().stream()
                .filter(ordersEntity -> id == null || ordersEntity.getId().equals(id))
                .filter(ordersEntity -> minPrice == null || ordersEntity.getPrice() >= minPrice)
                .filter(ordersEntity -> maxPrice == null || ordersEntity.getPrice() <= maxPrice)
                .map(EntityDtoMapper::mapOrdersToDto)
                .collect(Collectors.toList());
    }


    public void deleteOrder(Long orderId){
        ordersRepository.deleteById(orderId);
    }

    public List<OrdersDTO> findClientOrders(Long clientId) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
        if (clientEntity.isEmpty()) {
            return new ArrayList<>();
        }
        Set<OrdersEntity> ordersEntitySet = clientEntity.get().getOrdersEntitySet();
        List<OrdersDTO> collect = ordersEntitySet.stream()
                .map(EntityDtoMapper::mapOrdersToDto)
                .collect(Collectors.toList());
        return collect;
    }

//
//    public OrdersDTO addClientToOrders(OrdersDTO ordersDTO) {
//        OrderDetailsEntity orderDetailsEntity = ordersDetailsRepository.findById(ordersDTO.getClientDTO().getId());
//        ClientEntity clientEntity = clientRepository.findById(ordersDTO.getClientDTO().getId());
//        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(ordersDTO);
//        ordersEntity.setId(ordersDTO.getId());
////        ordersEntity.setDate(OffsetDateTime.parse(ordersDTO.getDate()));
//        ordersEntity.setDate(ordersDTO.getDate());
//        ordersEntity.setPrice(ordersDTO.getPrice());
//        ordersEntity.setStatus(ordersDTO.getStatus());
//        ordersEntity.setClientEntity(clientEntity);
//        clientEntity.getOrdersEntitySet().add(ordersEntity);
//        orderDetailsEntity.setOrdersEntity(ordersEntity);
//        OrdersEntity save = ordersRepository.save(ordersEntity);
//        return EntityDtoMapper.mapOrdersToDto(save);
//
//    }
//
//    public List<ProductsDTO> findOrderedProducts() {
//        List<ProductsEntity> allProducts = productsRepository.findAll();
//        if (allProducts.isEmpty()) {
//            return new ArrayList<>();
//        }
//        List<ProductsDTO> collect = allProducts.stream()
//                .map(EntityDtoMapper::mapProductsToDto)
//                .collect(Collectors.toList());
//        return collect;
//
//    }
}
