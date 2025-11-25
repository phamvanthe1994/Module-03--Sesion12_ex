package re.com.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    private static final String CLOUD_NAME = "dr9recst2";
    private static final String API_KEY = "694921818311331";
    private static final String API_SECRET = "kTTkgVBD0MiIV7PReE35Lutua08";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUD_NAME, "api_key", API_KEY, "api_secret", API_SECRET));
    }
}
