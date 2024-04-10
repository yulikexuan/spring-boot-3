//: sfg6lab.domain.service.ProfileId2JsonArrayConverter.java


package sfg6lab.domain.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@CacheConfig(cacheNames = { ProfileId2JsonArrayConverter.PROFILE_ID_ARRAY_CACHE_NAME })
public class ProfileId2JsonArrayConverter {

    public static final String PROFILE_ID_ARRAY_CACHE_NAME =
            "sfg6lab.domain.service.ProfileId2JsonArrayConverter";

    /*
     * If the result—it's a String here—has less than 100 characters
     * then it's a veto, and the string should not be cached
     *
     * Safe Navigation
     *   - SpEL supports safe navigation using ?..
     *     It means if a null value is returned at any point in the navigation
     *     chain, the entire expression will return null
     *     For example, condition = "#result?.length() < 10"
     *       - this won't throw NullPointerException if result is null
     */
    @Cacheable(
            condition = "#ids.size() > 3",
            unless = "#result == null || #result.length() < 10"
    )
    public String hotAsJson(List<Long> ids) {

        log.debug(">>> Generating JSON Array for {}", ids.toString());

        var jsonArr = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(
                        ", ", "[", "]"));

        log.debug(">>> The Json Arr is {}", jsonArr);

        return jsonArr;
    }

}///:~