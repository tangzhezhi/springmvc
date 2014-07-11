package org.tang.jpa.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.jasig.cas.client.authentication.AttributePrincipal;

public class CustomToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    private String password;
    private String username;
    private AttributePrincipal principal;
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CustomToken() {
    }

    public CustomToken(String password, AttributePrincipal principal
            ) {
        this.password = password;
        this.principal = principal;

    }

    public Object getCredentials() {
        return getPassword();
    }


    public void clear() {
        this.password = null;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


	@Override
	public Object getPrincipal() {
		return principal.getName();
	}

	@Override
	public boolean isRememberMe() {
		return false;
	}

	@Override
	public String getHost() {
		return null;
	}

}
