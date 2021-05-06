package investment;

import cn.hutool.core.net.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class IndexCodesApplication {
    public static void main(String[] args) {
        int defaultPort = 8011;
        while(!NetUtil.isUsableLocalPort(defaultPort)){
            defaultPort++;
        }
        new SpringApplicationBuilder(IndexCodesApplication.class).properties("server.port=" + defaultPort).run(args);
    }
}