package com.iticbcn.mywebapp.llibresapp.Model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Llibre implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre;

    @Column(nullable = false)
    private String titol;

    @Column(nullable = false)
    private String autor;

    private String editorial;

    private LocalDate datapublicacio;

    @Column(nullable = false, unique = true)
    private String isbn;

    private String tematica;
}
