package edu.mum.cs.projects.attendance.SecurityConfig;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

		http.csrf().disable();
		http.authorizeRequests().antMatchers("**/**").authenticated().anyRequest().permitAll().and().formLogin()
				.permitAll();
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

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.authorizeRequests().antMatchers("/product",
	// "/login").permitAll().anyRequest().authenticated().and().csrf()
	// .disable().headers().frameOptions().disable().and().formLogin().defaultSuccessUrl("/").permitAll().and()
	// .logout().logoutRequestMatcher(new
	// AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
	// }
	//
	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication().withUser("Ruby").password("12345").roles("ADMIN").and().withUser("Salek")
	// .password("12345").roles("ADMIN");
	// }
}
