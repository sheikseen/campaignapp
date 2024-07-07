package com.jvdm.campaign.security.services;

import com.jvdm.campaign.entity.User;
import com.jvdm.campaign.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User principal = userRepository.findByEmail(username);
    // .orElseThrow(() -> new UsernameNotFoundException("User Not Found with
    // username: " + username));

    return UserDetailsImpl.build(principal);
  }

}
