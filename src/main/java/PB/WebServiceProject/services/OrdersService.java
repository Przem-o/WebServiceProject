package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.repository.OrdersDetailsRepository;
import PB.WebServiceProject.repository.OrdersRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.rest.dto.OrderDetailsDTO;
import PB.WebServiceProject.rest.dto.OrdersDTO;
import PB.WebServiceProject.rest.dto.ProductsDTO;
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

    public OrderDetailsDTO addOrderedProductByClient(Long clientID, Long productId, OrderDetailsDTO orderDetailsDTO) {
        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
        Optional<ClientEntity> clientEntity = clientRepository.findById(clientID);
        OrderDetailsEntity orderDetailsEntity = EntityDtoMapper.mapOrderDetailsToEntity(orderDetailsDTO);
        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(orderDetailsDTO.getOrdersDTO());
        if (orderDetailsDTO.getOrdersDTO().getId() != null) {
            Optional<OrdersEntity> ordersEntity1 = ordersRepository.findById(orderDetailsDTO.getOrdersDTO().getId());
            if (ordersEntity1.isPresent()) {
                ordersEntity = ordersEntity1.get();
            }
        }
        orderDetailsEntity.setOrdersEntity(ordersEntity);
        orderDetailsEntity.setProductsEntity(productsEntity.get());
        orderDetailsEntity.setQuantity(orderDetailsDTO.getQuantity());
        List<OrderDetailsEntity> orderDetailsEntityList = new ArrayList<>();
        orderDetailsEntityList.add(orderDetailsEntity);
        ordersEntity.setOrderDetailsEntityList(orderDetailsEntityList);
        ordersEntity.setId(orderDetailsDTO.getOrdersDTO().getId());
        ordersEntity.setDate(OffsetDateTime.now());
        ordersEntity.setStatus(Status.ORDERED);
        ordersEntity.setPrice(orderDetailsEntity.getProductsEntity().getPrice());
        ordersEntity.setClientEntity(clientEntity.get());
        ordersRepository.save(ordersEntity);
        OrderDetailsEntity save1 = ordersDetailsRepository.save(orderDetailsEntity);
        Double orderPrices = updateOrderPrice(orderDetailsEntity.getOrdersEntity().getId());
        ordersEntity.setPrice(orderPrices);
        ordersRepository.save(ordersEntity);
        OrderDetailsDTO orderDetailsDTO1 = EntityDtoMapper.mapOrderDetailsToDto(save1);
        return orderDetailsDTO1;
    }

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
        List<OrdersDTO> ordersDTOList = ordersEntitySet.stream()
                .map(EntityDtoMapper::mapOrdersToDto)
                .collect(Collectors.toList());
        return ordersDTOList;
    }

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
