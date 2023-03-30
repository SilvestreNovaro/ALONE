package com.alone.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Products")
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="categories_id")
    private Categories  categories;


    @ManyToMany
    @JoinTable(name= "products_characteristics", joinColumns = @JoinColumn(name ="product_id"), inverseJoinColumns = @JoinColumn(name ="characteristics_id")
    )
    private List<Characteristics> characteristics;
    // O set? Cual es mejor para este caso?

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="cities_id")
    private Cities cities;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name ="images_id")
    private List<Images> images;

}
