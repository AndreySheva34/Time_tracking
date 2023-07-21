package org.itstep.timeTracking.command;

public record EmployeeCommand(
        String firstName,
        String lastName,
        String login,
        String password,
        Integer card,
        Integer departmentId) {
}
