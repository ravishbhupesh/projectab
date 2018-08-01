package com.projectab.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectab.app.jpa.repository.UserRepository;
import com.projectab.app.model.User;

@Service
public class ABUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println("ABUserDetailsService::loadUserByUsername:" + username);
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return new ABUserPrincipal(user.get());
	}
}