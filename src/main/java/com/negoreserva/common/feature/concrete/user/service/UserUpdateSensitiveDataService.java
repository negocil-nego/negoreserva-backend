package com.negoreserva.common.feature.concrete.user.service;

import com.negoreserva.common.exception.BadRequestException;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.concrete.user.exception.OtpInvalidException;
import com.negoreserva.common.feature.concrete.user.dto.request.SendEmailRequest;
import com.negoreserva.common.feature.concrete.user.dto.request.SendPhoneRequest;
import com.negoreserva.common.feature.concrete.user.enums.UserUpdateDataField;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserUpdateSensitiveDataNotFoundException;
import com.negoreserva.common.feature.concrete.user.model.UserUpdateSensitiveData;
import com.negoreserva.common.feature.concrete.user.repository.UserUpdateSensitiveDataRepo;
import com.negoreserva.common.feature.core.dto.request.UpdateDataRequest;
import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import com.negoreserva.common.feature.general.register.util.OtpGenerator;
import com.negoreserva.common.feature.general.sms.model.SmsUserUpdateEmail;
import com.negoreserva.common.feature.general.sms.model.SmsUserUpdatePhone;
import com.negoreserva.common.feature.general.sms.service.SmsUserUpdateEmailDispatcher;
import com.negoreserva.common.feature.general.sms.service.SmsUserUpdatePhoneDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class UserUpdateSensitiveDataService {
    private final SmsUserUpdateEmailDispatcher smsUserUpdateEmailDispatcher;
    private final SmsUserUpdatePhoneDispatcher smsUserUpdatePhoneDispatcher;
    private final UserUpdateSensitiveDataRepo repository;
    private final UserService userService;

    public List<UserUpdateSensitiveData> findAll() {
        return repository.findAll();
    }

    public UserUpdateSensitiveData findById(long id) {
        return repository.findById(id).orElseThrow(() -> new UserUpdateSensitiveDataNotFoundException(id));
    }

    @Transactional
    public UserUpdateSensitiveData save(UserUpdateSensitiveData request) {
        return repository.save(request);
    }

    public boolean reset(UpdateDataRequest request, Authentication authentication) {
        if(!request.id().matches("\\d+")) throw new BadRequestException();
        var user = userService.findBy(authentication);
        var updateSensitiveData = findById(Long.parseLong(request.id()));

        if (!request.otp().equals(updateSensitiveData.getCode())) throw new OtpInvalidException();
        var input = updateSensitiveData.getInput();

        switch (updateSensitiveData.getField()) {
            case EMAIL -> user.setEmail(input);
            case PHONE -> user.setPhone(input);
        }

        updateSensitiveData.setExpiredAt(Instant.now());
        save(updateSensitiveData);
        userService.save(user);
        return true;
    }

    public UserUpdateSensitiveData sendMessage(SendEmailRequest request, Authentication authentication) {
        return sendMessage(
                request.input(),
                authentication,
                UserUpdateDataField.EMAIL,
                otp -> smsUserUpdateEmailDispatcher.dispatch(new SmsUserUpdateEmail(request.input(), otp))
        );
    }

    public UserUpdateSensitiveData sendMessage(SendPhoneRequest request, Authentication authentication) {
        return sendMessage(
                request.input(),
                authentication,
                UserUpdateDataField.PHONE,
                otp -> smsUserUpdatePhoneDispatcher.dispatch(new SmsUserUpdatePhone(request.input(), otp))
        );
    }

    private UserUpdateSensitiveData sendMessage(
            String input,
            Authentication authentication,
            UserUpdateDataField field,
            Consumer<String> dispatcher
    ) {
        var user = userService.findBy(authentication);
        var optional = repository.findByUser(user);
        if (optional.isPresent()) return optional.get();

        var otp = OtpGenerator.generate();
        var expiredAt = ExpiredGenerator.updateDataExpired5Minutes();
        dispatcher.accept(otp);

        return repository.save(UserUpdateSensitiveData.builder()
                .type(OtpVerificationType.UPDATE_DATA)
                .expiredAt(expiredAt)
                .field(field)
                .input(input)
                .user(user)
                .code(otp)
                .build());
    }
}