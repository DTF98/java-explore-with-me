package ru.DTF98.stats.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ValidSearchDateIntervalValidator implements ConstraintValidator<ValidSearchDateInterval, Object[]> {
    @Override
    public boolean isValid(Object[] value, final ConstraintValidatorContext context) {
        try {
            if (value[0] != null && value[1] != null) {
                LocalDateTime rangeStart = LocalDateTime.parse((String) value[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime rangeEnd = LocalDateTime.parse((String) value[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return rangeEnd.isAfter(rangeStart);
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}