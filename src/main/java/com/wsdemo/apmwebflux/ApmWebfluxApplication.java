package com.wsdemo.apmwebflux;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApmWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApmWebfluxApplication.class, args);
        ElasticApmAttacher.attach("elasticapm.properties");
    }

}
