package africa.semicolon.lumexpress.security.config;


import africa.semicolon.lumexpress.security.filters.LumExpressAuthorizationFilter;
import africa.semicolon.lumexpress.security.filters.LumExpressUserAuthenticationFilter;
import africa.semicolon.lumexpress.security.jwt.JwtUtil;
import africa.semicolon.lumexpress.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomAuthenticationManager customAuthenticationManager;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter filter =
                new LumExpressUserAuthenticationFilter(customAuthenticationManager, jwtUtil);
        filter.setFilterProcessesUrl("/api/v1/customer/login");

        return http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/customer/register", "/api/v1/customer/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/customer/all").hasAnyAuthority("BUY")
                .and()
                .addFilter(filter)
                .addFilterBefore(new LumExpressAuthorizationFilter(), LumExpressUserAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .build();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }




}
