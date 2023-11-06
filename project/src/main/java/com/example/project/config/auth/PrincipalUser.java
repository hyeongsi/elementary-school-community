package com.example.project.config.auth;

import com.example.project.dto.UserDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class PrincipalUser extends User {

    private final String name;
    private final String email;
    private final String education;
    private final String school;
    private final String educationCode;
    private final String schoolCode;
    private final String usergrade;
    private final String userclass;

    public PrincipalUser(UserDTO userDTO, Collection<? extends GrantedAuthority> authorities) {
        super(userDTO.getId(), userDTO.getPwd(), authorities);
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.education = userDTO.getEducation();
        this.school = userDTO.getSchool();
        this.educationCode = userDTO.getEducationCode();
        this.schoolCode = userDTO.getSchoolCode();
        this.usergrade = userDTO.getUsergrade();
        this.userclass = userDTO.getUserclass();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }

    public String getSchool() {
        return school;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public String getUsergrade() {
        return usergrade;
    }

    public String getUserclass() {
        return userclass;
    }

}
