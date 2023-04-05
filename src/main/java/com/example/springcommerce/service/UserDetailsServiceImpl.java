package com.example.springcommerce.service;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springcommerce.model.UserDetailsImp;
import com.example.springcommerce.repository.UserDetailImpRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailImpRepository userRepository;

    // @Autowired
    // private AuthenticationFacade authenticationFacade;

    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserPrincipal) {
            UserDetailsImp userPrincipal = (UserDetailsImp) authentication.getPrincipal();
            return userPrincipal.getUsername();
        }
        return null;
    }

    public void save(UserDetailsImp userDetailsImp) {
        userRepository.save(userDetailsImp);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetailsImp user = userRepository.findById(username).get();
        System.out.println("Account= " + user);

        if (user == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }

        // EMPLOYEE,MANAGER,..
        String role = user.getRole().name();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        // ROLE_EMPLOYEE, ROLE_MANAGER
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        grantList.add(authority);

        boolean enabled = user.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(user.getUsername(), //
                user.getPassword(), enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);

        return userDetails;
    }
}
