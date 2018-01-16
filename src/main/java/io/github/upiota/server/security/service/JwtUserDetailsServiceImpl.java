package io.github.upiota.server.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.upiota.server.security.JwtUserFactory;
import io.github.upiota.server.sys.entity.User;
import io.github.upiota.server.sys.repository.ResourceRepository;
import io.github.upiota.server.sys.repository.UserRepository;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	List<String> resources = resourceRepository.listResourceByUserId(user.getId());
        	user.setResources(resources);
            return JwtUserFactory.create(user);
        }
    }
}
