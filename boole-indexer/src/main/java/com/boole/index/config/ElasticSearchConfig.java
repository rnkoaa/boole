package com.boole.index.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/26/15.
 */
@Configuration
public class ElasticSearchConfig implements InitializingBean, DisposableBean {
    private Client client;

    @Override
    public void destroy() throws Exception {
        client.close();
    }

    @Bean
    Client client() {
        return client;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "boole-cluster").build();
        client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
    }
    //BulkRequestBuilder
}
