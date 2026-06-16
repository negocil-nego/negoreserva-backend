package com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.config;

import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.exception.MailjetApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet")
public class MailjetRestErrorsHandler {

    @ExceptionHandler(MailjetApiException.class)
    public ProblemDetail handleMailjetApiException(MailjetApiException ex) {
        var problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_GATEWAY,
                "Falha ao enviar e-mail via Mailjet. Tente novamente."
        );
        problem.setTitle("Erro no serviço de e-mail");
        return problem;
    }
}
