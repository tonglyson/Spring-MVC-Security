package com.example.springMvcAndSecurity.entities;

import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@ScriptAssert(lang = "javascript",script = "_this.confirmPassword.equals(_this.password)",message = "{confirmpassword}")
public class UserDto {
    @NotEmpty(message = "{notnull}")
    private String name;
    @NotEmpty(message = "{notnull}")
    @Email(message = "{isemail}")
    private String email;
    @NotEmpty(message = "{notnull}")
    @Size(min = 4,message = "{password.length}")
    private String password;

    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
