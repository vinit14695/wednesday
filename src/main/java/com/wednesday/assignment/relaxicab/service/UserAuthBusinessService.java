package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.User;
import com.wednesday.assignment.relaxicab.data.entity.UserAuthentication;
import com.wednesday.assignment.relaxicab.repository.UserAuthRepository;
import com.wednesday.assignment.relaxicab.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthBusinessService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    public User getUser(final String authorizationToken) throws AuthorizationFailedException {

        User user = getUserAuthenticationEntity(authorizationToken).getUser();

        return user;
    }

    private UserAuthentication getUserAuthenticationEntity(final String authorizationToken) throws AuthorizationFailedException {

        UserAuthentication userAuthEntity = userAuthRepository.findByAccessToken(authorizationToken);

        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("UABS-001", "User has not signed in");
        }
        if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("UABS-002", "User is signed out.Sign in first.");
        }
        return userAuthEntity;
    }

}