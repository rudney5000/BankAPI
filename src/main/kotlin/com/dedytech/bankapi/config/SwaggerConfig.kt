package com.dedytech.bankapi.config

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class SwaggerConfig {
    @Bean
    fun productApi(): GroupedOpenApi{
        return GroupedOpenApi.builder()
          .group("Product")
            .packagesToScan("com.dedytech.bankapi")
          .pathsToMatch("/**")
          .build()
    }
}