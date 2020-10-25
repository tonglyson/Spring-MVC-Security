package com.example.springMvcAndSecurity.controller;

import com.example.springMvcAndSecurity.entities.User;
import com.example.springMvcAndSecurity.entities.UserDto;
import com.example.springMvcAndSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String home(){
        return "login/login";
    }
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userDto",new UserDto());
        return "login/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "login/register";
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        ra.addFlashAttribute("success","Tao tai khoan thanh cong");
        return "redirect:/login?success";
    }
}
