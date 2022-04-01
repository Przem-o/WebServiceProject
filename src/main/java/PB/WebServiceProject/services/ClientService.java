package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.AddressEntity;
import PB.WebServiceProject.entities.ClientEntity;
import PB.WebServiceProject.repository.AddressRepository;
import PB.WebServiceProject.repository.ClientRepository;
import PB.WebServiceProject.repository.cache.ClientCache;
import PB.WebServiceProject.rest.dto.AddressDTO;
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

    public ClientDTO addClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = EntityDtoMapper.mapClientToEntity(clientDTO);
        AddressEntity addressEntity = EntityDtoMapper.mapAddressToEntity(clientDTO.getAddressDTO());
        clientEntity.setAddressEntity(addressEntity);
        addressEntity.setClientEntity(clientEntity);
        ClientEntity saveClient = clientRepository.save(clientEntity);
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
            clientEntity.setId(clientDTO.getId()); // clientowi w bazie danych ustawiam/edytuje id ktore podałem w www
            clientEntity.setName(clientDTO.getName());
            AddressEntity addressEntity = clientEntity.getAddressEntity();
            if (addressEntity != null) {
                addressEntity.setCity(clientDTO.getAddressDTO().getCity());
                addressEntity.setCountry(clientDTO.getAddressDTO().getCountry());
            }
            ClientEntity saveClient = clientRepository.save(clientEntity);
//            AddressEntity saveAddress = addressRepository.save(clientEntity.getAddressEntity());
//            clientCache.saveClientResponseInCache(EntityDtoMapper.mapClientToDto(saveClient));
//            clientCache.saveAddressResponseInCache(EntityDtoMapper.mapAddressToDto(saveAddress));
            return EntityDtoMapper.mapClientToDto(saveClient);
        } else {
            ClientEntity clientEntity1 = EntityDtoMapper.mapClientToEntity(clientDTO);
            clientEntity1.setAddressEntity(EntityDtoMapper.mapAddressToEntity(clientDTO.getAddressDTO()));
            ClientDTO clientDTO1 = EntityDtoMapper.mapClientToDto(clientEntity1);
            return clientDTO1;
        }
    }

    public Optional<ClientDTO> getClientById(Long id) {
//        Optional<ClientDTO> client = clientCache.getClientResponse(id);
//        if (client.isPresent()) {
//            return Optional.of(client.get());
//        }
//        try {
//            Thread.sleep(5000); // symulacja oczekiwania 50 sekund na odpowedz żeby zobaczyc czy cache działa
//        } catch (InterruptedException interruptedException) {
//        }
        ClientEntity clientEntity = clientRepository.findById(id).get();
        ClientDTO clientDTO = EntityDtoMapper.mapClientToDto(clientEntity);
        // clientCache.saveClientResponseInCache(clientDTO);
        return Optional.of(clientDTO);
    }

    public List<ClientDTO> getClients(Long id, String name, String address) {
        return clientRepository.findAll().stream()
                .filter(clientEntity -> id == null || clientEntity.getId().equals(id))
                .filter(clientEntity -> name == null || clientEntity.getName().equalsIgnoreCase(name))
                .filter(clientEntity -> address == null || clientEntity.getAddressEntity().getCity().equalsIgnoreCase(address))
                .map(EntityDtoMapper::mapClientToDto)
                .collect(Collectors.toList());
    }
}


