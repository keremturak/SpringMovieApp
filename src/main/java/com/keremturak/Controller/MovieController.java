package com.keremturak.Controller;

import com.keremturak.entity.Movie;
import com.keremturak.service.MovieService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {

        this.service = service;

    }

    @GetMapping("/findallbyraiting")
    public List<Movie> findAllByRaitingGreaterThan(double raiting) {
        return service.findAllByRaitingGreaterThan(raiting);
    }

    @GetMapping("/findmoviesgenres")
    public ResponseEntity<List<Movie>> findMoviesByUserGenres(Long id) {
        return ResponseEntity.ok(service.findMoviesByUserGenres(id));
    }
    @GetMapping("/findlessdate")
    public List<Movie> findAllByPremieredIsLessThan(LocalDate date){
        return service.findAllByPremieredIsLessThan(date);
    }
    @GetMapping("/searchraiting")
    public ResponseEntity<Object[]> findCountRaitingAndRate(double rate){
        return ResponseEntity.ok(service.findCountRaitingAndRate(rate));
    }
    @GetMapping("/789")
    public ResponseEntity<List<Movie>> findAllByRaitingEquals7AndRaitingEquals8AndRaitingEquals9( ){
        return ResponseEntity.ok(service.findAllByRaitingEquals7AndRaitingEquals8AndRaitingEquals9());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> findAllByChoose( String[] list){
        return ResponseEntity.ok(service.findAllByChoose(list));
    }
}
