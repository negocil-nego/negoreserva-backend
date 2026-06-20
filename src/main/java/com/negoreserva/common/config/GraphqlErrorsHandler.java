package com.negoreserva.common.config;

import com.negoreserva.common.exception.BadRequestException;
import com.negoreserva.common.exception.ForbiddenException;
import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.exception.UnauthorizedException;
import com.negoreserva.common.feature.concrete.category.exception.notfound.CategoryNameNotFoundException;
import com.negoreserva.common.feature.concrete.category.exception.notfound.CategoryNotFoundException;
import com.negoreserva.common.feature.concrete.category.exception.unique.CategoryNameAlreadyExistsException;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationNameNotFoundException;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationNotFoundException;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationPhoneNotFoundException;
import com.negoreserva.common.feature.concrete.organization.exception.unique.OrganizationEmailAlreadyExistsException;
import com.negoreserva.common.feature.concrete.organization.exception.unique.OrganizationPhoneAlreadyExistsException;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNameNotFoundException;
import com.negoreserva.common.feature.concrete.payment.exception.notfound.PaymentNotFoundException;
import com.negoreserva.common.feature.concrete.payment.exception.unique.PaymentAlreadyExistsException;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNotFoundException;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductSlugNotFoundException;
import com.negoreserva.common.feature.concrete.product.exception.unique.ProductNameAlreadyExistsException;
import com.negoreserva.common.feature.concrete.product_file.exception.notfound.ProductFileNotFoundException;
import com.negoreserva.common.feature.concrete.user_otp_verification.exception.OtpInvalidException;
import com.negoreserva.common.feature.general.sms.config.SmsGraphqlErrorsHandler;
import com.negoreserva.common.feature.general.sms.execption.SmsException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserEmailNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserPhoneNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserUsernameNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.unique.UserEmailAlreadyExistsException;
import com.negoreserva.common.feature.concrete.user.exception.unique.UserPhoneAlreadyExistsException;
import com.negoreserva.common.feature.concrete.user.exception.unique.UserUsernameAlreadyExistsException;
import com.negoreserva.common.feature.general.user_forget_password.exception.PasswordRecoveryProcessIsActiveException;
import com.negoreserva.common.feature.general.user_forget_password.exception.PasswordsDifferentException;
import com.negoreserva.common.feature.concrete.user_otp_verification.exception.UserOtpVerificationConfirmException;
import com.negoreserva.common.feature.concrete.user_otp_verification.exception.UserOtpVerificationNotExpiredException;
import com.negoreserva.common.util.ConstraintUniqueList;
import com.negoreserva.common.feature.concrete.organization.exception.UserWithoutOrganizationException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphqlErrorsHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, @NonNull DataFetchingEnvironment env) {
        return switch (ex) {

            // -------------------------
            // User
            // -------------------------
            case UserEmailNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case UserPhoneNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case UserUsernameNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case UserNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case UserEmailAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            case UserPhoneAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            case UserUsernameAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            case PasswordsDifferentException e -> buildError(ErrorType.FORBIDDEN, e.getMessage(), env);

            case PasswordRecoveryProcessIsActiveException e -> buildError(ErrorType.FORBIDDEN, e.getMessage(), env);

            // -------------------------
            // Organization
            // -------------------------
            case OrganizationNameNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case OrganizationPhoneNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case OrganizationEmailAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            case OrganizationPhoneAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            case OrganizationNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            // -------------------------
            // Product
            // -------------------------
            case ProductSlugNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case ProductNameNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case ProductNameAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            case ProductFileNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case ProductNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            // -------------------------
            // Payment
            // -------------------------
            case PaymentNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case PaymentAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            // -------------------------
            // UserOtpVerification
            // -------------------------
            case UserOtpVerificationNotExpiredException e -> buildError(ErrorType.FORBIDDEN, e.getMessage(), env);

            case UserOtpVerificationConfirmException e -> buildError(ErrorType.FORBIDDEN, e.getMessage(), env);

            // -------------------------
            // Category
            // -------------------------
            case CategoryNameNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case CategoryNameAlreadyExistsException e -> buildError(ErrorType.INTERNAL_ERROR, e.getMessage(), env);

            case CategoryNotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            // -------------------------
            // Generic
            // -------------------------
            case UnauthorizedException e -> buildError(ErrorType.UNAUTHORIZED, e.getMessage(), env);

            case UserWithoutOrganizationException e -> buildError(ErrorType.FORBIDDEN, e.getMessage(), env);

            case ForbiddenException e -> buildError(ErrorType.FORBIDDEN, e.getMessage(), env);

            case BadRequestException e -> buildError(ErrorType.BAD_REQUEST, e.getMessage(), env);

            case NotFoundException e -> buildError(ErrorType.NOT_FOUND, e.getMessage(), env);

            case OtpInvalidException e -> buildError(ErrorType.BAD_REQUEST, e.getMessage(), env);

            case SmsException e -> SmsGraphqlErrorsHandler.resolve(e, env);

            case DataIntegrityViolationException dve -> ConstraintUniqueList.of(dve)
                    .map(it -> buildError(ErrorType.FORBIDDEN, it.getDetail(), env))
                    .orElseGet(() -> buildError(ErrorType.INTERNAL_ERROR, ex.getMessage(), env));
            default -> null;
        };
    }

    private GraphQLError buildError(ErrorType errorType, String message, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .errorType(errorType)
                .message(message)
                .build();
    }
}