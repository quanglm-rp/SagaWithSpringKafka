package com.example.common.util;

import reactor.core.publisher.Mono;

public class ObjectToMono<T> {

    public static <T> Mono<T> toMono(T object)
    {
        return Mono.just(object);
    }
}
