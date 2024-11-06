AQS 核心思想是如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。如果被请求的共享资源占用，那么就需要一套线程阻塞等待已经被唤醒时锁分配的机制，这个机制 AQS 是基于 CLH 锁 （Craig, Landin, and Hagersten locks） 实现的

![CLH 队列](https://oss.javaguide.cn/github/javaguide/java/concurrent/clh-queue-state.png)

#### AQS资源共享方式

* 独占，只有一个线程能执行，ReentrantLock
* 共享，多个线程可同时执行，Semaphor/CountDownLatch

ReentrantReadWriteLock 同步器同时实现独占和共享两种方式

### 自定义同步器

```Java
//独占方式。尝试获取资源，成功则返回true，失败则返回false。
protected boolean tryAcquire(int)
//独占方式。尝试释放资源，成功则返回true，失败则返回false。
protected boolean tryRelease(int)
//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
protected int tryAcquireShared(int)
//共享方式。尝试释放资源，成功则返回true，失败则返回false。
protected boolean tryReleaseShared(int)
//该线程是否正在独占资源。只有用到condition才需要去实现它。
protected boolean isHeldExclusively()
```

### Semaphore 信号量

`synchronized` 和 `ReentrantLock` 都是一次只允许一个线程访问某个资源，而`Semaphore`(信号量)可以用来控制同时访问特定资源的线程数量。

```Java
// 初始共享资源数量
final Semaphore semaphore = new Semaphore(5);
// 获取1个许可
semaphore.acquire();
// 释放1个许可
semaphore.release();
```

当初始资源个数为1的时候，Semaphore 退化为排他锁

#### Semaphore 有两种模式

* 公平模式：调用 `acquire()` 方法的顺序就是获取许可证的顺序，遵循 FIFO；
* 非公平模式：抢占式

默认非公平模式


### CountDownLatch 倒计时器

CountDownLatch 允许count个线程阻塞在一个地方，直至所有线程的任务都执行完毕

CountDownLatch 是一次性的，计数器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，当 CountDownLatch 使用完毕后，它不能再次使用

#### 原理

CountDownLatch 是共享锁的一种实现，它默认 AQS 的 state
