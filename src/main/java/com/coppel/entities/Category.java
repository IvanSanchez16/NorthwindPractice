package com.coppel.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "category_name", length = 100, nullable = false)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"category"})
    private List<Product> products;
}
