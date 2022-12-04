package cz.cvut.fit.niam1.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;


@Configuration
public class RestApplicationConfig {

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean
                = new FilterRegistrationBean<>( new ShallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/tour/strong-etag");
        filterRegistrationBean.setName("etagFilter");
        return filterRegistrationBean;
    }

}
