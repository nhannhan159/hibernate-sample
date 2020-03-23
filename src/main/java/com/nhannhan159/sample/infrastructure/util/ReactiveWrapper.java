package com.nhannhan159.sample.infrastructure.util;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author tien.tan
 */
@Component
public class ReactiveWrapper {

    public <T> Mono<T> toMono(final Callable<T> callable) {
        return Mono.fromCallable(callable)
            .subscribeOn(Schedulers.boundedElastic());
    }

    public <T> Flux<T> toFlux(final Callable<List<T>> callable) {
        return Mono.fromCallable(callable)
            .subscribeOn(Schedulers.boundedElastic())
            .flatMapMany(Flux::fromIterable);
    }
}
