package edu.xidian.sselab.cloudcourse.hbase;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * lombok 註解
 * @Data   注解再类上，提供类所有属性的gettting和setting方法，此外还提供了equals canEqual /hashCode toString 方法
 * @Setter:注解在属性上，为属性提供setting方法
 * @Getting：注解在属性上，为属性提供getting方法
 * @Log4j:注解在类上，为类提供一个属性名为log的log4j日志对象
 * @NoArgsConstructor:注解在类上，为类提供一个无参的构造方法
 * @AllARGSConstrutor:注解在类上，为类提供一个全参的构造方法
 */
@Data
@Component
@Configuration
public class HbaseProperties {
    
    @Value("${hbase.nodes}")
    private String hbaseNodes;
    
    // solve @Value cannot resolve ${}
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
