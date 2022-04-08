package com.piedpiper.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.piedpiper.model.Customer;
import com.piedpiper.model.Role;
import com.piedpiper.repository.CustRepo;
import com.piedpiper.repository.RoleRepository;

@Service
public class CustServiceImpl  implements UserDetailsService{
	

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustRepo custRepo;
	
	public Customer findUserByEmail(String email) {
	    return custRepo.findByEmail(email);
	}

	public void saveCustomer(Customer customer) {
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
	    //customer.setEnabled(true);
	    Role userRole = roleRepository.findByRole("ADMIN");
	    customer.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    custRepo.save(customer);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    Customer customer = custRepo.findByEmail(email);
	    if(customer != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(customer.getRoles());
	        return buildUserForAuthentication(customer, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	        
	    }
  }

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(Customer customer, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), authorities);
	}
}
