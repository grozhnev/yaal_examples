package ru.yaal.spring.security.application.config.authentication.manager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import ru.yaal.spring.security.application.config.authentication.AuthenticationProfiles;

@Configuration
@Profile(AuthenticationProfiles.AUTHENTICATION_MANAGER_BUILDER)
class AuthenticationManagerBuilderConfig {

	private static final String LOGIN = "u";
	private static final String PASSWORD = "p";
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void authenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(LOGIN).password(PASSWORD).roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("user").password("password")
				.roles("USER").and().withUser("admin").password("password").roles("USER", "ADMIN");
	}
}