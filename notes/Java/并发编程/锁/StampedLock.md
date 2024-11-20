StampedLock 是 JDK 1.8 引入的性能更好的读写锁，不可重入且不支持条件变量 Condition。
不同于一般的 Lock 类，StampedLock 并不是直接实现 Lock或 ReadWriteLock接口，而是基于 CLH 锁 独立实现的（AQS 也是基于这玩意）。

StampedLock 提供了三种模式的读写控制模式：读锁、写锁和乐观读

* 写锁：独占锁，一把锁只能被一个线程获得。当一个线程获取写锁后，其他请求读锁和写锁的线程必须等待。类似于 ReentrantReadWriteLock 的写锁，不过这里的写锁是不可重入的。
* 读锁（悲观读）：共享锁，没有线程获取写锁的情况下，多个线程可以同时持有读锁。如果己经有线程持有写锁，则其他线程请求获取该读锁会被阻塞。类似于 ReentrantReadWriteLock 的读锁，不过这里的读锁是不可重入的
* 乐观读：允许多个线程获取乐观读以及读锁。同时允许一个写线程获取写锁。

另外，`StampedLock` 还支持这三种锁在一定条件下进行相互转换 。

```
long tryConvertToWriteLock(long stamp){}
long tryConvertToReadLock(long stamp){}
long tryConvertToOptimisticRead(long stamp){}
```

#### StampedLock 的性能为什么更好？

相比于传统读写锁多出来的乐观读是StampedLock比 ReadWriteLock 性能更好的关键原因。StampedLock 的乐观读允许一个写线程获取写锁，所以不会导致所有写线程阻塞，也就是当读多写少的时候，写线程有机会获取写锁，减少了线程饥饿的问题，吞吐量大大提高。

#### StampedLock 适合什么场景？

和 ReentrantReadWriteLock 一样，StampedLock 同样适合读多写少的业务场景，可以作为 ReentrantReadWriteLock 的替代品，性能更好。
不过，需要注意的是StampedLock不可重入，不支持条件变量 Condition，对中断操作支持也不友好（使用不当容易导致 CPU 飙升——。。如果你需要用到 `ReentrantLock` 的一些高级性能，就不太建议使用 `StampedLock` 了。
