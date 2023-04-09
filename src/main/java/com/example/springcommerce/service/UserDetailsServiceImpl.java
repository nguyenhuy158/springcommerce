package com.example.springcommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springcommerce.emun.Role;
import com.example.springcommerce.model.UserDetailsImp;
import com.example.springcommerce.repository.UserDetailImpRepository;
import com.example.springcommerce.user.UserNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailImpRepository userDetailImpRepository;

    // @Autowired
    // private AuthenticationFacade authenticationFacade;

    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }

    public UserDetailsImp createUser(UserDetailsImp user) {
        return userDetailImpRepository.save(user);
    }

    public boolean isEmailUnique(String email) {
        UserDetailsImp user = userDetailImpRepository.findById(email).get();
        return user == null;
    }

    public UserDetailsImp getCurrentUser() {
        String username = getCurrentUserId();
        if (username == null) {
            return null;
        }
        return userDetailImpRepository.findById(username).get();
    }

    public void save(UserDetailsImp userDetailsImp) {
        userDetailsImp.setPassword(bCryptPasswordEncoder.encode(userDetailsImp.getPassword()));
        userDetailImpRepository.save(userDetailsImp);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetailsImp user = userDetailImpRepository.findById(username).get();
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean login(String username, String password) {
        Optional<UserDetailsImp> userByUsername = userDetailImpRepository.findById(username);
        if (userByUsername.isPresent()) {
            return userByUsername.get().getPassword()
                    .equals(bCryptPasswordEncoder.encode(password));
        } else {
            return false;
        }
    }

    public boolean isLogin() {
        return getCurrentUser() != null;
    }

    public boolean isAdmin() {
        return getCurrentUser().getRole().name().equals(Role.ADMIN.name());
    }

    public boolean isUsernameUnique(String username) {
        return !userDetailImpRepository.findById(username).isPresent();
    }

    public void saveWithUsernameAndRawPassword(String usename, String password, Role role) {
        UserDetailsImp user = new UserDetailsImp();
        user.setUserName(usename);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setFirstName("Huy");
        user.setLastName("Nguyen");
        user.setPhone("0837083700");
        user.setAddress("19 NHT, Q7, HCM");
        user.setRole(role);
        user.setActive(true);
        userDetailImpRepository.save(user);
    }

    public List<UserDetailsImp> getAllUses() {
        String currentUserId = getCurrentUserId();
        return userDetailImpRepository
                .findAll()
                .stream()
                .filter(
                        arg0 -> !arg0.getUserName().equals(currentUserId))
                .toList();
    }

    public void delete(String usename) throws UserNotFoundException {
        Long count = userDetailImpRepository.countByUserName(usename);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID " + usename);
        }
        userDetailImpRepository.deleteById(usename);
    }

    public UserDetailsImp get(String usename) throws UserNotFoundException {
        Optional<UserDetailsImp> result = userDetailImpRepository.findById(usename);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + usename);
    }

}
