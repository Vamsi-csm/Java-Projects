package com.vamsi.employees;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @NotBlank @Email @Column(unique = true) private String email;
    @NotBlank private String department;
    private String role;
    @PositiveOrZero private BigDecimal salary;

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String v) { name = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { email = v; }
    public String getDepartment() { return department; }
    public void setDepartment(String v) { department = v; }
    public String getRole() { return role; }
    public void setRole(String v) { role = v; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal v) { salary = v; }
}
