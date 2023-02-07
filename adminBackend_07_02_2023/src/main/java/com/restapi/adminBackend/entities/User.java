package com.restapi.adminBackend.entities;

import java.time.LocalDateTime;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_credentials", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "contact1"),
		@UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String roleCode;
	private String name;
	private String email;
	private String pass;
	private long contact1;
	private long contact2;
	private String aadhar_img;
	private long aadhar_no;
	private String city;
	private String dist;
	private long pincode;
	private String address;
	private String profile_img;
	private boolean status = true;
	@CreationTimestamp
	private LocalDateTime createdon;
	@UpdateTimestamp
	private LocalDateTime updatedon;
	
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	

}
