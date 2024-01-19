## `Socket`
> Client sockets (also called just "sockets") is an endpoint for communication between two machines.
> <br>
> [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html)

- Each `Socket` has an `InputStream` and an `OutputStream`. 
- These streams are tied to the socket. **If the socket is closed, the streams will also be closed**.

-------------------------

## `InputStream`
> This abstract class is the superclass of all classes representing an input stream of bytes.
> <br>
> [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html)

- Closing the `InputStream` of a `Socket` using `InputStream.close()` **will close the `Stream` and the `Socket`**. (_Javadocs: Socket_)
- Closing the `InputStream` **will not close any `Reader` that have been created from it**. Any `Reader` would need to be closed separately, any read attempt after closing would result in an `IOException`.
- Once an `InputStream` is closed, it cannot be reopened (since `Socket` is closed). You would need to create a new `Socket` connection to get a new `InputStream`.

-------------------------

## `OutputStream`
> This abstract class is the superclass of all classes representing an output stream of bytes. An output stream accepts output bytes and sends them to some sink.
> <br>
> [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html)

- Closing the `OutputStream` of a `Socket` using `OutputStream.close()` **will close the `Stream` and the `Socket`**. (_Javadocs: Socket_)
- Closing the `OutputStream` **will not close any `Writer` that have been created from it**. Any `Writer` would need to be closed separately, any attempt to write to it after closing would result in an `IOException`.
- Once an `OutputStream` is closed, it cannot be reopened (since `Socket` is closed). You would need to create a new `Socket` connection to get a new `OutputStream`.

-------------------------

## `InputStreamReader`
> An InputStreamReader is a bridge from byte streams to character streams: It reads bytes and decodes them into characters using a specified charset
> <br>
> `InputStreamReader(InputStream in)`
> <br>
> [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/io/InputStreamReader.html)

- Using `.close` will close the `Stream` and releases any system resources associated with it. Thus, closing `InputStreamReader` would close the `Socket`.
- Once the stream has been closed, any invocations will throw an `IOException`.

-------------------------

## `OutputStreamWriter`
> An OutputStreamWriter is a bridge from character streams to byte streams: Characters written to it are encoded into bytes using a specified charset.
> <br>
> `OutputStreamWriter(OutputStream out)`
> <br>
> [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/io/OutputStreamWriter.html)

- Using `.close` will close the `Stream`, **flushing it first**. Thus, closing `OutputStreamWriter` would close the `Socket`.
- Once the stream has been closed, any invocations will throw an `IOException`.

-------------------------

## `BufferedReader`
> `BufferedReader(Reader in)`
> <br>
> `BufferedReader(InputStreamReader in)`
> <br>
> [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html)

- `bufferedReader.close()` **will close the `Reader` or `Stream`**. Thus, will close the `InputStream` and the `Socket`.
- You can have different `BufferedReader` instances reading from the same `InputStream`. However, it's generally not a good idea because the readers may interfere with each other's buffering, leading to inconsistent results.

-------------------------

## `PrintWriter`
> `PrintWriter(Writer out)`
> <br>
> `PrintWriter(OutputStream out)`
> <br>
> [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html)

- `printWriter.close()` **will close the `Writer` or `Stream`**. Thus, will close the `OutputStream` and the `Socket`.

-------------------------

## Conclusion

> "Chain-Reaction" of Resource closing
> - Closing `BufferedReader` -> `InputStreamReader` -> `InputStream` -> `Socket`
> - Closing `PrintWriter` -> `OutputStreamWriter` -> `OutputStream` -> `Socket`
> - Closing `Socket` -> `Output-/InputStream`
