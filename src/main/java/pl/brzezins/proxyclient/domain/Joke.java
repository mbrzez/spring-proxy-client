package pl.brzezins.proxyclient.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Joke {
    private int id;
    private String type;
    private String setup;
    private String punchline;
}
