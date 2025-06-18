package com.youssef.productmanagement.service;

import com.youssef.productmanagement.entity.User;
import com.youssef.productmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));
        
        return new CustomUserPrincipal(user);
    }
    
    public static class CustomUserPrincipal implements UserDetails {
        private final User user;
        
        public CustomUserPrincipal(User user) {
            this.user = user;
        }
        
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toList());
        }
        
        @Override
        public String getPassword() {
            return user.getPassword();
        }
        
        @Override
        public String getUsername() {
            return user.getUsername();
        }
        
        @Override
        public boolean isAccountNonExpired() {
            return user.getAccountNonExpired();
        }
        
        @Override
        public boolean isAccountNonLocked() {
            return user.getAccountNonLocked();
        }
        
        @Override
        public boolean isCredentialsNonExpired() {
            return user.getCredentialsNonExpired();
        }
        
        @Override
        public boolean isEnabled() {
            return user.getEnabled();
        }
        
        public User getUser() {
            return user;
        }
        
        public String getFullName() {
            if (user.getFirstName() != null && user.getLastName() != null) {
                return user.getFirstName() + " " + user.getLastName();
            }
            return user.getUsername();
        }
    }
}

