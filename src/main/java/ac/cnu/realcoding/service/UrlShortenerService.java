package ac.cnu.realcoding.service;

import java.net.URI;

import ac.cnu.realcoding.encoding.Base62Processor;
import ac.cnu.realcoding.repository.URLInformation;
import org.springframework.stereotype.Service;

import ac.cnu.realcoding.configurations.ApplicationConfiguration;
import ac.cnu.realcoding.models.UrlShortenerRequest;
import ac.cnu.realcoding.models.UrlShortenerResponse;
import ac.cnu.realcoding.repository.URLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    private final URLRepository urlRepository;
    private final ApplicationConfiguration applicationConfig;

    public Mono<URI> unshortenUrl(String encoded) {
        // Problem A: Decode encoded String
        // Problem B: Get full url by querying decoded PK.
        // Problem C: if encoded string is invalid or not found return Bad Request
        return Mono.just(Base62Processor.decode(encoded))
                .flatMap(decoded -> urlRepository.findById(decoded))
                .map(URLInformation::getUrl)
                .map(URI::create);
    }

    public Mono<UrlShortenerResponse> shortenUrl(UrlShortenerRequest urlShortenerRequest) {
        // Problem A: Return Bad request for non http:// or https:// scheme
        // Problem B: Insert to database and get PK from database, PK should be auto-generated integer.
        // Problem C: Encode PK by using Base 63.
        // Problem D: Build UrlShortenerResponse with server host and port.
        String url = urlShortenerRequest.getUrl();
        return urlRepository.save(new URLInformation(url))
                .map(URLInformation::getId)
                .map(Base62Processor::encode)
                .map(encoded -> UriComponentsBuilder.newInstance()
                        .scheme("http")
                        .host(applicationConfig.getHost())
                        .port(applicationConfig.getPort())
                        .path(encoded)
                        .toUriString())
                .map(UrlShortenerResponse::of);
    }
}
