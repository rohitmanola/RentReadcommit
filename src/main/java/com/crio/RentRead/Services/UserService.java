package com.crio.RentRead.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.crio.RentRead.Repository.*;




@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
    }

}