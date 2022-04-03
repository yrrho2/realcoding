package ac.cnu.realcoding.repository;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class URLInformation {
    @Id
    private Long id;

    public URLInformation(String url) {
    }
}
