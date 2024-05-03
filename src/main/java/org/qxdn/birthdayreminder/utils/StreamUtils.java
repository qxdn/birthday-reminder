package org.qxdn.birthdayreminder.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtils {
    public static <T, R> List<R> map(Collection<T> collection, Function<T,R> convertor) {
        return collection.stream()
                .map(convertor)
                .collect(Collectors.toList());
    }

    public static <T> boolean anyMatch(Collection<T> collection, Predicate<T> predicate){
        return collection.stream()
                .anyMatch(predicate);
    }
}
