package com.tutorial.apidemo.Springboot.demo.database;

import com.tutorial.apidemo.Springboot.demo.models.Product;
import com.tutorial.apidemo.Springboot.demo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {


        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Macbook pro 14 inch", 2022, 2400.0, "");
                Product productB = new Product("Macbook pro 13.3 inch", 2021, 2000.0, "");
                logger.info("Insedrt data: " + productRepository.save(productA));
                logger.info("Insedrt data: " + productRepository.save(productB));
          
            }
        };
    }
}
