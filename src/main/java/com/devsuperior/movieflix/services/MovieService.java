package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id){
		Optional<Movie> result = repository.findById(id);
		Movie movie = result.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));
		return new MovieDetailsDTO(movie);
	}
	
	@Transactional(readOnly = true)
	public Page<MovieCardDTO> findByGenre(Pageable pageable, String genreId){
		Page<Movie> result = repository.findByGenre(pageable, Long.parseLong(genreId));
		return result.map(movie -> new MovieCardDTO(movie));
	}
}
