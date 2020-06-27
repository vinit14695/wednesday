package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.User;
import com.wednesday.assignment.relaxicab.data.entity.UserAuthentication;
import com.wednesday.assignment.relaxicab.repository.UserAuthRepository;
import com.wednesday.assignment.relaxicab.repository.UserRepository;
import com.wednesday.assignment.relaxicab.security.JwtTokenProvider;
import com.wednesday.assignment.relaxicab.security.PasswordCryptographyProvider;
import com.wednesday.assignment.relaxicab.service.exception.AuthenticationFailedException;
import com.wednesday.assignment.relaxicab.service.exception.GenericException;
import com.wednesday.assignment.relaxicab.service.exception.SignOutRestrictedException;
import com.wednesday.assignment.relaxicab.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class
UserBusinessService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;


    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;


    public User createUser(User user) throws SignUpRestrictedException {

        // To check for the condition username already exists
        if (userRepository.findByUsername(user.getUserName()) != null) {
            throw new SignUpRestrictedException("UBS-001", "Try any other Username, this Username has already been taken");
        }

        // To check the condition userEmail already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new SignUpRestrictedException("UBS-002", "This user has already been registered, try with any other emailId");
        }

        //Hashing implementation

        String[] encryptedPassword = passwordCryptographyProvider.encrypt(user.getPassword());
        user.setSalt(encryptedPassword[0]);
        user.setPassword(encryptedPassword[1]);
        userRepository.save(user);
        return user;

    }


    public UserAuthentication authenticateUser(String username, String password) throws AuthenticationFailedException, GenericException {
        User user = userRepository.findByUsername(username);

        //Username does not exist in DB
        if (user == null) {
            throw new AuthenticationFailedException("UBS-003", "This username does not exist");
        }
        String encryptedPassword = passwordCryptographyProvider.encrypt(password, user.getSalt());
        if (encryptedPassword.equals(user.getPassword())) {
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);

            UserAuthentication userAuthentication = new UserAuthentication();
            userAuthentication.setUser(user);

            final Timestamp now = new Timestamp(System.currentTimeMillis());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.HOUR, 8);

            final Timestamp expiresAt = new Timestamp(calendar.getTimeInMillis());

            userAuthentication.setAccessToken(jwtTokenProvider.generateToken(user.getId(), now, expiresAt));
            userAuthentication.setLoginAt(now);
            userAuthentication.setExpiresAt(expiresAt);
            userAuthRepository.save(userAuthentication);

            return userAuthentication;
        } else {
            throw new AuthenticationFailedException("UBS-004", "Password failed");
        }
    }


    public UserAuthentication signOut(String accessToken) throws SignOutRestrictedException {
        UserAuthentication userAuthenticationEntity = userAuthRepository.findByAccessToken(accessToken);
        if (userAuthenticationEntity == null) {
            throw new SignOutRestrictedException("UBS-005", "User is not Signed in");
        }
        userAuthenticationEntity.setLogoutAt(new Timestamp(System.currentTimeMillis()));
        return userAuthRepository.save(userAuthenticationEntity);
    }
}