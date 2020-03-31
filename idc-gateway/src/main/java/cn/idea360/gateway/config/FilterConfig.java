//package cn.idea360.gateway.config;
//
//import cn.idea360.gateway.filter1.AFilter;
//import cn.idea360.gateway.filter1.BFilter;
//import cn.idea360.gateway.filter1.CFilter;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    @Order(-1)
//    public GlobalFilter a() {
//        return new AFilter();
//    }
//
//    @Bean
//    @Order(0)
//    public GlobalFilter b() {
//        return new BFilter();
//    }
//
//    @Bean
//    @Order(1)
//    public GlobalFilter c() {
//        return new CFilter();
//    }
//}
