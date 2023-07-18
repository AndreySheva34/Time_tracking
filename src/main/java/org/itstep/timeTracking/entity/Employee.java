package org.itstep.timeTracking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.timeTracking.command.EmployeeCommand;
import org.springframework.security.core.userdetails.User;

@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "card_number")
    private Integer cardNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Department department;

    //@ManyToMany(mappedBy = "user")
    //private User user;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setDepartment(Department department){
        department.getEmployees().add(this);
        this.department = department;
    }

    public static Employee fromEmployee(EmployeeCommand command){
        return new Employee(command.firstName(), command.lastName());
    }
}
