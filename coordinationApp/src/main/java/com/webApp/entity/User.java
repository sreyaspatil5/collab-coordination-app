package com.webApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be null")
    private String username;

    @Column(unique = true)
    @NotBlank(message = "email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "address cannot be null")
    @NotBlank(message = "address cannot be null")
    private String address;

    @NotNull(message = "contact cannot be null")
    @NotBlank(message = "contact cannot be null")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must")
    private String contactNo;

    //@NotNull(message = "image cannot be null")
    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference // Add this annotation to the non-owner side
    private Admin admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Add this annotation to the owner side
    private List<Post> posts;
    

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public byte[] getImage() {
        return this.image = this.image;
    }

    public byte[] setImage(byte[] image) {
        return this.image = this.image;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User() {
        super();
    }

    public User(Long id, String username, String email, String address, String contactNo, byte[] image, Admin admin,
            List<Post> posts) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.contactNo = contactNo;
        this.image = this.image;
        this.admin = admin;
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", address=" + address
                + ", contactNo=" + contactNo + ", image=" + image + ", admin=" + admin + ", posts=" + posts + "]";
    }
}
