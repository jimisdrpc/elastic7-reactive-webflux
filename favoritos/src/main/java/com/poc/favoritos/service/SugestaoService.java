package com.poc.favoritos.service;


import org.springframework.stereotype.Service;

import com.poc.favoritos.model.Sugestao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SugestaoService {

    Mono<Sugestao> findSugestaoById(String id);

	Flux<Sugestao> findAllMySugestoes(String field, String value);
}
