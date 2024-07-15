package com.example.aluracursos.LiterAlura.repository;

import com.example.aluracursos.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);
    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.anoNacimiento AND a.anoMuerte")
    List<Autor> autoresVivos(Integer anio);
}
