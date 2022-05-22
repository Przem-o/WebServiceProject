package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.repository.OrdersDetailsRepository;
import PB.WebServiceProject.repository.OrdersRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.rest.dto.OrderDetailsDTO;
import PB.WebServiceProject.rest.dto.OrdersDTO;;
import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.rest.dto.Status;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final ClientRepository clientRepository;
    private final ProductsRepository productsRepository;
    private final OrdersDetailsRepository ordersDetailsRepository;
    private final OrdersRepository ordersRepository;

//    public OrdersDTO addOrder(Long clientId, OrdersDTO ordersDTO) {
//        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(ordersDTO);
//        Optional<ClientEntity> client = clientRepository.findById(clientId);
//        ordersEntity.setDate(ordersDTO.getDate());
//        ordersEntity.setPrice(ordersDTO.getPrice());
//        ordersEntity.setStatus(ordersDTO.getStatus());
//        ordersEntity.setClientEntity(client.get());
//        OrdersEntity save = ordersRepository.save(ordersEntity);
//        OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(save);
//        return ordersDTO1;
//    }

//    public OrdersDTO addOrderWithOrderDetails(Long clientId, Long productId, OrdersDTO ordersDTO) {
//        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(ordersDTO);
//        Optional<ClientEntity> client = clientRepository.findById(clientId);
////        ordersEntity.setDate(ordersDTO.getDate());
//        ordersEntity.setDate(OffsetDateTime.now());
//        ordersEntity.setPrice(ordersDTO.getPrice());
//        ordersEntity.setStatus(ordersDTO.getStatus());
//        ordersEntity.setClientEntity(client.get());
//        OrdersEntity ordersEntitySaved = ordersRepository.save(ordersEntity);
//        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(ordersDTO.getOrderDetailsDTO());
//        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
//        orderDetailsEntity.setOrdersEntity(ordersEntity);
//        orderDetailsEntity.setProductsEntity(productsEntity.get());
//        orderDetailsEntity.setQuantity(ordersDTO.getOrderDetailsDTO().getQuantity());
//        OrderDetailsEntity orderDetailsEntitySaved = ordersDetailsRepository.save(orderDetailsEntity);
//        EntityDtoMapper.mapOrderDetailsToDto(orderDetailsEntitySaved);
//        OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(ordersEntitySaved);
//        return ordersDTO1;
//    }


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
//
    public OrdersDTO addOrderedProductByClient(Long clientId, Long productId, OrdersDTO ordersDTO, OrderDetailsDTO orderDetailsDTO){
        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(ordersDTO);

//        Set<OrderDetailsEntity> orderDetailsEntitySet = new HashSet<>();
//        orderDetailsEntitySet.add(orderDetailsEntity);
//        ordersEntity.setOrderDetailsEntitySet(orderDetailsEntitySet);
        ordersEntity.setClientEntity(clientEntity.get());
        ordersEntity.setId(ordersDTO.getId());
        ordersEntity.setDate(OffsetDateTime.now());
        ordersEntity.setStatus(Status.ORDERED);
//        ordersEntity.setPrice(orderDetailsEntity.getQuantity() * orderDetailsEntity.getProductsEntity().getPrice());
        ordersEntity.setPrice(0.0);

        orderDetailsEntity.setProductsEntity(productsEntity.get());
        orderDetailsEntity.setQuantity(orderDetailsDTO.getQuantity());
        orderDetailsEntity.setOrdersEntity(EntityDtoMapper.mapOrdersToEntity(ordersDTO));

        ordersEntity.setPrice(orderDetailsEntity.getQuantity() * orderDetailsEntity.getProductsEntity().getPrice());

        ordersDetailsRepository.save(orderDetailsEntity);

//        ordersEntity.setOrderDetailsEntitySet(orderDetailsEntitySet);
        OrdersEntity save = ordersRepository.save(ordersEntity);

        OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(save);
        return ordersDTO1;
    }
    public int sumOfQuantity(Set<OrderDetailsEntity> orderDetailsEntitySet) {
        return orderDetailsEntitySet.stream()
                .mapToInt(OrderDetailsEntity::getQuantity)
                .sum();
    }
//   public double calculatePrice (Long ordersId) {
//       double sum = 0;
//       ordersDetailsRepository.
//       for (double i = 0; i <; i++) {
//
//
//       }
//   }

    public List<OrdersDTO> getOrders(Long id, Integer minPrice, Integer maxPrice) {
        return ordersRepository.findAll().stream()
                .filter(ordersEntity -> id == null || ordersEntity.getId().equals(id))
                .filter(ordersEntity -> minPrice == null || ordersEntity.getPrice() >= minPrice)
                .filter(ordersEntity -> maxPrice == null || ordersEntity.getPrice() <= maxPrice)
                .map(EntityDtoMapper::mapOrdersToDto)
                .collect(Collectors.toList());
    }


    public void deleteOrder(Long orderId) {
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
    public OrdersDTO editOrder(Long orderId, OrdersDTO ordersDTO) {
        Optional<OrdersEntity> ordersEntity = ordersRepository.findById(orderId);
        if (ordersEntity.isPresent()) {
//            ordersEntity.get().setDate(ordersDTO.getDate());
            ordersEntity.get().setDate(ordersDTO.getDate());
            ordersEntity.get().setPrice(ordersDTO.getPrice());
            ordersEntity.get().setStatus(ordersDTO.getStatus());
            OrdersEntity save = ordersRepository.save(ordersEntity.get());
            OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(save);
            return ordersDTO1;
        } else {
            OrdersEntity ordersEntity1 = EntityDtoMapper.mapOrdersToEntity(ordersDTO);// zmienić to - jesli nie ma zamowienie to je dodaje
            OrdersEntity save = ordersRepository.save(ordersEntity1);
            OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(save);
            return ordersDTO1;
        }

    }
}
