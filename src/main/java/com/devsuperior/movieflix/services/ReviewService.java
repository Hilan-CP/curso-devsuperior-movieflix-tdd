package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		try {
			Review review = new Review();
			Movie movie = movieRepository.getReferenceById(dto.getMovieId());
			User user = userRepository.getReferenceById(dto.getUserId());
			review.setText(dto.getText());
			review.setMovie(movie);
			review.setUser(user);
			review = reviewRepository.save(review);
			return new ReviewDTO(review);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Filme ou usuário inválidos");
		}
	}
}
