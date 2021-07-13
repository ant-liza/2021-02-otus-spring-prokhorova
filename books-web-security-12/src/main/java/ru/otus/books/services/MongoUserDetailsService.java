package ru.otus.books.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.books.model.UserMongo;
import ru.otus.books.model.UserRoles;
import ru.otus.books.repository.UserRepository;
import ru.otus.books.repository.UserRolesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public MongoUserDetailsService(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserMongo userMongo = userRepository.findByUserName(userName);
        if (userMongo == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<UserRoles> usersRoles = userRolesRepository.findByUserName(userName);
        List<GrantedAuthority> authorities = new ArrayList<>();
        usersRoles.forEach(ur -> authorities.add(new SimpleGrantedAuthority(ur.getRole())));
        return new User(userMongo.getUserName(), userMongo.getPassword(), authorities);
    }
}
