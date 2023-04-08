package com.bookingwebapppApi.ModelPackage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity

public class Userr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String userId;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String firstname;
    private String lastname;
    private String othername;
    private String gender;
    
    @Column(name = "email", nullable = false, updatable = false)
    private String email;
    private String phone;

    private String imageUrl;

    private Boolean isAccountEnabled;
    private Long failedAttempt;
    private Date lockTime;

    private Boolean isAccountNonLocked;

    private Date dateJoined;

    private Date lastLoginDate;

    private Date lastLoginDateDisplay;

    private String role;

    private String[] authorities;
    
    private Boolean isIdcard = false;
    
    private Boolean isVerified = false;
    
    private String identityType;


    public Userr() {
    }



    public Userr(Long id, String userId, String username, String password, String firstname, String lastname, 
                String othername, String gender, String userType, String email, String phone, String imageUrl, boolean isAccountEnabled,
                Long failedAttempt, Date lockTime, boolean isAccountNonLocked, Date dateJoined, Date lastLoginDate,
                Date lastLoginDateDisplay, String role, String[] authorities) {

        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.othername = othername;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.isAccountEnabled = isAccountEnabled;
        this.failedAttempt = failedAttempt;
        this.lockTime = lockTime;
        this.isAccountNonLocked = isAccountNonLocked;
        this.dateJoined = dateJoined;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDateDisplay = lastLoginDateDisplay;
        this.role = role;
        this.authorities = authorities;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean isAccountEnabled() {
        return isAccountEnabled;
    }

    public void setAccountEnabled(Boolean isAccountEnabled) {
        this.isAccountEnabled = isAccountEnabled;
    }

    public Long getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(Long failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }



	public Boolean getIsIdcard() {
		return isIdcard;
	}



	public void setIsIdcard(Boolean isIdcard) {
		this.isIdcard = isIdcard;
	}



	public Boolean getIsVerified() {
		return isVerified;
	}



	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}



	public String getIdentityType() {
		return identityType;
	}



	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}


}
