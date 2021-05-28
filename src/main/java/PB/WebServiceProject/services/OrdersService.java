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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final ClientRepository clientRepository;
    private final ProductsRepository productsRepository;
    private final OrdersDetailsRepository ordersDetailsRepository;
    private final OrdersRepository ordersRepository;

    public List<ProductsDTO> findOrderedProducts(){
        List<ProductsEntity> allProducts = productsRepository.findAll();
        if(allProducts.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductsDTO> collect = allProducts.stream()
                .map(EntityDtoMapper::mapProductsToDto)
                .collect(Collectors.toList());
        return collect;

    }

    public OrdersDTO addOrdersToUser(OrdersDTO ordersDTO){
        OrderDetailsEntity orderDetailsEntity = ordersDetailsRepository.findById(ordersDTO.getClientDTO().getId()).get();
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
//    public List<ProductsDTO> addProductsToOrderList(Long clientId, Long productId){
//        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
//        if(clientEntity.isEmpty()){
//            return new ArrayList<>();
//        }
//        Optional<ProductsEntity> productsEntity = productsRepository.findById(productId);
//        if(productsEntity.isEmpty()){
//            return new ArrayList<>();
//        }
//        clientEntity.get().get
//    }


}
