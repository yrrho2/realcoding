package ac.cnu.realcoding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.cnu.realcoding.models.UrlShortenerRequest;
import ac.cnu.realcoding.models.UrlShortenerResponse;
import ac.cnu.realcoding.service.UrlShortenerService;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("health/{n}")
    public Mono<String> healthCheck(@PathVariable int n) {
        // For basic tutorial
<<<<<<< HEAD

        return Mono.just("Hello World");
=======
        return fib(n).map(String::valueOf);
>>>>>>> upstream/team-11
    }

    private Mono<Integer> fib(int n){
        if(n==0){
            return Mono.just(0);
        }
<<<<<<< HEAD
        if(n==1){
=======
        if(n==1||n==2){
>>>>>>> upstream/team-11
            return Mono.just(1);
        }
        Mono<Integer> f0 = fib(n-1);
        Mono<Integer> f1 = fib(n-2);
<<<<<<< HEAD
        return (Mono.zip(f0, f1, (n0, n1)-> n0 + n1));
=======
        return Mono.zip(f0, f1, (n0, n1) -> n0+n1);
>>>>>>> upstream/team-11
    }


    @GetMapping("{encoded}")
    public Mono<ResponseEntity<Object>> unshorten(@PathVariable String encoded) {
        // Question A: What is difference between HTTPStatus.MOVED_PERMANENTLY and HttpStatus.FOUND (302)
        // Question B: What is the role of location header from HTTP protocol?
        // Question C: Implement unshorten logic
        return urlShortenerService
                .unshortenUrl(encoded)
                .map(uri -> ResponseEntity.status(HttpStatus.FOUND)
                                          .location((URI) uri)
                                          .build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("shorten")
    public Mono<ResponseEntity<UrlShortenerResponse>> createUrlShortener(@RequestBody UrlShortenerRequest urlShortenerRequest) {
        return urlShortenerService.shortenUrl(urlShortenerRequest)
                                  .map(ResponseEntity::ok);
    }
}
