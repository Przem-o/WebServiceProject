package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.*;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.repository.OrdersDetailsRepository;
import PB.WebServiceProject.repository.OrdersRepository;
import PB.WebServiceProject.repository.ProductsRepository;
import PB.WebServiceProject.rest.dto.OrdersDTO;
import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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

    public List<OrdersDTO> getOrders(Long id, Integer minPrice, Integer maxPrice) {
        return ordersRepository.findAll().stream()
                .filter(ordersEntity -> id == null || ordersEntity.getId().equals(id))
                .filter(ordersEntity -> minPrice == null || ordersEntity.getPrice() >= minPrice)
                .filter(ordersEntity -> maxPrice == null || ordersEntity.getPrice() <= maxPrice)
                .map(EntityDtoMapper::mapOrdersToDto)
                .collect(Collectors.toList());
    }

    public List<OrdersDTO> findClientOrders(Long clientId) {
        Optional<ClientEntity> client = clientRepository.findById(clientId);
        if (client.isEmpty()) {
            return new ArrayList<>();
        }
        Set<OrdersEntity> ordersEntitySet = client.get().getOrdersEntitySet();
        List<OrdersDTO> collect = ordersEntitySet.stream()
                .map(EntityDtoMapper::mapOrdersToDto)
                .collect(Collectors.toList());
        return collect;
    }

    public OrdersDTO addOrder(OrdersDTO ordersDTO) {
        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(ordersDTO);
        OrdersEntity save = ordersRepository.save(ordersEntity);
        OrdersDTO ordersDTO1 = EntityDtoMapper.mapOrdersToDto(save);
        return ordersDTO1;
    }

    public void deleteOrder(Long id){
        ordersRepository.deleteById(id);
    }

    public OrdersDTO addOrdersToUser(OrdersDTO ordersDTO) {
        OrderDetailsEntity orderDetailsEntity = ordersDetailsRepository.findById(ordersDTO.getClientEntity().getId()).get();
        ClientEntity clientEntity = clientRepository.findById(ordersDTO.getClientId()).get();
        OrdersEntity ordersEntity = EntityDtoMapper.mapOrdersToEntity(ordersDTO);
        ordersEntity.setId(ordersDTO.getId());
//        ordersEntity.setDate(OffsetDateTime.parse(ordersDTO.getDate()));
        ordersEntity.setDate(ordersDTO.getDate());
        ordersEntity.setPrice(ordersDTO.getPrice());
        ordersEntity.setStatus(ordersDTO.getStatus());
        ordersEntity.setClientEntity(clientEntity);
        clientEntity.getOrdersEntitySet().add(ordersEntity);
        orderDetailsEntity.setOrdersEntity(ordersEntity);
        OrdersEntity save = ordersRepository.save(ordersEntity);
        return EntityDtoMapper.mapOrdersToDto(save);

    }

    public List<ProductsDTO> findOrderedProducts() {
        List<ProductsEntity> allProducts = productsRepository.findAll();
        if (allProducts.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductsDTO> collect = allProducts.stream()
                .map(EntityDtoMapper::mapProductsToDto)
                .collect(Collectors.toList());
        return collect;

    }
}
