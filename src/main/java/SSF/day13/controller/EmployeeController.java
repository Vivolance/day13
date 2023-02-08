package SSF.day13.controller;

import java.io.FileNotFoundException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import SSF.day13.model.Employee;
import SSF.day13.repository.EmployeeRepo;
import jakarta.validation.Valid;

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
    //Model model should be at the last
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model model) throws FileNotFoundException {

        if (result.hasErrors()) {
            return "employeeadd";
        }

        Boolean bresult = false;
        bresult = empRepo.save(employeeForm);

        //Return to link in requestmapping first
        return "redirect:/employees/home";

    }

    @GetMapping("/deleteEmployee/{email}")
    public String delEmployee(@PathVariable("email") String email) {

        Employee emp = empRepo.findByEmailId(email);

        Boolean bresult = empRepo.delete(emp);

        return "redirect:/employees/home";
    }

    @GetMapping("/updateEmployee/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model) {

        Employee emp = empRepo.findByEmailId(email);
        model.addAttribute("employee", emp);

        return "employeeupdate";
    }
    @PostMapping("/updateEmp")
    public String updateEmployeeProcess(@ModelAttribute("employee") Employee emp, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "employeeupdate";
        }

        empRepo.updateEmployee(emp);
        return "redirect:/employees/home";


    }
    
}
