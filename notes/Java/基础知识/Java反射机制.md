#### 获取 Class 对象的四种方式
```Java
//1. 知道具体类
Class alunbarClass = TargetObject.class;

//2. 知道具体全路径名
Class alunbarClass1 = Class.forName("cn.javaguide.TargetObject");

//3. 通过实例
TargetObject o = new TargetObject();
Class alunbarClass2 = o.getClass();

//4. 通过类加载器
ClassLoader.getSystemClassLoader().loadClass("cn.javaguide.TargetObject");
```
# Java 代理
https://javaguide.cn/java/basis/proxy.html


## CGLIB 动态代理机制
### 介绍
JDK 动态代理有一