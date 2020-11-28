package br.com.robertomassoni.xyinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableSpringDataWebSupport
public class XyincApplication implements WebMvcConfigurer {
 
    public static void main(String[] args) {
        SpringApplication.run(XyincApplication.class, args);
    }
    
}
