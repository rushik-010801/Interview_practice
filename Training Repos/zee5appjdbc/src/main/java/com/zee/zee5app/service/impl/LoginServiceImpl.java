package com.zee.zee5app.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.impl.LoginRepositoryImpl;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {

	public LoginServiceImpl() throws IOException{
		
	}
	
	LoginRepository loginrepository;
	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		return loginrepository.addCredentials(login);
	}

	@Override
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeRole(String username, ROLE role) {
		// TODO Auto-generated method stub
		return loginrepository.changeRole(username, role);
	}

}
