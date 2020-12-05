package io.github.darryring.learn.java.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xh on 2019-11-09 18:17
 *
 * @explain JDK 动态代理：继承 Proxy 类，实现【代理接口】，实现 equals、hashCode、toString 和【代理接口】的方法，实际上调用的还是 handler 对应的实现。
 */
class JDKProxy2Test {

    class WizardTowerHandler implements InvocationHandler {
        private Object target;

        WizardTowerHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.print(">>>>>>>>>>>> ");
            Object retVal = method.invoke(target, args);
            System.out.println(" <<<<<<<<<<<");
            return retVal;
        }
    }

    /**
     * 1. 进入 `Proxy.newProxyInstance` 源码
     * 2. `Class<?> cl = getProxyClass0(loader, intfs);` 就是动态生成字节码，这个类就是【代理类】
     * 2.1. 进入 `getProxyClass0`，发现有缓存机制 `WeakCache proxyClassCache`，底层使用 `ConcurrentMap` 存储
     * 2.2. `proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());` 查看 `ProxyClassFactory` 代理类生成工厂
     * 2.3. ` byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);` 生成字节码，进入 `sun.misc.ProxyGenerator`
     * 3. `private static final boolean saveGeneratedFiles` 这个变量就是保存代理类的字节码开关，进入 `GetBooleanAction`、`Boolean.getBoolean`，实际上是调用了 `System.getProperty()`
     * 4. 进入 `sun.misc.ProxyGenerator#generateClassFile()`，看下代码了解各大概，也可以通过保存的代理类进行反编译
     * 5. 回到 `Proxy.newProxyInstance` 的返回代码：`return cons.newInstance(new Object[]{h});` 将 handler（类型为 InvocationHandler 接口） 作为【代理类】的构造参数，实例化一个【代理类对象】
     */
    @Test
    void t1() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); // 没生效的话（Junit 的影响），就去 VM args 加 `-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true`

        ProxyDesignPattern1Test t = new ProxyDesignPattern1Test();
        WizardTowerHandler handler = new WizardTowerHandler(t.new IvoryTower());
        ProxyDesignPattern1Test.WizardTower proxy = (ProxyDesignPattern1Test.WizardTower) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{ProxyDesignPattern1Test.WizardTower.class},
                handler);

        /*
         * 1. 查看保存的 `$Proxy9.class`
         * 2. `extends Proxy implements WizardTower` 由于继承了 Proxy，并且 Java 是单继承，所以代理接口时才能用 JDK 动态代理
         * 3. 参数为 `InvocationHandler` 的构造方法，用于装载用户实现的 handler 实例
         * 3. 查看静态代码块，该类通过获取 Method 对象分别实现了 `java.lang.Object` 的 `equals/hashCode/toString` 三个方法，实现方式也是调用了 handler 对应的这三个方法
         * 4. 实现【代理接口】的方法，也就是调用 handler 实现该接口的方法
         * 5. 这样看来，handler 不就是实际上的【代理类】吗？为什么需要生成一堆 proxy 类呢？
         * 6. 参考 spring AOP 切面编程，即在 proxy 中实现了 `BeforeAdvice/AroundAdvice/AroundAdvice/AfterReturningAdvice/AfterThrowingAdvice/AfterAdvice` 这些方法
         */
        t.x1(proxy);
    }
}
