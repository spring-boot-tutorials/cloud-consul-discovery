package com.example.consul.discovery;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MyDiscoveryController {

    private final DiscoveryClient discoveryClient;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/discoveryClient")
    public String discoveryPing() throws RestClientException, ServiceUnavailableException {
        URI service = serviceUrl()
                .map(s -> s.resolve("/ping"))
                .orElseThrow(ServiceUnavailableException::new);
        return new RestTemplate().getForEntity(service, String.class)
                .getBody();
    }

    private Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("myApp")
                .stream()
                .findFirst()
                .map(ServiceInstance::getUri);
    }
}
