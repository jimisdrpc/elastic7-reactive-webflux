package com.poc.favoritos.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class SugestaoController {

	@Autowired
	SugestaoService sugestaoService;

	@GetMapping("/{id}")
	public Mono<Sugestao> getExtrato(@PathVariable String id) {
		return sugestaoService.findSugestaoById(id);
	}

	// https://stackoverflow.com/a/54687521/4148175
	// 1 - application/json = It will buffer the Flux<YourObject> in memory and
	// serialize it in one pass.
	// 
	// 2 - application/stream+json = flush on the network each element of the Flux
	// input. This behavior is handy when the stream is infinite, or when you want
	// to push information to the client as soon as it's available

	// Delay 0.5 second
	@GetMapping(path = "/search-with-delay/{parte_da_palavra}")
	public Flux<Sugestao> getSugestoesDelay(@PathVariable("parte_da_palavra") String parte_da_palavra) {
		return sugestaoService.findAllMySugestoes("name", parte_da_palavra).delayElements(Duration.ofMillis(500));
	}

	@GetMapping(path = "/search/{parte_da_palavra}")
	public Flux<Sugestao> getSugestoes(@PathVariable("parte_da_palavra") String parte_da_palavra) {
		return sugestaoService.findAllMySugestoes("name", parte_da_palavra);
	}
}
