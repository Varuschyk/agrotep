package technikal.task.fishmarket.configuration.security;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(@Nonnull final HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/register", "/login", "/fish", "/", "/images/**")
						.permitAll().anyRequest().authenticated())
				.formLogin(form ->
						form.permitAll().defaultSuccessUrl("/fish"))
				.logout(LogoutConfigurer::permitAll).build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(getUser(), getAdmin());
	}

	private UserDetails getUser() {
		final var passwordEncoder = passwordEncoder();
		return User.withUsername("user")
				.password(passwordEncoder.encode("user"))
				.roles("USER").build();
	}

	private UserDetails getAdmin() {
		final var passwordEncoder = passwordEncoder();
		return User.withUsername("admin")
				.password(passwordEncoder.encode("admin"))
				.roles("ADMIN").build();
	}
}
