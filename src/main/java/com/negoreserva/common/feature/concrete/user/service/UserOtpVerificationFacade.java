package com.negoreserva.common.feature.concrete.user.service;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import com.negoreserva.common.feature.concrete.user.repository.UserOtpVerificationDao;
import com.negoreserva.common.exception.NotFoundException;
import org.springframework.data.domain.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOtpVerificationFacade {
    private final UserOtpVerificationDao userOtpVerificationDao;

    public UserOtpVerification save(UserOtpVerification userOtpVerification) {
        return userOtpVerificationDao.save(userOtpVerification);
    }

    public UserOtpVerification findById(String id) {
       return findById(Long.parseLong(id));
    }

    public UserOtpVerification findById(long id) {
        return userOtpVerificationDao.findById(id).orElseThrow(NotFoundException::new);
    }

    public UserOtpVerification findByTypeAndCode(UserOtpVerification userOtpVerification) {
        return userOtpVerificationDao.findByTypeAndCode(userOtpVerification.getType(), userOtpVerification.getCode())
                .orElseThrow(NotFoundException::new);
    }

    public boolean existsByUser(User user) { return userOtpVerificationDao.existsByUser(user); }

    public boolean existsByCode(String code) { return userOtpVerificationDao.existsByCode(code); }

    public UserOtpVerification findOne(Example<UserOtpVerification> example) {
        return userOtpVerificationDao.findOne(example).orElseThrow(NotFoundException::new);
    }

}
