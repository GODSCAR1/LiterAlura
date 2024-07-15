package com.example.aluracursos.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer anoNacimiento;

    private Integer anoMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.anoNacimiento = datosAutor.anoNacimiento();
        this.anoMuerte = datosAutor.anoMuerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoMuerte() {
        return anoMuerte;
    }

    public void setAnoMuerte(Integer anoMuerte) {
        this.anoMuerte = anoMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor: " + nombre + "\n" +
                "Fecha de cacimiento: " + anoNacimiento + "\n" +
                "Fecha de fallecimiento: " + anoMuerte + "\n" +
                "Libros: " + libros.stream().map(Libro::getTitulo).toList() +"\n";

    }
}
