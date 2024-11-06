自旋锁缺点

* 第一个是锁饥饿问题。在锁竞争激烈的情况下，可能存在一个线程一直被其他线程”插队“而一直获取不到锁的情况。
* 第二是性能问题。在实际的多处理上运行的自旋锁在锁竞争激烈时性能较差

```java
public class CLH {
    private final ThreadLocal<Node> node = ThreadLocal.withInitial(Node::new);
    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());
  
    public void lock() {
        Node node = this.node.get();
        node.locked = true;
        Node pre = this.tail.getAndSet(node);
        while (pre.locked);
    }
    public void unlock() {
        final Node node  = this.node.get();
        node.locked = false;
    }
    private static class Node {
        private volatile boolean locked;
    }
}
```
