package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService service;
}
