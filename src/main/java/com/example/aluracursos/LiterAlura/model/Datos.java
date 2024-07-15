package com.example.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("count") Integer count,
        @JsonAlias("next") String next,

        @JsonAlias("previous") String previous,

        @JsonAlias("results") List<DatosLibro> resultados
) {
}
