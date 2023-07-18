package org.itstep.timeTracking.command;

import org.itstep.timeTracking.entity.Department;

public record DepartmentCommand(String title, Department parentId) {
}

