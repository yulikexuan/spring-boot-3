//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import sfg6lab.controller.data.BuyerDto;
import sfg6lab.controller.data.BuyerMapper;
import sfg6lab.controller.data.DataMaper;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.service.QuoteService;


@Slf4j
@SpringBootConfiguration
@Import(Sfg6WebMvcCfg.class)
@EnableJdbcRepositories(basePackages = { "sfg6lab.domain.repository" })
public class Sfg6AppCfg {

    @Bean
    DataMaper<Buyer, BuyerDto> buyerMapper() {
        return new BuyerMapper();
    }

    @Bean
    QuoteService buddhaQuoteService() {
        return QuoteService.buddhaQuoteService();
    }

}///:~
