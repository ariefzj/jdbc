package com.amigoscode.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {
  private final JdbcTemplate jdbcTemplate;

  public MovieDataAccessService( JdbcTemplate jdbcTemplate ) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Movie> selectMovies() {
    var sql = """
            select id, name, release_date
            from movie
            limit 100
            """;
    return jdbcTemplate.query( sql, new MovieRowMapper() );
  }

  @Override
  public int insertMovie( Movie movie ) {
    String sql = """
            insert into movie(name, release_date) values (?, ?)
            """;
    return jdbcTemplate.update( sql,
            movie.name(), movie.releaseDate() );
  }

  @Override
  public int deleteMovie( int id ) {
    var sql = """
            delete from movie where id=?
            """;
    return jdbcTemplate.update( sql, id );
  }

  @Override
  public Optional<Movie> selectMovieById( int id ) {
    var sql = """
            select id, name, release_date
            from movie
            where id = ?
            """;
    return jdbcTemplate.query( sql, new MovieRowMapper(), id )
            .stream()
            .findFirst();
  }

}
