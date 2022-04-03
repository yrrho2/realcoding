package ac.cnu.realcoding.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ac.cnu.realcoding.configurations.ApplicationConfiguration;
import ac.cnu.realcoding.models.UrlShortenerRequest;
import ac.cnu.realcoding.models.UrlShortenerResponse;
import ac.cnu.realcoding.repository.URLInformation;
import ac.cnu.realcoding.repository.URLRepository;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class UrlShortenerServiceTest {

    @Mock
    private URLRepository urlRepository;

    @Mock
    private ApplicationConfiguration applicationConfig;

    @InjectMocks
    private UrlShortenerService shortenerService;

    @Test
    public void encode() {
        // given
        final String domain = "https://www.google.com";
        UrlShortenerRequest request = new UrlShortenerRequest(domain);
        URLInformation urlInformation = new URLInformation(domain);
        urlInformation.setId(1234567L);
        given(applicationConfig.getHost()).willReturn("real-coding.com");
        given(applicationConfig.getPort()).willReturn("8080");
        given(urlRepository.save(any())).willReturn(Mono.just(urlInformation));

        // when
        UrlShortenerResponse res = shortenerService.shortenUrl(request).block();

        // then
        assertThat(res.getUrl()).isEqualTo("http://real-coding.com:8080/FLKX");
        then(urlRepository).should().save(new URLInformation(domain));
    }

    @Test
    public void decode() {
        // given
        String domain = "https://www.google.com";
        Long pk = 1234567L;
        URLInformation urlInformation = new URLInformation(domain);
        urlInformation.setId(pk);
        given(urlRepository.findById(eq(pk))).willReturn(Mono.just(urlInformation));

        // when
        URI res = shortenerService.unshortenUrl("FLKX").block();

        // then
        assertThat(res.toString()).isEqualTo(domain);
        then(urlRepository).should().findById(pk);
    }
}
