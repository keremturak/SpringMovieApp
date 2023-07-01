package com.keremturak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String surname;
    @Column(length = 50)
    private String email;
    @Column(length = 15)
    private String phone;
    @Column(length = 32)
    private String password;
    @ManyToMany

    private List<Movie> favMovie;
    @ManyToMany

    private List<Genre> favGenre;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    private List<MovieComment> comments;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EUserType userType=EUserType.USER;

}
