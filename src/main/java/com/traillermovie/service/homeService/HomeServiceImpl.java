package com.traillermovie.service.homeService;

import com.traillermovie.model.Director;
import com.traillermovie.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeServiceImpl implements IHomeService {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/trailler_movie";
    private String jdbcUserName = "root";
    private String jdbcPassword = "chinh@240203";
    private static final String SELECT_ALL_MOVIES = "select * from movies;";
    private static final String SELECT_ALL_ACTION_MOVIES = "select * from movies where id_genre = 3;";
    private static final String SELECT_ALL_POPULAR_MOVIES = "select * from movies where rating_movie between 8.8 and 10;";
    private static final String SELECT_ALL_ANIMATION_MOVIES = "select * from movies where id_genre = 4;";
    private static final String SELECT_ALL_NEW_MOVIES = "select * from movies where year_movie > 2004 order by year_movie desc;";
    private static final String SELECT_MOVIE_BY_ID = "select * from movies where id_movie = ?;";
    private static final String SELECT_DIRECTOR_BY_ID_MOVIE = "select dr.id_director, dr.name_director, image_director from directors as dr\n" +
            "join movie_director as md on dr.id_director = md.id_director\n" +
            "where md.id_movie = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public List<Movie> getListActionMovies() {
        List<Movie> actionMovieList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTION_MOVIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_movie");
                String title = resultSet.getString("title_movie");
                double rating = resultSet.getDouble("rating_movie");
                int rank = resultSet.getInt("rank_movie");
                int yearPublic = resultSet.getInt("year_movie");
                String image = resultSet.getString("image_movie");
                String description = resultSet.getString("description_movie");
                String trailer = resultSet.getString("traller_movie");
                actionMovieList.add(new Movie(id, title, rating, rank, yearPublic, image, description, trailer));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actionMovieList;
    }

    public List<Movie> getListPopularMovies() {
        List<Movie> popularMovieList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POPULAR_MOVIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_movie");
                String title = resultSet.getString("title_movie");
                double rating = resultSet.getDouble("rating_movie");
                int rank = resultSet.getInt("rank_movie");
                int yearPublic = resultSet.getInt("year_movie");
                String image = resultSet.getString("image_movie");
                String description = resultSet.getString("description_movie");
                String trailer = resultSet.getString("traller_movie");
                popularMovieList.add(new Movie(id, title, rating, rank, yearPublic, image, description, trailer));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return popularMovieList;
    }

    public List<Movie> getListAnimationMovies() {
        List<Movie> animationMovieList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ANIMATION_MOVIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_movie");
                String title = resultSet.getString("title_movie");
                double rating = resultSet.getDouble("rating_movie");
                int rank = resultSet.getInt("rank_movie");
                int yearPublic = resultSet.getInt("year_movie");
                String image = resultSet.getString("image_movie");
                String description = resultSet.getString("description_movie");
                String trailer = resultSet.getString("traller_movie");
                animationMovieList.add(new Movie(id, title, rating, rank, yearPublic, image, description, trailer));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animationMovieList;
    }

    @Override
    public List<Movie> getListNewMovies() {
        List<Movie> newMovieList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NEW_MOVIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_movie");
                String title = resultSet.getString("title_movie");
                double rating = resultSet.getDouble("rating_movie");
                int rank = resultSet.getInt("rank_movie");
                int yearPublic = resultSet.getInt("year_movie");
                String image = resultSet.getString("image_movie");
                String description = resultSet.getString("description_movie");
                String trailer = resultSet.getString("traller_movie");
                newMovieList.add(new Movie(id, title, rating, rank, yearPublic, image, description, trailer));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newMovieList;
    }

    @Override
    public Movie getMovieById(int id) throws SQLException {
        Movie movie = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title_movie");
                double rating = resultSet.getDouble("rating_movie");
                int rank = resultSet.getInt("rank_movie");
                int yearPublic = resultSet.getInt("year_movie");
                String image = resultSet.getString("image_movie");
                String description = resultSet.getString("description_movie");
                String trailer = resultSet.getString("traller_movie");
                movie = new Movie(id, title, rating, rank, yearPublic, image, description, trailer);
            }
            return movie;
        }
    }

    public List<Director> getDirectorListByIdMovie(int id) throws SQLException {
        List<Director> directorList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIRECTOR_BY_ID_MOVIE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_director = resultSet.getInt("dr.id_director");
                String name = resultSet.getString("dr.name_director");
                String image = resultSet.getString("dr.image_director");
                directorList.add(new Director(id_director, name, image));
            }
            return directorList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
