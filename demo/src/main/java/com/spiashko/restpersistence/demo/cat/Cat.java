package com.spiashko.restpersistence.demo.cat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.spiashko.restpersistence.demo.crudbase.View;
import com.spiashko.restpersistence.demo.crudbase.entity.BaseEntity;
import com.spiashko.restpersistence.demo.person.Person;
import com.spiashko.restpersistence.jacksonjpa.entitybyid.EntityByIdDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cat")
public class Cat extends BaseEntity {

    public static final String OWNER = "owner";
    public static final String FATHER = "father";
    public static final String MOTHER = "mother";

    @Id
    @Column(name = "id")
    private UUID id;

    @JsonView({View.Retrieve.class, View.CatCreate.class})
    @NotEmpty
    @Column(name = "name")
    private String name;

    @JsonView({View.Retrieve.class, View.CatCreate.class})
    @NotNull
    @Column(name = "dob")
    private LocalDate dob;

    @EntityByIdDeserialize
    @JsonView({View.Retrieve.class, View.CatCreate.class})
    @NotNull
    @JsonIgnoreProperties(Person.KITTENS)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_owner")
    private Person owner;

    @EntityByIdDeserialize
    @JsonView({View.Retrieve.class, View.CatCreate.class})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_father", updatable = false)
    private Cat father;

    @EntityByIdDeserialize
    @JsonView({View.Retrieve.class, View.CatCreate.class})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_mother", updatable = false)
    private Cat mother;

    @Enumerated(EnumType.STRING)
    @JsonView({View.Retrieve.class, View.CatCreate.class})
    @NotNull
    @Column(name = "gender")
    private Gender gender;

    @JsonIgnoreProperties({Cat.MOTHER, Cat.FATHER})
    @JsonView({View.Retrieve.class})
    @OneToMany(mappedBy = Cat.MOTHER, fetch = FetchType.LAZY)
    private Set<Cat> motherForKids;

    @JsonIgnoreProperties({Cat.FATHER, Cat.MOTHER})
    @JsonView({View.Retrieve.class})
    @OneToMany(mappedBy = Cat.FATHER, fetch = FetchType.LAZY)
    private Set<Cat> fatherForKids;

}
