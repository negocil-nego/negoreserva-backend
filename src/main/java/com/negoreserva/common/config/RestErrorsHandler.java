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
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNotFoundException;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductSlugNotFoundException;
import com.negoreserva.common.feature.concrete.product.exception.unique.ProductNameAlreadyExistsException;
import com.negoreserva.common.feature.concrete.product_file.exception.notfound.ProductFileNotFoundException;
import com.negoreserva.common.feature.concrete.user_otp_verification.exception.OtpInvalidException;
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
import com.negoreserva.internal.admin.util.AdminProblemDetailHelpers;
import com.negoreserva.common.feature.core.util.ProblemDetailHelpers;
import com.negoreserva.common.feature.concrete.organization.exception.UserWithoutOrganizationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestErrorsHandler extends ResponseEntityExceptionHandler {

    // -------------------------
    // User
    // -------------------------

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserEmailNotFoundException(UserEmailNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Not found User by email", ex.getMessage());
    }

    @ExceptionHandler(UserPhoneNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserPhoneNotFoundException(UserPhoneNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Not found User by phone", ex.getMessage());
    }

    @ExceptionHandler(UserUsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserUsernameNotFoundException(UserUsernameNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Not found User by username", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserNotFoundException(UserNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Not found User", ex.getMessage());
    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException ex) {
        return ProblemDetailHelpers.conflict("Integrity violation in email", ex.getMessage());
    }

    @ExceptionHandler(UserPhoneAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserPhoneAlreadyExistsException(UserPhoneAlreadyExistsException ex) {
        return ProblemDetailHelpers.conflict("Integrity violation in phone", ex.getMessage());
    }

    @ExceptionHandler(UserUsernameAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserUsernameAlreadyExistsException(UserUsernameAlreadyExistsException ex) {
        return ProblemDetailHelpers.conflict("Integrity violation in username", ex.getMessage());
    }

    @ExceptionHandler(PasswordsDifferentException.class)
    public ResponseEntity<ProblemDetail> handlePasswordsDifferentException(PasswordsDifferentException ex) {
        return ProblemDetailHelpers.conflict("Confirm password", ex.getMessage());
    }

    @ExceptionHandler(PasswordRecoveryProcessIsActiveException.class)
    public ResponseEntity<ProblemDetail> handlePasswordRecoveryProcessIsActiveException(PasswordRecoveryProcessIsActiveException ex) {
        return ProblemDetailHelpers.conflict("Conflict send sms", ex.getMessage());
    }

    // -------------------------
    // Organization
    // -------------------------

    @ExceptionHandler(OrganizationNameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleOrganizationNameNotFoundException(OrganizationNameNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Organization not found by name", ex.getMessage());
    }

    @ExceptionHandler(OrganizationPhoneNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleOrganizationPhoneNotFoundException(OrganizationPhoneNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Organization not found by phone", ex.getMessage());
    }

    @ExceptionHandler(OrganizationEmailAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleOrganizationEmailAlreadyExistsException(OrganizationEmailAlreadyExistsException ex) {
        return ProblemDetailHelpers.conflict("Organization email already exists", ex.getMessage());
    }

    @ExceptionHandler(OrganizationPhoneAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleOrganizationPhoneAlreadyExistsException(OrganizationPhoneAlreadyExistsException ex) {
        return ProblemDetailHelpers.conflict("Organization phone already exists", ex.getMessage());
    }

    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleOrganizationNotFoundException(OrganizationNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Organization not found", ex.getMessage());
    }

    // -------------------------
    // Product
    // -------------------------

    @ExceptionHandler(ProductSlugNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProductSlugNotFoundException(ProductSlugNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Product not found by slug", ex.getMessage());
    }

    @ExceptionHandler(ProductNameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProductNameNotFoundException(ProductNameNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Product not found by name", ex.getMessage());
    }

    @ExceptionHandler(ProductNameAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleProductNameAlreadyExistsException(ProductNameAlreadyExistsException ex) {
        return ProblemDetailHelpers.conflict("Product name already exists", ex.getMessage());
    }

    @ExceptionHandler(ProductFileNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProductFileNotFoundException(ProductFileNotFoundException ex) {
        return ProblemDetailHelpers.notFound("ProductFile not found", ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProductNotFoundException(ProductNotFoundException ex) {
        return ProblemDetailHelpers.notFound("Product not found", ex.getMessage());
    }

    // -------------------------
    // UserOtpVerification
    // -------------------------

    @ExceptionHandler(UserOtpVerificationNotExpiredException.class)
    public ResponseEntity<ProblemDetail> handleUserOtpVerificationNotExpiredException(UserOtpVerificationNotExpiredException ex) {
        return ProblemDetailHelpers.conflict("User with code not expired", ex.getMessage());
    }

    @ExceptionHandler(UserOtpVerificationConfirmException.class)
    public ResponseEntity<ProblemDetail> handleUserOtpVerificationConfirmException(UserOtpVerificationConfirmException ex) {
        return ProblemDetailHelpers.conflict("User code verification, code not equals", ex.getMessage());
    }

    // -------------------------
    // Category
    // -------------------------

    @ExceptionHandler(CategoryNameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNameNotFoundException(CategoryNameNotFoundException ex) {
        return AdminProblemDetailHelpers.notFound("Category not found by name", ex.getMessage());
    }

    @ExceptionHandler(CategoryNameAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNameAlreadyExistsException(CategoryNameAlreadyExistsException ex) {
        return AdminProblemDetailHelpers.conflict("Category name already exists", ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return AdminProblemDetailHelpers.notFound("Category not found", ex.getMessage());
    }

    // -------------------------
    // Generic
    // -------------------------

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ProblemDetail> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Data Integrity Violation");
        var item = ConstraintUniqueList.of(ex).orElseGet(() -> problemDetail);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(item);
    }

    @ExceptionHandler(UserWithoutOrganizationException.class)
    public ResponseEntity<ProblemDetail> handleUserWithoutOrganizationException(UserWithoutOrganizationException ex) {
        return ProblemDetailHelpers.conflict("User without organization", ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFoundException(NotFoundException ex) {
        return ProblemDetailHelpers.notFound(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ProblemDetail> handleUnauthorizedException(UnauthorizedException ex) {
        return ProblemDetailHelpers.conflict("Unauthorized", ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProblemDetail> handleBadRequestException(BadRequestException ex) {
        return ProblemDetailHelpers.conflict("BadRequest", ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ProblemDetail> handleForbiddenException(ForbiddenException ex) {
        return ProblemDetailHelpers.conflict("Forbidden", ex.getMessage());
    }

    @ExceptionHandler(OtpInvalidException.class)
    public ResponseEntity<ProblemDetail> handleOtpInvalidException(OtpInvalidException ex) {
        return ProblemDetailHelpers.conflict("BadRequest", ex.getMessage());
    }

    @ExceptionHandler(SmsException.class)
    public ResponseEntity<ProblemDetail> handleSmsException(SmsException ex) {
        return ProblemDetailHelpers.conflict("Conflict send sms", ex.getMessage());
    }
}