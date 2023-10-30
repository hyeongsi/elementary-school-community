package com.example.project.dto;

public class UserDTO {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private String education;
	private String school;
	private String education_code;
	private String school_code;
	private String usergrade;
	private String userclass;
	
	
	public UserDTO() {

	}


	public UserDTO(String id, String pwd, String name, String email, String education, String school,
			String education_code, String school_code, String usergrade, String userclass) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.education = education;
		this.school = school;
		this.education_code = education_code;
		this.school_code = school_code;
		this.usergrade = usergrade;
		this.userclass = userclass;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


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


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public String getEducation_code() {
		return education_code;
	}


	public void setEducation_code(String education_code) {
		this.education_code = education_code;
	}


	public String getSchool_code() {
		return school_code;
	}


	public void setSchool_code(String school_code) {
		this.school_code = school_code;
	}


	public String getUsergrade() {
		return usergrade;
	}


	public void setUsergrade(String usergrade) {
		this.usergrade = usergrade;
	}


	public String getUserclass() {
		return userclass;
	}


	public void setUserclass(String userclass) {
		this.userclass = userclass;
	}


	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", education=" + education
				+ ", school=" + school + ", education_code=" + education_code + ", school_code=" + school_code
				+ ", usergrade=" + usergrade + ", userclass=" + userclass + "]";
	}
	

	
	
	
}
	
	