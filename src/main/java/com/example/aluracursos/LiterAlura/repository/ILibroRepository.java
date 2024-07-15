package com.example.aluracursos.LiterAlura.repository;

import com.example.aluracursos.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdiomaIgnoreCase(String sigla);
}
