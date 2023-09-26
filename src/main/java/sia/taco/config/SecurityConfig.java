package sia.taco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    // BCryptPasswordEncoder – применяет надежное шифрование bcrypt;
    // NoOpPasswordEncoder – не применяет шифрования;
    // Pbkdf2PasswordEncoder – применяет шифрование PBKDF2;
    // SCryptPasswordEncoder – применяет шифрование Scrypt;
    // StandardPasswordEncoder – применяет шифрование SHA-256

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> userDetails = new ArrayList<>();
        User userMax = new User("max", passwordEncoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        User userAlex = new User("alex", passwordEncoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        userDetails.add(userAlex);
        userDetails.add(userMax);
        return new InMemoryUserDetailsManager(userDetails);
    }
}
