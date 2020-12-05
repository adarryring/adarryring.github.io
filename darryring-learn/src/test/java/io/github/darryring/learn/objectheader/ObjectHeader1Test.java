package io.github.darryring.learn.objectheader;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * Created by darryring on 2019-11-07 21:04
 */
class ObjectHeader1Test {

    private void printLine() {
        System.out.println("***************************************************");
    }

    private void printObjectHeader(Object o) {
        printLine();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        printLine();
    }

    // 参考 jvm 源码：`src/share/vm/oops/markOop.hpp`
    // ==================================================================================
    // The markOop describes the header of an object.
    //
    // Note that the mark is not a real oop but just a word.
    // It is placed in the oop hierarchy for historical reasons.
    //
    // Bit-format of an object header (most significant first, big endian layout below):
    //
    //  32 bits:
    //  --------
    //             hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
    //             JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
    //             size:32 ------------------------------------------>| (CMS free block)
    //             PromotedObject*:29 ---------->| promo_bits:3 ----->| (CMS promoted object)
    //
    //  64 bits:
    //  --------
    //  unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
    //  JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
    //  PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
    //  size:64 ----------------------------------------------------->| (CMS free block)
    //
    //  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
    //  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
    //  narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
    //  unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)
    // ==================================================================================

    /**
     * http://openjdk.java.net/groups/hotspot/docs/HotSpotGlossary.html Hotspot 词表说明
     * 启用指针压缩（默认）: -XX:+UseCompressedOops，禁止指针压缩: -XX:-UseCompressedOops
     * 大端存储
     */
    @Test
    void t1() {
        // class
        System.out.println(ClassLayout.parseClass(Student.class).toPrintable());

        // set value
        Student student = new Student();
        student.setId(1);
        student.setName("darryring");
        printObjectHeader(student);

        // hash code
        int hashCode = student.hashCode();
        System.out.println(String.format("hash code is : %s. It's hex number is : %s", hashCode, Integer.toHexString(hashCode)));
        printObjectHeader(student);
    }

    /**
     * 类加载的过程：加载、验证、准备、解析、初始化
     */
    class Student {
        private int id;
        private String name;
        private boolean b;

        public boolean isB() {
            return b;
        }

        public void setB(boolean b) {
            this.b = b;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    void t2() {
        Student student = new Student();
        synchronized (student) {
            printObjectHeader(student);
        }
    }
}
