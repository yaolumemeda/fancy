package com.fancy.order.facade;


import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
@ComponentScan(basePackages={"com.fancy.order.core","com.fancy.order.service"})
@MapperScan({ "com.fancy.order.dal.mapper"})
@ImportResource(locations = {"classpath:account-dal.xml"})
public class ConfigueApplication {

}
