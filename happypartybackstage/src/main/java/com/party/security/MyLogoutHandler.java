package com.party.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
@Component
public class MyLogoutHandler extends SecurityContextLogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		super.logout(request, response, authentication);
	}

	@Override
	public boolean isInvalidateHttpSession() {
		// TODO Auto-generated method stub
		return super.isInvalidateHttpSession();
	}

	@Override
	public void setInvalidateHttpSession(boolean invalidateHttpSession) {
		// TODO Auto-generated method stub
		super.setInvalidateHttpSession(invalidateHttpSession);
	}

	@Override
	public void setClearAuthentication(boolean clearAuthentication) {
		// TODO Auto-generated method stub
		super.setClearAuthentication(clearAuthentication);
	}
	
}
