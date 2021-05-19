package com.example.fleetapplicationprojectlast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
    private AdminRepository adminRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByEmail(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(username);
        return new CustomUserDetails(admin);
    }
}
