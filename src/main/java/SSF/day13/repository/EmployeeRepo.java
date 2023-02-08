package SSF.day13.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Repository;

import SSF.day13.model.Employee;

@Repository
public class EmployeeRepo {

    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {
        if (employees == null) {
            employees = new ArrayList<Employee>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = df.parse("1980-10-15");
        Employee emp = new Employee("Derrick", "Tan", "derrick@gmail.com",
        "81869283", 7500, dt , "10 Ghim Moh", 272210);
        employees.add(emp);

        dt = df.parse("1979-02-10");
        emp = new Employee("Dennis", "Chew", "dennis@gmail.com", "92387642", 
        8500, dt, "28 Woodlands Drive", 626376);
        employees.add(emp);


    }

    public List<Employee> findAll() {

        return employees;

    }

    public Boolean save(Employee employee) {

        Boolean result = employees.add(employee);
        return result;
    }

    public Boolean delete(Employee employee) {
    // Employee e = employees.stream().filter(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail())).
    // findFirst().get();

    Boolean result = false;
    int employeeIndex = employees.indexOf(employee);

    if (employeeIndex >=0) {
        employees.remove(employeeIndex);
        result = true;
    }

    return result;

    }

    public Employee findByEmailId(String email) {

        Employee emp = employees.stream().filter(e -> e.getEmail().equals(email)).findFirst().get();

        return emp;
    }

    public Boolean updateEmployee(Employee em) {

        Employee emp = employees.stream().filter(e -> e.getEmail().equals(em.getEmail())).findFirst().get();

        int employeeIndex = employees.indexOf(emp);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
        }
        
        employees.add(em);

        return true;



    }
    
}
