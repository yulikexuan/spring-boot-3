package springlab;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.ResolvableType;
import springlab.config.SpringShellConfig;
import springlab.domain.model.CoffeeBrewingType;
import springlab.domain.service.CoffeeBrewService;

import java.util.Map;


@Slf4j
public class SpringShellLabApp {

    public static void main(String[] args) {

        var ctx = new SpringApplicationBuilder(SpringShellConfig.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .build(args)
                .run();

        ObjectProvider<Map<CoffeeBrewingType, CoffeeBrewService>>
                brewingServicesProvider =
                ctx.getBeanProvider(ResolvableType.forClassWithGenerics(
                        Map.class,
                        CoffeeBrewingType.class,
                        CoffeeBrewService.class));


        log.debug(">>> We have {} different brewing methods served now",
                brewingServicesProvider.getIfAvailable().size());
    }

}