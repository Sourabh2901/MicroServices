package com.sourabh.restwebservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    // Configuring HttpSecurity
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		Disable csrf
        http.csrf(csrf -> csrf.disable());

//		Http Request Filter
        http.authorizeHttpRequests(
                requestMatcher -> requestMatcher
                                    .requestMatchers("/h2-console/**").permitAll()
                                    .requestMatchers("/register").permitAll()
                                    .requestMatchers("/login").permitAll()
                                    .anyRequest().authenticated()
        );


//		Set Session Policy = stateless
        http.sessionManagement(
                sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        //		Adding Jwt Auth filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

//    This will Interact with database and fetch details regarding user
    @Bean
    AuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return authenticationProvider;
    }

//    This bean is to authenticate user is valid or not
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


//    Below UserDetailsService  deals with static values
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.withDefaultPasswordEncoder()
//                                .username("sourabh")
//                                .password("sourabh")
//                                .roles("USER")
//                                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                                .username("admin")
//                                .password("admin")
//                                .roles("ADMIN")
//                                .build();
//        return new InMemoryUserDetailsManager(user ,admin);
//    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
