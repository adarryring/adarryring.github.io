## com.xh.java
- concurrent 并发
    - atomicity 原子性
    - lock
        - sync synchronized 关键字
- objectheader 对象头
- proxy 代理
- socket 连接
- util 工具类

#### proxy spring AOP
```
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       // BeforeAdvice
       Object retVal = null;
       try {
           // AroundAdvice
           retVal = method.invoke(target, args);
           // AroundAdvice
           // AfterReturningAdvice
       }
       catch (Throwable e) {
           // AfterThrowingAdvice
       }
       finally {
           // AfterAdvice
       }
       return retVal;
   }
```

#### scanner 无法输入
- 去 IDEA 的 bin 目录 idea64.exe.vmoptions
- 添加 VM 参数：`-Deditable.java.test.console=true`
- 重启。。

## javap
- 栈帧：用于支持虚拟机进行方法调用和方法执行的数据结构
- 每一个栈帧都包括了局部变量表、 操作数栈、 动态连接、 方法返回地址和一些额外的附加信息。
- flags 方法标志
- Code 字节码指令
    - stack 操作数栈的深度
    - locals 局部变量表的深度
    - args_size 方法参数个数
    - 程序计数器：操作码 （操作数） // （操作数的字面量）
    - Exception table 异常表