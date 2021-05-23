package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.AddressEntity;
import PB.WebServiceProject.entities.ClientEntity;

import PB.WebServiceProject.repository.AddressRepository;
import PB.WebServiceProject.repository.cache.ClientCache;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.rest.dto.ClientDTO;
import PB.WebServiceProject.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
     private final ClientCache clientCache;


    public Optional<ClientDTO> getClientById(Long id) {
//        Optional<ClientDTO> client = clientCache.getClientResponse(id);
//        if (client.isPresent()) {
//            return Optional.of(client.get());
//        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException interruptedException) {
//        }
        ClientEntity clientEntity = clientRepository.findById(id).get();
        ClientDTO clientDTO = EntityDtoMapper.mapClientToDto(clientEntity);
        // clientCache.saveClientResponseInCache(clientDTO);
        return Optional.of(clientDTO);
    }

    public List<ClientDTO> findClients(String name) {
        return findClientsByName(name).stream()
                .map(EntityDtoMapper::mapClientToDto)// zamienia jednego lub wszystkich clientEntity na clientDTO
                .collect(Collectors.toList());
    }

    private List<ClientEntity> findClientsByName(String name) { //metoda pomocnicza do w/w
        if (StringUtils.isBlank(name)) {
            List<ClientEntity> allClients = clientRepository.findAll();
            return allClients;
        } else {
            List<ClientEntity> clientByName = clientRepository.findByName(name);
            return clientByName;
        }
    }

    public ClientDTO addClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = EntityDtoMapper.mapClientToEntity(clientDTO);// wrzuca clientaDTO do metody entity i zapisuje jako Entity
        AddressEntity addressEntity = EntityDtoMapper.mapAddressToEntity(clientDTO.getAddress()); // wrzuca adresDTO clienta do entity i zapisuje jako Entity
        clientEntity.setAddressEntity(addressEntity);// ustawia dla clientaEntity adresEntity
        addressEntity.setClientEntity(clientEntity);// ustawia dla adresuEntity clientaEntity
        ClientEntity saveClient = clientRepository.save(clientEntity);// zapisanie w/w clientaEntity z adresem do bazy danych i przypisanie do zmiennej
        // ClientDTO clientDTO1 = EntityDtoMapper.mapClientToDto(saveClient);//zamiana mapowanie clientEntity na clientDto w celu zapisu w cachu i przypisanie do zmiennej
        // clientCache.saveClientResponseInCache(clientDTO1);// zapisanie w/w clientaDto do cacha, w cachu zapisujemy to co trafiło do bazy danych
        return EntityDtoMapper.mapClientToDto(saveClient);// zwraca ClientDTO i leci do -> ClientController
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
        // clientCache.deleteClientResponseFromCache(id);
    }

    public ClientDTO editClient(Long id, ClientDTO clientDTO) {
        Optional<ClientEntity> findClientById = clientRepository.findById(id);
        if (findClientById.isPresent()) {
            ClientEntity clientEntity = findClientById.get();
            clientEntity.setName(clientDTO.getName());
            clientEntity.setAddressEntity(EntityDtoMapper.mapAddressToEntity(clientDTO.getAddress()));
            ClientEntity saveClient = clientRepository.save(clientEntity);
            AddressEntity saveAddress = addressRepository.save(clientEntity.getAddressEntity());
//            clientCache.saveClientResponseInCache(EntityDtoMapper.mapClientToDto(saveClient));
//            clientCache.saveAddressResponseInCache(EntityDtoMapper.mapAddressToDto(saveAddress));
            return EntityDtoMapper.mapClientToDto(saveClient);
        } else {
            ClientEntity clientEntity1 = EntityDtoMapper.mapClientToEntity(clientDTO);
            clientEntity1.setAddressEntity(EntityDtoMapper.mapAddressToEntity(clientDTO.getAddress()));
            return EntityDtoMapper.mapClientToDto(clientEntity1);
        }

    }
}

