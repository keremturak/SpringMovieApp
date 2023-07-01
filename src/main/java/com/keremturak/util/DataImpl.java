package com.keremturak.util;

import com.google.gson.Gson;
import com.keremturak.entity.Movie;
import com.keremturak.entity.MovieComment;
import com.keremturak.entity.User;
import com.keremturak.service.GenreService;
import com.keremturak.service.MovieService;
import com.keremturak.service.UserService;
import com.keremturak.util.data.Sample;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl implements ApplicationRunner {
    private final UserService userService;
    private final MovieService movieService;
    private final GenreService genreService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //saveAllMoviesFromTvMazetoDB();
        //createUser();


    }
    public void saveAllMoviesFromTvMazetoDB(){
        URL url = null;
        try {
            url = new URL("https://api.tvmaze.com/shows");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String value ="";
            value = bufferedReader.readLine();
            Sample[] samples = new Gson().fromJson(value, Sample[].class);
           // Arrays.asList(samples).forEach(System.out::println);

            Arrays.asList(samples).forEach(sample ->
                    {
                        if (sample.network==null) {
                            Movie movie = Movie.builder()
                                    .id(sample.id)
                                    .name(sample.name)
                                    .url(sample.url)
                                    .image(sample.image.medium)
                                    .language(sample.language)
                                    .premiered(LocalDate.parse(sample.premiered))
                                    .summary(sample.summary)
                                    .raiting(sample.rating.average)
                                    .genre(genreService.createGenresWithName(sample.genres))
                                    .build();
                            movieService.save(movie);
                        }else {
                            Movie movie = Movie.builder()
                                    .id(sample.id)
                                    .name(sample.name)
                                    .url(sample.url)
                                    .image(sample.image.medium)
                                    .language(sample.language)
                                    .premiered(LocalDate.parse(sample.premiered))
                                    .summary(sample.summary)
                                    .raiting(sample.rating.average)
                                    .country(sample.network.country.name)
                                    .build();
                            movieService.save(movie);

                        }

                    }
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void createUser(){
        User user5 = User.builder()
                .email("keremturak@gmail.com")
                .name("kerem")
                .password("123")
                .phone("123")
                .surname("turak")
                .favGenre(genreService.createGenresWithName(List.of("Drama","Romance","Horror","Action")))
                .favMovie(movieService.findAllByIds(List.of(1L,10L,50L)))
                .build();
        User user1 = User.builder()
                .email("merve@gmail.com")
                .name("merve")
                .password("123")
                .phone("123")
                .surname("Oz")
                .favGenre(genreService.createGenresWithName(List.of("Drama","Romance","Action")))
                .favMovie(movieService.findAllByIds(List.of(1L,8L,27L,32L)))
                .build();
        user1.setComments(List.of(
                MovieComment.builder().content("iyi").date(LocalDate.now().minusYears(1)).user(user1).movie(movieService.findbyId(78L)).build(),
                MovieComment.builder().content("eh işte").date(LocalDate.now().minusYears(2)).user(user1).movie(movieService.findbyId(10L)).build(),
                MovieComment.builder().content("kötü").date(LocalDate.now().minusYears(4)).user(user1).movie(movieService.findbyId(120L)).build(),
                MovieComment.builder().content("çok iyi").date(LocalDate.now().minusYears(1)).user(user1).movie(movieService.findbyId(78L)).build()
        ));

        userService.saveAll(List.of(user1,user5));
    }


  


}
