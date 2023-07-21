package org.itstep.timeTracking.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.EmployeeCommand;
import org.itstep.timeTracking.entity.Department;
import org.itstep.timeTracking.entity.Employee;
import org.itstep.timeTracking.repository.DepartmentRepository;
import org.itstep.timeTracking.repository.EmployeeRepository;
import org.itstep.timeTracking.security.entity.CustomRole;
import org.itstep.timeTracking.security.entity.CustomUser;
import org.itstep.timeTracking.security.repository.CustomRoleRepository;
import org.itstep.timeTracking.security.repository.CustomUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final CustomUserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomRoleRepository customRoleRepository;
    private final DepartmentRepository departmentRepository;
    @Transactional
    public Employee save(EmployeeCommand employeeCommand) {
        var user = new CustomUser(employeeCommand.login(),passwordEncoder.encode(employeeCommand.password()));
        var userRole  =  customRoleRepository.findByAuthorityLike("%USER%");
        user.addRole(userRole);
        userRepository.save(user);
        Employee employee = new Employee(employeeCommand.firstName(), employeeCommand.lastName());
        employee.setUser(user);

        Optional<Department> optionalDepartment = departmentRepository.findById(employeeCommand.departmentId());
        optionalDepartment.ifPresent(employee::setDepartment);

        return employeeRepository.save(employee);
    }
}
