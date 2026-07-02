package com.negoreserva.common.feature.concrete.user.dto.request;

import com.negoreserva.common.util.RegexValidators;
import jakarta.validation.constraints.Pattern;

public record SendPhoneRequest(@Pattern(regexp = RegexValidators.PATTERN_PHONE, message = "Phone invalid") String input) { }
