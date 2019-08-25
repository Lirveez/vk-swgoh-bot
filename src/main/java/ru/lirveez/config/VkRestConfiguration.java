package ru.lirveez.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;

@Configuration
@RequiredArgsConstructor
public class VkRestConfiguration {

    private final Logbook logbook;

    @Bean
    public RestTemplate vkRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(this::createClientHttpRequestFactory)
                .build();
    }

    private HttpComponentsClientHttpRequestFactory createClientHttpRequestFactory() {
        CloseableHttpClient client = HttpClientBuilder.create()
                .addInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
                .addInterceptorLast(new LogbookHttpResponseInterceptor())
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }
}
