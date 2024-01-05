package com.hotel.elastichotelsyncservice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.Collections;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.hotel.elastichotelsyncservice.Repository")
@ComponentScan(basePackages = { "com.hotel.elastichotelsyncservice" })
public class ElasticsearchClientConfig  {
    /*@Bean
    public RestHighLevelClient client() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Elastic-Product", "Elasticsearch");
        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo("elasticsearch-service:9200")
                        .withDefaultHeaders(headers)
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }*/
}
