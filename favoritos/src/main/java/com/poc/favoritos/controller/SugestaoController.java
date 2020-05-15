package com.poc.favoritos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.favoritos.service.SugestaoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.poc.favoritos.model.*;

@RestController
@RequestMapping("/sugestao")
public class SugestaoController {

	@Autowired
	SugestaoService sugestaoService;

	@GetMapping("/{id}")
	public Mono<Sugestao> getExtrato(@PathVariable String id) {
		return sugestaoService.findSugestaoById(id);
	}

	@GetMapping("/search/{parte_da_palavra}")
	public Flux<Sugestao> getExtratos(@PathVariable("parte_da_palavra") String parte_da_palavra) {
		return sugestaoService.findAllMySugestoes("name", parte_da_palavra);
	}

}
