package com.webApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;
    private String name;
    private String job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    //@JsonBackReference // Add this annotation to the non-owner side
    private User user;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(long pid, String name, String job, User user) {
		super();
		this.pid = pid;
		this.name = name;
		this.job = job;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [pid=" + pid + ", name=" + name + ", job=" + job + ", user=" + user + "]";
	}

    
}

