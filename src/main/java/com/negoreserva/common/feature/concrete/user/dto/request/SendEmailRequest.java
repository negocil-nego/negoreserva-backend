package com.negoreserva.common.feature.concrete.user.dto.request;

import jakarta.validation.constraints.Email;

public record SendEmailRequest(@Email String input) { }
