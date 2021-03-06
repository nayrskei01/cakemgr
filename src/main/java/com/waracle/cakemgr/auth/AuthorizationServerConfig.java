package com.waracle.cakemgr.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig
    extends AuthorizationServerConfigurerAdapter {
	/**
	 * Configure for security.
	 *
	 * @param security
	 */
	@Override
	public void configure(final AuthorizationServerSecurityConfigurer
									  security) {
		security.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.checkTokenAccess("permitAll()")
		.tokenKeyAccess("permitAll()");
	}

	/**
	 * Client configure details.
	 *
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.inMemory()
			.withClient("guest_app")
			.scopes("READ_ALL_GUEST", "WRITE_GUEST", "UPDATE_GUEST")
			.secret("secret")
			.autoApprove(true)
			.authorities("ROLE_GUEST_AUTHORIZED_CLIENT")
			.authorizedGrantTypes("client_credentials")
			.and()
			.withClient("api_audit")
			.scopes("READ_ALL_GUEST")
			.secret("secret")
			.autoApprove(true)
			.authorities("ROLE_GUEST_AUTHORIZED_CLIENT")
			.authorizedGrantTypes("client_credentials");
	}

	/**
	 * Server endpoints configure.
	 *
	 * @param endpoints
	 */
	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer
	endpoints) {
		endpoints.tokenStore(new InMemoryTokenStore());
	}
}
