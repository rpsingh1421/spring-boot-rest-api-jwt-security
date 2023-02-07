package com.restapi.adminBackend.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {	
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
	private boolean status=true;
	public boolean getStatus()  {
		// TODO Auto-generated method stub
		return status;
	}
	
}
