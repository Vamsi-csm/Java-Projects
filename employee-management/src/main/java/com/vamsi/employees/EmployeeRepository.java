package com.vamsi.employees;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    @Query("select e from Employee e where lower(e.name) like lower(concat('%', :q, '%')) or lower(e.department) like lower(concat('%', :q, '%'))")
    Page<Employee> search(@Param("q") String q, Pageable pageable);

    @Query("select e.department as department, count(e) as count from Employee e group by e.department")
    List<DepartmentCount> countByDepartment();

    interface DepartmentCount { String getDepartment(); long getCount(); }
}
