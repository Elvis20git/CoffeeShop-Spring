package com.Employee3.controller;

import com.Employee3.model.Employee;
import com.Employee3.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }



    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new Employee());
        return "registration_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new Employee());
        return "login_page";
    }



        @PostMapping("/register")
    public String register(@ModelAttribute Employee employee){
            System.out.println("register request :" + employee);
            Employee registeredEmployee = employeeService.registerEmployee(employee.getLogin(), employee.getEmail(), employee.getPassword());
            return registeredEmployee == null ? "error_page" : "redirect:/login";

        }

    @PostMapping("/login")
    public String login(@ModelAttribute Employee employee, Model model){
        System.out.println("login request :" + employee);
        Employee authenticated = employeeService.authenticate(employee.getEmail(), employee.getPassword());
        if(authenticated !=null){
            model.addAttribute("employeeLogin", authenticated.getLogin());
            return "personalAccountPage";
        }else {
            return "error_page";
        }

    }


}
