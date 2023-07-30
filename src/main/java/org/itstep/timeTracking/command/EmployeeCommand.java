package org.itstep.timeTracking.command;

import jakarta.validation.constraints.NotBlank;

public record EmployeeCommand(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String login,
        String password,
        Integer cardNumber,
        Integer departmentId) {
}
