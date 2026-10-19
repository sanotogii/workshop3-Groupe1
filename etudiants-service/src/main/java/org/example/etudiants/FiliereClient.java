package org.example.etudiants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class FiliereClient {

    private final RestClient restClient;

    public FiliereClient(RestClient.Builder restClientBuilder,
            @Value("${services.filieres.url}") String filieresServiceUrl) {
        this.restClient = restClientBuilder.baseUrl(filieresServiceUrl).build();
    }

    public FiliereDto findById(Long filiereId) {
        try {
            return restClient.get()
                    .uri("/api/filieres/{id}", filiereId)
                    .retrieve()
                    .onStatus(status -> status == HttpStatus.NOT_FOUND, (request, response) -> {
                        throw new FiliereNotFoundException(filiereId);
                    })
                    .onStatus(HttpStatusCode::isError, (request, response) -> {
                        throw new FiliereUnavailableException(filiereId);
                    })
                    .body(FiliereDto.class);
        } catch (ResourceAccessException exception) {
            throw new FiliereUnavailableException(filiereId, exception);
        }
    }
}
