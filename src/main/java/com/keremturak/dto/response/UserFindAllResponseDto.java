package com.keremturak.dto.response;

import com.keremturak.entity.EUserType;
import com.keremturak.entity.Genre;
import com.keremturak.entity.Movie;
import com.keremturak.entity.MovieComment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserFindAllResponseDto {
    Long id;
    String name;
    String email;
    String surname;
    String phone;
    EUserType userType;
    List<Movie> favmovies;
    List<Genre> favGenre;
    List<String> movieComments;
    Map<String,String> movieContent;
}
