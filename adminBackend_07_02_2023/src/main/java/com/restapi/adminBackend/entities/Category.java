package com.restapi.adminBackend.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="meds_category")
public class Category {
	
	@Id
	private long id;
	private String name;
	private String code;
	private int status;
	@CreationTimestamp
	private LocalDateTime createdon;
	@UpdateTimestamp
	private LocalDateTime updatedon;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(long id, String name, String code, int status, LocalDateTime createdon, LocalDateTime updatedon) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.status = status;
		this.createdon = createdon;
		this.updatedon = updatedon;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", code=" + code + ", status=" + status + ", createdon="
				+ createdon + ", updatedon=" + updatedon + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getCreatedon() {
		return createdon;
	}
	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}
	public LocalDateTime getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(LocalDateTime updatedon) {
		this.updatedon = updatedon;
	}
	
	
	
}
