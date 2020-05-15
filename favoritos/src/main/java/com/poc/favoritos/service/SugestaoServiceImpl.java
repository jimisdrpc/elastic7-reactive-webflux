package com.poc.favoritos.service;

import com.poc.favoritos.model.Sugestao;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SugestaoServiceImpl implements SugestaoService {

	private static final Logger logger = LoggerFactory.getLogger(SugestaoServiceImpl.class);

	private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;

	private final ReactiveElasticsearchClient reactiveElasticsearchClient;

	public SugestaoServiceImpl(ReactiveElasticsearchOperations reactiveElasticsearchOperations,
			ReactiveElasticsearchClient reactiveElasticsearchClient) {
		this.reactiveElasticsearchOperations = reactiveElasticsearchOperations;
		this.reactiveElasticsearchClient = reactiveElasticsearchClient;
	}

	@Override
	public Mono<Sugestao> findSugestaoById(String id) {
		IndexCoordinates indexCoordinates = IndexCoordinates.of("sugestao");

		return reactiveElasticsearchOperations.get(id, Sugestao.class, indexCoordinates)
				.doOnError(throwable -> logger.error(throwable.getMessage(), throwable));
	}

	@Override
	public Flux<Sugestao> findAllMySugestoes(String field, String value) {
		NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();

		IndexCoordinates indexCoordinates = IndexCoordinates.of("sugestao");

		if (!StringUtils.isEmpty(field) && !StringUtils.isEmpty(value)) {

			query.withQuery(QueryBuilders.matchQuery(field, value));
		}

		Flux<SearchHit<Sugestao>> fluxSearchHits = reactiveElasticsearchOperations.search(query.build(), Sugestao.class,
				indexCoordinates);

		Flux<Sugestao> fluxEntity = fluxSearchHits.map(searchHit -> searchHit.getContent());

		return fluxEntity;

	}

}
