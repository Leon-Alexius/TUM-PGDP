## Thread Pt.1
<ol>
    <li> 
        BasicThread
        <ul>
            <li><code>extends Thread</code> = <code>run()</code></li>
            <li><code>implements Runnable</code> = <code>run()</code></li>
            <li><code>implements Callable</code> = <code>call()</code></li>
        </ul>
    </li>
    <li> ExecutorService </li>
    <li> Future </li>
    <li> Multi Threading </li>
</ol>

## Thread Pt.2
<ol>
    <li> Zustand (join, sleep, etc.) </li>
    <li> Locks (Monitor) </li>
    <li> Producer-Consumer Problem</li>
        <ul>
            <li><code>wait()</code></li>
            <li><code>notify()</code></li>
            <li><code>notifyAll()</code></li>
        </ul>
    <li> Semaphore </li>
</ol>

---

## Important!
> <b>ExecutorService is AutoCloseable in Java 19+</b>
> 
> Starting from Java 19, ExecutorService implements AutoCloseable. 
> 
> The default implementation invokes `shutdown()` and waits for tasks to complete with `awaitTermination()` in a loop. It calls `shutdownNow()` if interrupted.

---

## `wait()` and `notify()`
> The `notify()` and `wait()` methods in Java are part of the object’s monitor, which means they need to be called from a `synchronized` context. 
> <br>
> You can’t call `object.notify()` outside a synchronized block that locks on the `object`.