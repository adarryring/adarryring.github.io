package io.github.darryring.learn.hack.bytes;


import io.github.darryring.util.bytecode.ClassModifierUtil;

import java.lang.reflect.Method;

/**
 * Created by darryring on 2019-09-25 20:41
 *
 * @explain
 */
public class JavaClassExecuter {

    /**
     * 执行外部传过来的代表一个 Java 类的 byte 数组 <br>
     * 将输入类的 byte 数组中代表 java•lang.System 的 CONSTANT_Utf8_info 常量修改为劫持后的
     * HackSystem 类
     * static main(String[] args} 方法， 输出结果为该类向 System.out/err
     * 输出的信息
     *
     * @param classByte 代表一个 Java 类的 byte 数组
     * @return 执行结果
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifierUtil cm = new ClassModifierUtil(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", HackSystem.class.getName().replace(".", "/"));
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
