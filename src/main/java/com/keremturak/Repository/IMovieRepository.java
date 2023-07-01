package com.keremturak.Repository;

import com.keremturak.entity.Genre;
import com.keremturak.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByRaitingGreaterThan(double raiting);

    List<Movie> findAllByGenreInOrderByRaiting(List<Genre> genres);

    List<Movie> findAllByPremieredIsLessThan(LocalDate date);
    List<Movie> findAllByRaitingIn(List<Double> list);
    List<Movie> findAllByNameIn(String[] list);

    @Query("select count(m.raiting), m.raiting from Movie as m where m.raiting =?1 group by m.raiting")
    Object[] findCountRaitingAndRate(double rate);

}
