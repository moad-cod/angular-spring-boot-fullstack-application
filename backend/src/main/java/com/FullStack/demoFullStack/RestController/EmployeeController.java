package com.FullStack.demoFullStack.RestController;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FullStack.demoFullStack.Entity.Employee;
import com.FullStack.demoFullStack.Exception.ResourceNotFoundException;
import com.FullStack.demoFullStack.Repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // get the employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
        Employee employee = employeeRepository.findById(id) // get employee by id from database using jpaRepository
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id)); // a
        return ResponseEntity.ok().body(employee); // return 200

    }

    // add employee rest api
    @PostMapping("/employees")
    public Employee CreateEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id)); // return 404
        employee.setFirstName(employeeDetails.getFirstName()); // update employee
        employee.setLastName(employeeDetails.getLastName()); // update employee
        employee.setEmailId(employeeDetails.getEmailId()); // update employee
        Employee updatedEmployee = employeeRepository.save(employee); // save employee
        return ResponseEntity.ok(updatedEmployee); // return 200
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id)); // return 404
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>(); // return 200 hash mapping for boolean value
        response.put("deleted", Boolean.TRUE); // return delete true when deleted
        return ResponseEntity.ok(response);
    }
}
