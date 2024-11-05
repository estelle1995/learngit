* JDK1.8之前的HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了节约哈希碰撞
* JDK1.8之后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（或者红黑树的边界值，默认为8）并且当前数组的长度大于64时，此时此索引位置上的所有数据改为使用红黑树存储。

```java
/**
 * 根据key求index的过程
 * 1,先用key求出hash值
 */
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
//2,再用公式index = (n - 1) & hash（n是数组长度）
int hash=hash(key);
index=(n-1)&hash;
```

Hash算法本质上就是三步： 取 key 的 hashCode 值、高位运算、取模运算


解决哈希冲突的三种方法：拉链法、开放地址法、再哈希法

HashMap 使用的就是拉链法

### 开放寻址法

开放寻址法：当哈希碰撞发生时，从发生碰撞的那个单元起，按照一定的次序，从哈希表中寻找一个空闲的单元，然后把发生冲突的元素存入到该单元。这个空闲单元又称为开放单元或空白单元

```
Hi(key)=(H(key) + f(i)) MOD m, i=0,1,2,…, k(k<=m-1)，
```

* 线行探测再散列  f(i) = i
* 平方探测再散列  f(i) = i^2
* 双散列   f(i) = i * hash2(key).   通常 hash2(key) = PRIME - (key % PRIME), 其中 PRIME 是小于散列表大小的质数

### 再哈希法

当发生冲突时，通过使用另一个哈希函数重新计算元素的哈希值
