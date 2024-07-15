package com.example.aluracursos.LiterAlura.principal;

import com.example.aluracursos.LiterAlura.model.Autor;
import com.example.aluracursos.LiterAlura.model.Datos;
import com.example.aluracursos.LiterAlura.model.DatosLibro;
import com.example.aluracursos.LiterAlura.model.Libro;
import com.example.aluracursos.LiterAlura.repository.IAutorRepository;
import com.example.aluracursos.LiterAlura.repository.ILibroRepository;
import com.example.aluracursos.LiterAlura.service.ConsumoAPI;
import com.example.aluracursos.LiterAlura.service.ConvierteDatos;

import java.lang.reflect.Array;
import java.util.*;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private ConvierteDatos conversor = new ConvierteDatos();

    private List<Libro> libros = new ArrayList<>();

    private IAutorRepository autorRepository;

    private ILibroRepository libroRepository;


    public Principal(ILibroRepository libroRepository, IAutorRepository autorRepository){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opción aa través de su número: 
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma                                 
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        return datos.resultados().get(0);
    }

    private void buscarLibroPorTitulo(){
        try {
            DatosLibro datosLibro = getDatosLibro();
            if (datosLibro == null) {
                throw new Exception("Libro no encontrado");
            }
            Libro libro = new Libro(datosLibro);
            Autor autor = autorRepository.findByNombre(datosLibro.autores().get(0).nombre());
            if (autor == null) {
                autorRepository.save(libro.getAutor());
            } else {
                libro.addAutor(autor);
                libro.setAutor(autor);
            }
            this.libroRepository.save(libro);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void listarLibrosRegistrados(){
        List<Libro> libros = this.libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados(){
        List<Autor> autores = this.autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos(){
        System.out.println("Ingrese el año por el que desea buscar: ");
        var anio =(Integer) teclado.nextInt();
        List<Autor> autores = this.autorRepository.autoresVivos(anio);

        if(autores.isEmpty()){
            System.out.println("No se encontraron autores vivos para este año");
        }else{
            autores.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma(){
        List<String> aux = new ArrayList<String>(Arrays.asList("es" , "en", "fr", "pt"));
        String siglas = """
                Ingrese el idioma para buscar los libros:
                es - español
                en - ingles
                fr - frances 
                pt - portugues               
                """;
        System.out.println(siglas);
        var sigla = teclado.nextLine();
        if(aux.stream().anyMatch(sigla::equalsIgnoreCase)){
            List<Libro> librosIdioma = libroRepository.findByIdiomaIgnoreCase(sigla);
            if(librosIdioma.isEmpty()){
                System.out.println("No hay libros para el idioma seleccionado");
            }else{
                librosIdioma.forEach(System.out::println);
            }
        }else{
            System.out.println("Opcion invalida");
        }

    }


}
