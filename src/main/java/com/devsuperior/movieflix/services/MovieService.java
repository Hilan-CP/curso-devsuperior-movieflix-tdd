package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Long id = null;
		if(!genreId.isBlank()) {
			id = Long.parseLong(genreId);
		}
		if(pageable.getSort().isUnsorted()) {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
		}
		Page<Movie> result = repository.findByGenre(pageable, id);
		return result.map(movie -> new MovieCardDTO(movie));
	}
}
