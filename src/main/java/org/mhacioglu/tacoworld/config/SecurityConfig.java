package org.mhacioglu.tacoworld.config;

import org.mhacioglu.tacoworld.repository.UserRepository;
import org.mhacioglu.tacoworld.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        return new DefaultWebSecurityExpressionHandler();
    }

    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User("buzz", passwordEncoder.encode("password"),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
        usersList.add(new User("woody", passwordEncoder.encode("password"),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
        return new InMemoryUserDetailsManager(usersList);
    }*/

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(("User '" + username + "' not found"))
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CustomOAuth2UserService oAuth2UserService) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/admin'**")
                        .access(new WebExpressionAuthorizationManager("hasRole('ADMIN')"))
                        .requestMatchers("/design", "/orders")
                        .access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                        .requestMatchers("/", "/**")
                        .access(new WebExpressionAuthorizationManager("permitAll()"))
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("usr")
                        .passwordParameter("pwd")
                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/design")
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService)
                        )
                        .defaultSuccessUrl("/design")
                        .permitAll()
                )
                .logout(lout -> lout
                        .logoutSuccessUrl("/login")
                        .logoutUrl("/logout"));
        return http.build();
    }

    /*@Bean
    public SecurityFilterChain anotherFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/design", "/orders")
                        .access(new WebExpressionAuthorizationManager(
                                "hasRole('USER') && " +
                                        "T(java.util.Calendar).getInstance().get("+
                                        "T(java.util.Calendar).DAY_OF_WEEK) == " +
                                        "T(java.util.Calendar).TUESDAY"
                        ))
                        .requestMatchers("/", "/**")
                        .access(new WebExpressionAuthorizationManager("permitAll()"))).build();
    }*/





}
