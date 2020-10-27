package com.adriano.curso.ws.security.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "restservice"; //tem que ser o mesmo nome da constante da classe AuthorizationServerConfiguration

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(this.RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception { //Define o que continua publico e o que fica restrito para autenticação
        http.logout().logoutSuccessUrl("/")
                .permitAll().invalidateHttpSession(true)
                .clearAuthentication(true)
                .and().authorizeRequests()
                .antMatchers("/api/users/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().denyAll()
                .and().exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
