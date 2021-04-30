package investment;

import cn.hutool.core.net.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class IndexGatherStoreApplication {
    public static void main(String[] args) {
        int port = 8001;
        while(!NetUtil.isUsableLocalPort(port)){
            port++;
        }
        new SpringApplicationBuilder(IndexGatherStoreApplication.class).properties("server.port=" + port).run(args);
    }
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}