package com.keremturak.Repository;

import com.keremturak.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieCommentRepository extends JpaRepository<MovieComment,Long> {

    //1
    List<MovieComment> findAllByMovie_Id(Long id);
    List<MovieComment> findAllByMovie_Name(String name);
    //2
    @Query("select m from MovieComment m where m.movie.name=?1 and m.date > ?2 and m.date < ?3")
    List<MovieComment> findByDate(String name, LocalDate fdate, LocalDate ldate);
    List<MovieComment> findAllByDateBetweenAndMovie_Id(LocalDate fdate, LocalDate ldate,Long id);
    //3
    List<MovieComment> findAllByUser_Id(Long userId);
    //4
    List<MovieComment> findAllByDateBetweenAndUser_Id(LocalDate fdate, LocalDate ldate,Long id);

    //5
    List<MovieComment> findAllByContentContainingIgnoreCase(String value);
    //6
    @Query("select m from MovieComment m where length(m.content)>:length")
    List<MovieComment> findByContentLengthGreaterThan(int length);


}
