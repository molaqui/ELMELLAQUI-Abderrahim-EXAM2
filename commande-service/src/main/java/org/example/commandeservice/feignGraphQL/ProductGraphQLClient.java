package org.example.commandeservice.feignGraphQL;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "product-service", url = "http://localhost:8091/graphql")
public interface ProductGraphQLClient {

    @PostMapping
    Map<String, Object> executeGraphQLQuery(@RequestBody Map<String, Object> request);
}
