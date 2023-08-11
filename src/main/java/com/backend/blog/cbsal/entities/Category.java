package com.backend.blog.cbsal.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "category_title", length = 100, nullable = false)
	private String categoryTitle;

	@Column(name = "category_description")
	private String categoryDescription;

	// to make list of posts with 1 to many relationship

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();

}
