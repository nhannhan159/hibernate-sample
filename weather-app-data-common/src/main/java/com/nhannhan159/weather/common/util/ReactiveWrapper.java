package com.nhannhan159.weather.common.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author tien.tan
 */
public class ReactiveWrapper {

    private ReactiveWrapper() {}

    public static <T> Mono<T> toMono(final Callable<T> callable) {
        return Mono.fromCallable(callable)
            .subscribeOn(Schedulers.boundedElastic());
    }

    public static <T> Flux<T> toFlux(final Callable<List<T>> callable) {
        return Mono.fromCallable(callable)
            .subscribeOn(Schedulers.boundedElastic())
            .flatMapMany(Flux::fromIterable);
    }
}
