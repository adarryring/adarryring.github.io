package io.github.darryring.learn.java.hack.bytes;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by xh on 2019-09-25 20:40
 *
 * @explain 用来代替 java.lang.System 的 HackSystem, 这个类中的
 * 方法看起来不少， 但其实除了把 out 和 err 两个静态变量改成使用 ByteArrayOutputStream 作
 * 为打印目标的同一个 PrintStream 对象， 以及增加了读取、 清理 ByteArrayOutputStream 中
 * 内容的 getBufferStringO 和 clearBufferO 方法外， 就再没有其他新鲜的内容了。 其余的方法
 * 全部都来自于 System 类的 public 方法， 方法名字、 参数、 返回值都完全一样， 并且实现也
 * 是直接转调了 System 类的对应方法而已。 保留这些方法的目的， 是为了在 Sytem 被替换成
 * HackSystem 之后， 执行代码中调用的 System 的其余方法仍然可以继续使用
 * <p>
 * 为 JavaClass 劫持 java.lang.System 提供支持
 * 除了 out 和 err 外， 其余的都直接转发给 System 处理
 * </p>
 */
public class HackSystem {
    public final static InputStream in = System.in;
    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    public final static PrintStream out = new PrintStream(buffer);
    public final static PrintStream err = out;

    public static String getBufferString() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager s) {
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src, int srcPos, Object dest,
                                 int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    // 下面所有的方法都与 java.lang.System 的名称一样
    // 实现都是字节转调 System 的对应方法
    // 因版面原因， 省略了其他方法
}
