package ac.cnu.realcoding.models;

import lombok.Value;

@Value(staticConstructor = "of")
public class UrlShortenerResponse {
    private String url;
}
