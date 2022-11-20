package com.akash.saison;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
public class SaisonApplication {

	@Value("${slow.rest.template.read.timeout}")
	private Integer SLOW_READ_TIMEOUT;

	@Value("${rest.template.connect.timeout}")
	private Integer CONNECT_TIMEOUT;

	@Value("${rest.template.connect.request.timeout}")
	private Integer CONNECT_REQUEST_TIMEOUT;

	@Value("${rest.template.max.conn.per.route}")
	private Integer MAX_CONN_PER_ROUTE;

	@Value("${rest.template.max.conn}")
	private Integer MAX_CONN;

	public static void main(String[] args) {
		SpringApplication.run(SaisonApplication.class, args);
	}

	@Bean("slowRestTemplate")
	public RestTemplate slowRestTemplate(RestTemplateBuilder builder) {
		HttpComponentsClientHttpRequestFactory requestFactory = getConnectionSettings();
		requestFactory.setReadTimeout(SLOW_READ_TIMEOUT);

		return new RestTemplate(requestFactory);
	}


	private HttpComponentsClientHttpRequestFactory getConnectionSettings() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setDefaultMaxPerRoute(MAX_CONN_PER_ROUTE);
		connectionManager.setMaxTotal(MAX_CONN);
		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory(
						HttpClientBuilder.create().setConnectionManager(connectionManager).build());
		requestFactory.setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT);
		requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
		return requestFactory;
	}
}
