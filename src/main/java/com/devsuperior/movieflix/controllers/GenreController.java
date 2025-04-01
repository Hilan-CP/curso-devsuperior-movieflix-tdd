package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.services.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	private GenreService service;
}
