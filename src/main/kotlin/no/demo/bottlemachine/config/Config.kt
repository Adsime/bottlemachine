package no.demo.bottlemachine.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer



@Component
@ConfigurationProperties(prefix = "empty")
class VesselConfig {
    lateinit var threshold: Number
}

@Component
@ConfigurationProperties(prefix = "reimbursement")
class ReimbursementConfig {
    lateinit var can: Number
    lateinit var bottle: Number
}

@Configuration
class WebConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedMethods("*")
    }
}
