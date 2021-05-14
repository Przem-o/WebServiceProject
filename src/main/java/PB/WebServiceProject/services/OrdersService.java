//package PB.WebServiceProject.services;
//
//import PB.WebServiceProject.repository.ClientRepository;
//import PB.WebServiceProject.repository.ProductsRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import task4.entities.ClientEntity;
//import task4.entities.SmartphoneEntity;
//import task4.repositories.ClientRepository;
//import task4.repositories.SmartphoneRepository;
//import task4.rest.dto.SmartphoneDTO;
//import task4.util.EntityDtoMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//public class OrdersService {
//
//    private final ClientRepository clientRepository;
//    private final ProductsRepository productsRepository;
//
////    public OrderService(ClientRepository clientRepository, SmartphoneRepository smartphoneRepository) {
////        this.clientRepository = clientRepository;
////        this.smartphoneRepository = smartphoneRepository;
////    }
//
//    public List<SmartphoneDTO> findOrderedSmartphones(Long clientId){
//        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
//        if(clientEntity.isEmpty()) {
//            return new ArrayList<>();
//        }
//        Set<SmartphoneEntity> smartphoneEntities = clientEntity.get().getSmartphoneEntities();
//        return smartphoneEntities.stream().map(EntityDtoMapper::mapToDto).collect(Collectors.toList());
//    }
//
//    public List<SmartphoneDTO> addSmartphoneToOrderList(Long clientId, Long smartphoneId) {
//        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
//        if(clientEntity.isEmpty()) {
//            return new ArrayList<>();
//        }
//        Optional<SmartphoneEntity> smartphoneEntity = smartphoneRepository.findById(smartphoneId);
//        if(smartphoneEntity.isEmpty()) {
//            return new ArrayList<>();
//        }
//        clientEntity.get().getSmartphoneEntities().add(smartphoneEntity.get());
//        ClientEntity save = clientRepository.save(clientEntity.get());
//        return save.getSmartphoneEntities().stream()
//                .map(EntityDtoMapper::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    public void deleteSmartphoneFromOrderList(Long clientId, Long smartphoneId) {
//        Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
//        if(clientEntity.isEmpty()) {
//            return;
//        }
//        Optional<SmartphoneEntity> smartphone = clientEntity.get().getSmartphoneEntities().stream()
//                .filter(smartphoneEntity -> smartphoneEntity.getId().equals(smartphoneId))
//                .findFirst();
//        if(smartphone.isEmpty()) {
//            return;
//        }
//        clientEntity.get().getSmartphoneEntities().remove(smartphone.get());
//        clientRepository.save(clientEntity.get());
//    }
//
//}
