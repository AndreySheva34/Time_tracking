package org.itstep.timeTracking.command;

import org.itstep.timeTracking.entity.Department;

public record UserInfoCommand(String firstName, String lastName) {
}
