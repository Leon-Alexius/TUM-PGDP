## `Collectors.groupingByConcurrent()` vs `Colelctors.groupingBy`

> The difference between `Collectors.groupingBy` and `Collectors.groupingByConcurrent` lies in the type of `Map` they return and their concurrency behavior.
>
> - `Collectors.groupingBy`: This collector returns a `HashMap` by default, which is not thread-safe. It means that if you use it in a parallel stream, you might encounter race conditions where multiple threads try to modify the map at the same time, leading to unpredictable results.
>
> - `Collectors.groupingByConcurrent`: This collector returns a `ConcurrentHashMap` by default, which is thread-safe. It is designed to handle concurrent modifications from multiple threads, making it a safer choice for parallel streams.
>
> So, if you're working with parallel streams or in a multi-threaded environment, `Collectors.groupingByConcurrent` would be a better choice. However, if you're working with sequential streams or in a single-threaded environment, you can use `Collectors.groupingBy` without any issues.
>
> Please note that `ConcurrentHashMap` may have a slight performance overhead compared to `HashMap` due to the additional synchronization. So, it's recommended to use `ConcurrentHashMap` (and by extension `Collectors.groupingByConcurrent`) only when necessary.
