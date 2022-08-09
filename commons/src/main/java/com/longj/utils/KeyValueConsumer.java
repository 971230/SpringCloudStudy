package com.longj.utils;

import java.util.Objects;

/**
 * @Author 龙江锋
 * @Date 2022/8/3 20:00
 * @Version 1.0
 */
@FunctionalInterface
public interface KeyValueConsumer<T, K, V> {
    void accept(T t, K key, V value);

    default KeyValueConsumer<T, K, V> andThen(KeyValueConsumer<? super T, ? super K, ? super V> after) {
        Objects.requireNonNull(after);

        return (t, k, v) -> {
            accept(t, k, v);
            after.accept(t, k, v);
        };
    }
}
