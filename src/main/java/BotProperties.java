import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bot")
public class BotProperties {
    private String token = "ODMxOTQzNjYzOTg2NTQwNTg1.YHcl6g.cHGs0W1SAkDkn4gp8FHbLTvQGCk";

    public String getToken(){
        return token;
    }
}
