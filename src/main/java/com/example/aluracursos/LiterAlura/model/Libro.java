package com.example.aluracursos.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;

    private String idioma;

    private Double numeroDeDescargas;

    @ManyToOne
    private Autor autor;

    public Libro(){}
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
        this.autor = new Autor(datosLibro.autores().get(0));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void addAutor(Autor autor){
        autor.getLibros().add(this);
    }

    @Override
    public String toString() {
        return "-------Libro-------\n" +
                " Titulo='" + titulo + "\n" +
                " Autor=" + autor.getNombre() + "\n" +
                " Idioma='" + idioma + "\n" +
                " Numero de descargas=" + numeroDeDescargas + "\n" +
                "-------------------\n";
    }
}
