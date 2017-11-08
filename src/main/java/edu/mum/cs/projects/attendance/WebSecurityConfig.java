package edu.mum.cs.projects.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.mum.cs.projects.attendance.repository.UsersRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/welcome")
				.access("hasRole('FACULTY') or hasRole('STAFF') or hasRole('STUDENT') or hasRole('ADMIN')").and().csrf()
				.disable().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/welcome").and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return true;
			}
		};
	}
}
