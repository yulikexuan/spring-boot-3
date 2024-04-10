//: sfg6lab.config.Sfg6AppCfgTest.java

package sfg6lab.config;


import static org.assertj.core.api.Assertions.assertThat;
import static sfg6lab.domain.service.EncryptionService.ENCRYPTION_CACHE_NAME;
import static sfg6lab.domain.service.ProfileId2JsonArrayConverter.PROFILE_ID_ARRAY_CACHE_NAME;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import sfg6lab.domain.service.EncryptionService;
import sfg6lab.domain.service.ProfileId2JsonArrayConverter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@Slf4j
@SpringBootTest(
        classes = { Sfg6AppCfg.class },
        useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
@DisplayName("Test Sfg6AppCfg Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6AppCachingTest {

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
    }

    @Nested
    @DisplayName("Test Caching EncryptionService::encrypt - ")
    class EncryptionServiceTest {

        @Autowired
        private EncryptionService encryptionService;

        @Test
        void encryption_Should_Be_Null_If_Data_Is_Null() {

            // Given

            // When
            var encryptedData = encryptionService.encrypt(null);

            // Then
            assertThat(encryptedData).isEmpty();
        }

        @Test
        void encryption_Should_Not_Be_Null_Or_Empty_If_Data_Is_Empty() {

            // Given
            String data = "";
            var cache = cacheManager.getCache(ENCRYPTION_CACHE_NAME);

            // When
            var encryptedData_1 = encryptionService.encrypt(data);
            var encryptedData_2 = encryptionService.encrypt(data);

            // Then
            assertThat(encryptedData_1).isEqualTo(encryptedData_2)
                    .isNotEmpty();
        }

        @Test
        void always_Add_To_Cache_With_CachePut() {

            // Given
            var newData = "1234567";

            // When
            var encryptedNew_1 = encryptionService.encryptNew(newData);
            var encryptedNew_2 = encryptionService.encryptNew(newData);

            // Then
            assertThat(encryptedNew_1).isNotSameAs(encryptedNew_2);
            assertThat(encryptedNew_1).isNotEqualTo(encryptedNew_2);
        }

        @Test
        void able_To_Remove_Cached_Encryption_Manually() {

            // Given
            var data_1 = "123";
            var data_2 = "456";
            var data_3 = "789";

            var cache = cacheManager.getCache(ENCRYPTION_CACHE_NAME);

            this.encryptionService.encryptNew(data_1);
            this.encryptionService.encryptNew(data_2);
            this.encryptionService.encryptNew(data_3);

            assertThat(cache.get(data_1)).isNotNull();
            assertThat(cache.get(data_2)).isNotNull();
            assertThat(cache.get(data_3)).isNotNull();

            // When
            encryptionService.remove(data_1);
            encryptionService.remove(data_3);

            // Then
            assertThat(cache.get(data_1)).isNull();
            assertThat(cache.get(data_2)).isNotNull();
            assertThat(cache.get(data_3)).isNull();
        }

        @Test
        void able_To_Remove_All_Cached_Encryption_Manually() {

            // Given
            var data_1 = "123";
            var data_2 = "456";
            var data_3 = "789";

            var cache = cacheManager.getCache(ENCRYPTION_CACHE_NAME);

            this.encryptionService.encryptNew(data_1);
            this.encryptionService.encryptNew(data_2);
            this.encryptionService.encryptNew(data_3);

            assertThat(cache.get(data_1)).isNotNull();
            assertThat(cache.get(data_2)).isNotNull();
            assertThat(cache.get(data_3)).isNotNull();

            // When
            encryptionService.removeAll();

            // Then
            assertThat(cache.get(data_1)).isNull();
            assertThat(cache.get(data_2)).isNull();
            assertThat(cache.get(data_3)).isNull();
        }

    } // End of class EncryptionServiceTest

    @Nested
    @DisplayName("Test Caching ProfileId2JsonArrayConverter::hotAsJson - ")
    class ProfileId2JsonArrayConverterTest {

        @Autowired
        private ProfileId2JsonArrayConverter idConverter;

        @Test
        void json_Of_Ids_Can_Not_Be_Cached_If_Ids_Size_Less_Than_Or_Equal_To_Three() {

            // Given
            List<Long> ids = List.of(1L, 2L, 3L);
            String json = idConverter.hotAsJson(ids);
            var cache = cacheManager.getCache(PROFILE_ID_ARRAY_CACHE_NAME);

            // When
            var cachedVal1 = Optional.ofNullable(cache.get(ids))
                    .map(Cache.ValueWrapper::get);
            var cachedVal2 = Optional.ofNullable(cache.get(ids))
                    .map(Cache.ValueWrapper::get);

            // Then
            assertThat(cachedVal1).isEmpty();
            assertThat(cachedVal2).isEmpty();
        }

        @Test
        void json_Of_Ids_Can_Be_Cached_If_Ids_Size_Greater_Than_Three() {

            // Given
            List<Long> ids = List.of(1L, 2L, 3L, 4L);

            String json = idConverter.hotAsJson(ids);
            log.debug(">>> The length of result is {}", json.length());

            var cache = cacheManager.getCache(PROFILE_ID_ARRAY_CACHE_NAME);

            // When
            var cachedVal = Optional.ofNullable(cache.get(ids))
                    .map(Cache.ValueWrapper::get);
            var cachedVal2 = Optional.ofNullable(cache.get(ids))
                    .map(Cache.ValueWrapper::get);

            // Then
            assertThat(cachedVal).hasValue(json);
            assertThat(cachedVal.get()).isSameAs(json).isSameAs(cachedVal2.get());
        }
    }

}///:~