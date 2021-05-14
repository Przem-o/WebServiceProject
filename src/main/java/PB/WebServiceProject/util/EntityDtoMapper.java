package PB.WebServiceProject.util;

import PB.WebServiceProject.entities.AddressEntity;
import PB.WebServiceProject.entities.ClientEntity;
import PB.WebServiceProject.rest.dto.AddressDTO;
import PB.WebServiceProject.rest.dto.ClientDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {

    public static ClientDTO mapToDto(ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
//        ClientDTO clientDTO = ClientDTO.builder().build();
        BeanUtils.copyProperties(clientEntity, clientDTO);
        if(clientEntity.getAddressEntity()!=null) {  // if dlatego że nie każdy klient ma adres i powstaje błąd
            clientDTO.setAddress(EntityDtoMapper.mapToDto(clientEntity.getAddressEntity()));
        }
        return clientDTO;
    }

    public static ClientEntity mapToEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
//        ClientEntity clientEntity = ClientEntity.builder().build();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        clientEntity.setAddressEntity(EntityDtoMapper.mapToEntity(clientDTO.getAddress()));
        return clientEntity;
    }

    public static AddressDTO mapToDto(AddressEntity addressEntity) {
        AddressDTO addressDTO = new AddressDTO();
//        AddressDTO addressDTO = AddressDTO.builder().build();
        BeanUtils.copyProperties(addressEntity, addressDTO);
        return addressDTO;
    }

    public static AddressEntity mapToEntity(AddressDTO addressDTO) {
        AddressEntity addressEntity = new AddressEntity();
//        AddressEntity addressEntity = AddressEntity.builder().build();
        BeanUtils.copyProperties(addressDTO, addressEntity);
        return addressEntity;
    }

//    public static SmartphoneDTO mapToDto(SmartphoneEntity smartphoneEntity) {
//        // SmartphoneDTO smartphoneDTO = new SmartphoneDTO();
//        SmartphoneDTO smartphoneDTO = SmartphoneDTO.builder().build();
//        BeanUtils.copyProperties(smartphoneEntity, smartphoneDTO);
//        return smartphoneDTO;
//    }
//
//    public static SmartphoneEntity mapToEntity(SmartphoneDTO smartphoneDTO) {
//        //SmartphoneEntity smartphoneEntity = new SmartphoneEntity();
//        SmartphoneEntity smartphoneEntity = SmartphoneEntity.builder().build();
//        BeanUtils.copyProperties(smartphoneDTO, smartphoneEntity);
//        return smartphoneEntity;
//    }

}
