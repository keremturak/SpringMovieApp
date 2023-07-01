package com.keremturak.service;

import com.keremturak.Repository.IMovieRepository;
import com.keremturak.entity.Genre;
import com.keremturak.entity.Movie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final IMovieRepository repository;
    private final UserService userService;

    public MovieService(IMovieRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public List<Movie> findAllByRaitingGreaterThan(double raiting) {
        return repository.findAllByRaitingGreaterThan(raiting);
    }

    public List<Movie> findAllByIds(List<Long> ids) {

        return ids.stream().map(x -> repository.findById(x).get()).collect(Collectors.toList());
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public Movie findbyId(long id) {

        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else {
            throw new RuntimeException("Movie not found");
        }
    }
    public List<Movie> findMoviesByUserGenres (Long id){
        List<Genre> genreList = userService.findById(id).get().getFavGenre();

        return repository.findAllByGenreInOrderByRaiting(genreList);

    }
    public List<Movie> findAllByPremieredIsLessThan(LocalDate date){
        return repository.findAllByPremieredIsLessThan(date);
    }

    public Object[] findCountRaitingAndRate(double rate){
        return repository.findCountRaitingAndRate(rate);
    }

    public List<Movie> findAllByRaitingEquals7AndRaitingEquals8AndRaitingEquals9(){
        return repository.findAllByRaitingIn(List.of(7D,8D,9D));
    }

    public List<Movie> findAllByChoose(String[] list){
        return repository.findAllByNameIn(list);
    }


}
