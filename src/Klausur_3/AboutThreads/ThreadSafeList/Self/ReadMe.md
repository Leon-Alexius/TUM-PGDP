## Important!
> **Not completely Checked!** <br>
> [List.java](List.java) <br>
> [ListElement.java](ListElement.java) <br>
> [ListIterator.java](ListIterator.java)

---

## About `ReadWriteLock`
> `ReentrantReadWriteLock`: [JavaDocs](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html)

### Question: `WriteLock.lock()` after `WriteLock.lock()`
> it’s perfectly fine to lock the `WriteLock` twice from the same thread. The `ReentrantReadWriteLock` is reentrant, which means that a single thread can acquire the same lock multiple times without blocking itself. This is a feature of Java’s `ReentrantLocks`, including both `ReentrantLock` and `ReentrantReadWriteLock`.
>
> However, you should be careful to ensure that all code paths release the lock the same number of times it was acquired. Otherwise, other threads may be permanently blocked from acquiring the lock. This is why it’s a good practice to use a `try-finally` block when working with locks, as it ensures the lock is released even if an exception is thrown.

### Question: `ReadLock.lock()` after`ReadLock.lock()`
> No, this won't cause any trouble. The `ReentrantReadWriteLock` in Java is reentrant, which means that if a thread already holds the write lock, it can also acquire the read lock without blocking itself. This is because acquiring the read lock while holding the write lock is essentially a downgrade in lock strength, and it's always safe to downgrade a lock.
>
> However, the opposite is not true. If a thread holds a read lock, it cannot acquire the write lock unless it's the only thread that holds the read lock. This is because upgrading from a read lock to a write lock can potentially lead to a deadlock if not handled correctly.
>
> In your case, since the `removeAt()` method acquires the write lock before calling `getListElementAtIndex()`, there won't be any issues with lock acquisition. The `getListElementAtIndex()` method will be able to acquire the read lock without any problems because the same thread already holds the write lock.
>
> Here's a simplified explanation of how it works:
>
> 1. The `removeAt()` method acquires the write lock.
> 2. The `removeAt()` method calls `getListElementAtIndex()`.
> 3. The `getListElementAtIndex()` method acquires the read lock. Since the same thread already holds the write lock, this acquisition is successful.
> 4. The `getListElementAtIndex()` method releases the read lock and returns.
> 5. The `removeAt()` method continues execution and eventually releases the write lock.
>
> So, your implementation is correct and it won't cause any trouble.
