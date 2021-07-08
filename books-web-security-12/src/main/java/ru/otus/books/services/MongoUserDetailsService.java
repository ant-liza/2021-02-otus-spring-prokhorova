package ru.otus.books.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.books.model.UserMongo;
import ru.otus.books.repository.UserRepository;
import ru.otus.books.security.roles.UserRoles;

import java.util.Collections;
import java.util.List;

@Service
public class MongoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserMongo userMongo = userRepository.findByUserName(userName);
        if (userMongo == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(UserRoles.USER.getRoleName()));
        return new User(userMongo.getUserName(), userMongo.getPassword(), authorities);
    }
}
