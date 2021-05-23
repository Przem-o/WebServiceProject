package PB.WebServiceProject.cacheConfig;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String CLIENT_RESPONSE_CACHE_MANAGER = "clientResponseCacheManager"; //nazwa klasy+Response...
    public static final String CLIENT_RESPONSE_CACHE_NAME = "clientResponseCache"; //nazwa klasy+Response...

    public static final String PRODUCTS_RESPONSE_CACHE_MANAGER = "productsResponseCacheManager";
    public static final String PRODUCTS_RESPONSE_CACHE_NAME = "productsResponseCache";

    @Primary
    @Bean(CLIENT_RESPONSE_CACHE_MANAGER)
    public CacheManager cacheManagerClient() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(CLIENT_RESPONSE_CACHE_NAME));
        return cacheManager;
    }

    @Bean(PRODUCTS_RESPONSE_CACHE_MANAGER)
    public CacheManager cacheManagerProducts() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(PRODUCTS_RESPONSE_CACHE_NAME));
        return cacheManager;
    }
}


