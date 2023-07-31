package org.itstep.timeTracking.command;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record NumRunCommand(
        String title,
        LocalDate startDate,
        LocalDate endDate,
        Integer cycle,
        Integer units
) {
}
