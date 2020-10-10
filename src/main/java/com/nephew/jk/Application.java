package com.nephew.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /*  https访问
    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        //将http的访问端口重定向到https的访问端口

        connector.setScheme("http");
        //http的访问端口
        connector.setPort(16666);
        connector.setSecure(false);
        //https的访问端口 就是ssl证书的端口  默认是443
        connector.setRedirectPort(443);
        return connector;
    }
    */
}
