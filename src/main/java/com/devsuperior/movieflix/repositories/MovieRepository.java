package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(value = "SELECT m FROM Movie m WHERE (:genreId IS NULL OR m.genre.id = :genreId)")
	Page<Movie> findByGenre(Pageable pageable, Long genreId);
}
