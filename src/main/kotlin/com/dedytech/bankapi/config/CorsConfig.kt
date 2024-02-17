package com.dedytech.bankapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {

            override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
                registry.addResourceHandler("/**")
                    .addResourceLocations(*CLASSPATH_RESOURCE_LOCATIONS)
            }

            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("*")
            }

            override fun configurePathMatch(configurer: PathMatchConfigurer) {
                configurer.setUseTrailingSlashMatch(true)
            }
        }
    }

    companion object {
        private val CLASSPATH_RESOURCE_LOCATIONS = arrayOf(
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/public/",
            "classpath:/public/**"
        )
    }
}