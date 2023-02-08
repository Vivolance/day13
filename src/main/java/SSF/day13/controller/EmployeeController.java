package SSF.day13.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import SSF.day13.model.Employee;
import SSF.day13.repository.EmployeeRepo;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/home")
    public String employeeListPage(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);
        
        return "employeesList";
    }

    @GetMapping("/addnew")
    public String addnew(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employeeadd";
    }

    @PostMapping("/addnew")
    //Object that you define in the form will be map to the employee object.
    public String addEmployee(@ModelAttribute("employee") Employee employeeForm, Model model, BindingResult result) {

        if (result.hasErrors()) {
            return "/addnew";
        }

        Boolean bresult = false;
        bresult = empRepo.save(employeeForm);

        return "redirect:/home";

    }
    
}
