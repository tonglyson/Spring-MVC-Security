package com.example.springMvcAndSecurity.controller;

import com.example.springMvcAndSecurity.entities.Employee;
import com.example.springMvcAndSecurity.entities.User;
import com.example.springMvcAndSecurity.repositories.EmployeeRepository;
import com.example.springMvcAndSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/home")
    public String home(Model model,Authentication authentication){
        User u =userRepository.findByEmail(authentication.getName()).orElseThrow();
        model.addAttribute("list",u.getEmployees());
        return "main/list";
    }
    @GetMapping("/employee/add")
    public String addPage(Model model){
        model.addAttribute("employee",new Employee());
        return "main/addEmployee";
    }
    @PostMapping("/employee/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, RedirectAttributes rd,Authentication authentication){
        if(result.hasErrors()){
            return "main/addEmployee";
        }
        User u = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("Khong ton tai "));
        employee.setUser(u);
        employeeRepository.save(employee);
        rd.addFlashAttribute("success","Them thanh cong");
        return "redirect:/home";
    }
    @RolesAllowed({"ROLE_USER"})
    @GetMapping("/employee/edit/{id}")
    public String updatePage(@PathVariable("id") int id,Model model){
        Employee e = employeeRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Khong tim thay id"));
        model.addAttribute("employee",e);
        return "main/editEmployee";
    }
    @PostMapping("employee/edit/post")
    public String update(@Valid @ModelAttribute("employee") Employee employee,BindingResult result,RedirectAttributes rd){
        Employee e = employeeRepository.findById(employee.getId()).orElseThrow();
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        e.setEmail(employee.getEmail());
        employeeRepository.save(e);
        rd.addFlashAttribute("success","Update thanh cong");
        return "redirect:/home";
    }
    @GetMapping("employee/delete/{id}")
    public String delete(@PathVariable("id") int id,RedirectAttributes rd){
        Employee e = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(e);
        rd.addFlashAttribute("success","Delete thanh cong");
        return "redirect:/home";
    }
}
