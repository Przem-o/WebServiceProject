package PB.WebServiceProject.services;

import PB.WebServiceProject.entities.AddressEntity;
import PB.WebServiceProject.entities.ClientEntity;
import PB.WebServiceProject.repository.ClientCache;
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
    private  final ClientCache clientCache;

    public Optional<ClientDTO> getClientById(Long id) {
        Optional<ClientDTO> client = clientCache.getClientResponse(id);
        if(client.isPresent()){
            return Optional.of(client.get());
        }
        try {
            Thread.sleep(5000);
        }catch (InterruptedException interruptedException){}
        ClientEntity clientEntity = clientRepository.findById(id).get();
        ClientDTO clientDTO = EntityDtoMapper.mapToDto(clientEntity);
        clientCache.saveResponseInCache(clientDTO);
        return Optional.of(clientDTO);
    }

    public List<ClientDTO> findClients(String name) {
        return findClientsByName(name).stream()
                .map(EntityDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private List<ClientEntity> findClientsByName(String name) {
        if (StringUtils.isBlank(name)) {
            return clientRepository.findAll();
        } else {
            return clientRepository.findByName(name);
        }
    }

    public ClientDTO addClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = EntityDtoMapper.mapToEntity(clientDTO);
        AddressEntity addressEntity = EntityDtoMapper.mapToEntity(clientDTO.getAddress());
        clientEntity.setAddressEntity(addressEntity);
        addressEntity.setClientEntity(clientEntity);
        ClientEntity save = clientRepository.save(clientEntity);
        ClientDTO clientDTO1 = EntityDtoMapper.mapToDto(save);
        clientCache.saveResponseInCache(clientDTO1);
        return EntityDtoMapper.mapToDto(save);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
