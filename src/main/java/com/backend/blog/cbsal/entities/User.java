package com.backend.blog.cbsal.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "user_email", nullable = false)
	private String email;

	@Column(name = "user_password", nullable = false, length = 100)
	private String password;

	@Column(name = "user_about")
	private String about;
	
	//many to 1 post
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();
}
