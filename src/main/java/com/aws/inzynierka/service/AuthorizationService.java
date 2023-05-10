package com.aws.inzynierka.service;

import com.aws.inzynierka.configuration.security.JwtTokenUtil;
import com.aws.inzynierka.configuration.security.UserDetailsImpl;
import com.aws.inzynierka.exception.exceptions.UserExistsException;
import com.aws.inzynierka.model.entity.Role;
import com.aws.inzynierka.model.entity.User;
import com.aws.inzynierka.model.entity.UserRole;
import com.aws.inzynierka.repository.UserRepository;
import com.inzynierka.generated.model.LoginRequest;
import com.inzynierka.generated.model.LoginResponse;
import com.inzynierka.generated.model.RegisterRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class AuthorizationService {

    public AuthorizationService(UserRepository userRepository,@Autowired(required = false) PasswordEncoder passwordEncoder,@Autowired(required = false) AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail()) || userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserExistsException("User with data from register request exists in database");
        }
        User user = new User(registerRequest.getUsername()
                , passwordEncoder.encode(registerRequest.getPassword())
                , registerRequest.getEmail()
                , false,
                true);
        user.getRoles().add(new Role(UserRole.ROLE_USER));
        log.info("Saving user with role: ROLE_USER");
        userRepository.save(user);
        log.info("User saved");
    }


    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        log.info("Returning token for user: " + loginRequest.getUsername());
        return  LoginResponse.builder()
                .authenticationToken(jwt)
                .username(userDetails.getUsername())
                .roles(roles)
                .expiresAt(jwtTokenUtil.getExpiration(jwt))
                .issuedAt(jwtTokenUtil.getIssued(jwt))
                .build();
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

}
