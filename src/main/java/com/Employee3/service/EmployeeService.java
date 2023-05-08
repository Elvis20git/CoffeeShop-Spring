package com.Employee3.service;

import com.Employee3.model.Employee;
import com.Employee3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private JavaMailSender mailSender;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee registerEmployee(String login, String email, String password){

        if (email == null || password == null) {
            return null;
        } else {
            if(employeeRepository.findFirstByEmail(email).isPresent()){
                System.out.println("Duplicated email");
                return null;
            }
            Employee emp = new Employee();
            emp.setEmail(email);
            emp.setLogin(login);
            emp.setPassword(password);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("murengerantwarielvis03@gmail.com");
            message.setTo(emp.getEmail());
            message.setText("Welcome!! Now You Can Login and Register a new Product !!");
            message.setSubject("Confirmation Message");
            mailSender.send(message);
            return employeeRepository.save(emp);
        }
    }

    public Employee authenticate(String email, String password){
        return employeeRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
