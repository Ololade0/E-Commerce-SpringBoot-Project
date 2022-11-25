package africa.semicolon.lumexpress.appconfig;

import africa.semicolon.lumexpress.security.jwt.JwtUtil;
import com.auth0.jwt.algorithms.Algorithm;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class ProjectConfig {
    @Value("${cloudinary.api.name}")
    private  String cloudName;
    @Value("${cloudinary.api.key}")
    private String cloudKey;
    @Value("${cloudinary.api.secret}")
    private String cloudSecret;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;




    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", cloudKey,
                        "api_secret",cloudSecret,
                        "secure", true)
        );
    }


    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(jwtIssuer, Algorithm.HMAC512(jwtSecret));
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
