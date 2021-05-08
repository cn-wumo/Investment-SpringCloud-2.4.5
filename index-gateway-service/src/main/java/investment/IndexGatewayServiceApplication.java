package investment;

import cn.hutool.core.net.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IndexGatewayServiceApplication {
    public static void main( String[] args ) {
        int port = 8031;
        while(!NetUtil.isUsableLocalPort(port)){
            port++;
        }
        new SpringApplicationBuilder(IndexGatewayServiceApplication.class).properties("server.port=" + port).run(args);
    }
}
