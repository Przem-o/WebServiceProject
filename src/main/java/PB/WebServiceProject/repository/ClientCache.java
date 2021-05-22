//package PB.WebServiceProject.repository;
//
//import PB.WebServiceProject.cache.CacheConfig;
//import PB.WebServiceProject.rest.dto.ClientDTO;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public class ClientCache {
//
//
//    @CachePut(key = "#clientDTO.id", cacheManager = CacheConfig.CLIENT_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.CLIENT_RESPONSE_CACHE_NAME)
//    public ClientDTO saveClientResponseInCache(ClientDTO clientDTO) {
//        return clientDTO;
//    }
//
//    @Cacheable(key = "#id", cacheManager = CacheConfig.CLIENT_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.CLIENT_RESPONSE_CACHE_NAME)
//    public Optional<ClientDTO> getClientResponse(Long id) {
//        return Optional.empty();
//    }
//
//    @CacheEvict(key = "#id", cacheManager = CacheConfig.CLIENT_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.CLIENT_RESPONSE_CACHE_NAME)
//    public void deleteClientResponseFromCache(Long id) {
//    }
//}
