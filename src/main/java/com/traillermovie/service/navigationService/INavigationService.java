package com.traillermovie.service.navigationService;

import com.traillermovie.model.Genre;
import com.traillermovie.model.Movie;

import java.util.List;

public interface INavigationService {
    List<Movie> getAllMovie();
    int getNumberPage();
    List<Movie> getMovieListByPage(int index);
    List<Movie> getMovieListByGenre(int genre);
    String getNameGenreById(int genre);
}
