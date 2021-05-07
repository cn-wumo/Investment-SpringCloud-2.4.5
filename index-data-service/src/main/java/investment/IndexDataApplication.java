package investment;

import cn.hutool.core.net.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class IndexDataApplication {
    public static void main(String[] args) {
        int defaultPort = 8021;
        while(!NetUtil.isUsableLocalPort(defaultPort)){
            defaultPort++;
        }
        new SpringApplicationBuilder(IndexDataApplication.class).properties("server.port=" + defaultPort).run(args);
    }
}