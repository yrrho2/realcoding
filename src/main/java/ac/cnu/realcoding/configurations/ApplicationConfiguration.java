package ac.cnu.realcoding.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ApplicationConfiguration {
    @Value("${server.address}")
    private String host;

    @Value("${server.port}")
    private String port;
}
