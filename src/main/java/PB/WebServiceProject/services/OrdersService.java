package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.ClientEntity;
import PB.WebServiceProject.entities.OrderDetailsEntity;
import PB.WebServiceProject.entities.OrdersEntity;
import PB.WebServiceProject.entities.ProductsEntity;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.repository.OrdersDetailsRepository;
import PB.WebServiceProject.repository.OrdersRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.rest.dto.OrderDetailsDTO;
import PB.WebServiceProject.rest.dto.OrdersDTO;
import PB.WebServiceProject.rest.dto.Status;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final ClientRepository clientRepository;
    private final ProductsRepository productsRepository;
    private final OrdersDetailsRepository ordersDetailsRepository;
    private final OrdersRepository ordersRepository;
    private final OrderDetailsService orderDetailsService;

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
    public OrdersDTO addOrderedProductByClient(Long clientId, Long productId, OrderDetailsDTO orderDetailsDTO) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(orderDetailsDTO.getOrdersDTO());
        ordersEntity.setId(orderDetailsDTO.getOrdersDTO().getId());
        ordersEntity.setDate(OffsetDateTime.now());
        ordersEntity.setStatus(Status.ORDERED);
        ordersEntity.setClientEntity(clientEntity.get());
        ordersEntity.setPrice(0.0);
        orderDetailsEntity.setProductsEntity(productsEntity.get());
        orderDetailsEntity.setQuantity(orderDetailsDTO.getQuantity());
        orderDetailsEntity.setOrdersEntity(ordersEntity);
        ordersDetailsRepository.save(orderDetailsEntity);
        Double orderPrices = updateOrderPrice(orderDetailsEntity.getOrdersEntity().getId());
        ordersEntity.setPrice(orderPrices);
        OrdersEntity save = ordersRepository.save(ordersEntity);
        OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(save);
        return ordersDTO1;
    }


//    public Double calcProductAndQuantity(Long productId, Integer quantity) {
//        Double productPrice = productsRepository.findById(productId).get().getPrice();
//        return productPrice * quantity;
//    }

    public Double updateOrderPrice(Long orderId) {
        List<OrderDetailsEntity> allByOrdersEntityId = ordersDetailsRepository.findAllByOrdersEntityId(orderId);
        double sum = 0;
        for (int i = 0; i < allByOrdersEntityId.size(); i++) {
            Double unitPriceOfProduct = allByOrdersEntityId.get(i).getProductsEntity().getPrice();
            Integer quantity = allByOrdersEntityId.get(i).getQuantity();
            double x = unitPriceOfProduct * quantity;
            sum = sum + x;
        }
        return sum;
    }
//    public Double orderPrice(Long ordersId){
//        DoubleStream doubleStream = ordersDetailsRepository.findAllByOrdersEntityId(ordersId).stream()
//                .map(OrderDetailsEntity::getProductsEntity)
//                .mapToDouble(ProductsEntity::getPrice)
//                .
//
////        DoubleStream doubleStream1 = ordersDetailsRepository.findAllByQuantity(quantity).stream().mapToDouble(OrderDetailsEntity::getQuantity);
////        return doubleStream * quantity;
//        return doubleStream;
//
//    }


//    public int sumOfQuantity(Long orderId) {
//        Optional<OrderDetailsEntity> byId = ordersDetailsRepository.findById(orderId);
//         byId.stream()
//                 .map(OrderDetailsEntity::getQuantity)
//                 .
//    }
//   public double calculatePrice (Long productId, Set<OrderDetailsEntity> orderDetailsEntitySet) {
//       Optional<ProductsEntity> byId = productsRepository.findById(productId);
//       for (int i = 0; i < orderDetailsEntitySet.size() ; i++) {
//           IntStream intStream = orderDetailsEntitySet.stream().mapToInt(OrderDetailsEntity::getQuantity);
//           orderDetailsEntitySet.stream().
//
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
