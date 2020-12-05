package io.github.darryring.learn.effective.constant;

import com.google.common.collect.Maps;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Enumx {

    private static void t1() {
        System.out.println(Week.abc.getClass());
        System.out.println(Week.abc.toString().getClass());
        for (Week e : Week.values()) {
            System.out.println(e.toString());
        }
        System.out.println(Student.HARRY);
    }

    private static void t2() {    // 创建EnumMap对象，该EnumMap的所有key都是Season枚举类的枚举值
        EnumMap enumMap = new EnumMap(Season.class);
        enumMap.put(Season.SUMMER, "小荷才露尖尖角");
        enumMap.put(Season.SPRING, "满园春色关不住");
        System.out.println(enumMap);
        System.out.println(enumMap.get(Season.SPRING));
        System.out.println(enumMap.get(Season.SPRING).getClass());
    }

    private static void t3() {
        System.out.println(Week.abc);
        System.out.println(Week.abc);
    }

    public static void t4() {
//        System.out.println(DbType.mysql);
//        System.out.println(DbType.mysql.getClass());
        System.out.println(DbType.mysql.str);
//        System.out.println(DbType.mysql.str.getClass());
        System.out.println(Season.SPRING.str);

        System.out.println(Week.abc);
    }

    public static void t5() {
        System.out.println(Color.BLUE.v);
    }

    public static void t6() {
        System.out.println("EnumSet.noneOf");
        EnumSet<Student> set = EnumSet.noneOf(Student.class);
        set.add(Student.HARRY);
        set.add(Student.ROBBIE);
        set.add(Student.ROBIN);
        for (Student p : set)
            System.out.println(p);
        set.clear();
        System.out.println("EnumSet.allOf");
        set = EnumSet.allOf(Student.class);
        for (Student p : set)
            System.out.println(p);
        set.clear();
        System.out.println("EnumSet.Of one");
        set = EnumSet.of(Student.ROBIN);
        for (Student p : set)
            System.out.println(p);
        System.out.println("EnumSet.Of two");
        set = EnumSet.of(Student.ROBIN, Student.HARRY);
        for (Student p : set)
            System.out.println(p);
    }

    public static void t7() {
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = Maps.newHashMap();
    }


    public static void main(String[] args) {
//        t1();
//        t2();
//        t3();
//        t4();
        t5();
    }

    enum Student {
        ROBIN("robin"),
        HARRY("harry", 40),
        ROBBIE("robbie");
        String name;
        int age;

        private Student(String name) {
            this(name, 0);
        }

        private Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name;
        }
    }

    enum DbType {
        mysql, oracle("oracle");
        String str;

        private DbType() {
            str = this.toString();
        }

        private DbType(String str) {
            this.str = str;
        }
    }

    enum Season {
        SPRING, SUMMER, FALL, WINTER;
        String str;

        private Season() {
            str = this.toString().toLowerCase();
        }
    }

    enum Color {
        RED(3), BLUE(5), BLACK(8), YELLOW(13), GREEN(28);
        private int v;

        private Color(int v) {
            this.v = v;
        }
    }

    enum Week {
        MON, TUE, WED, THU, FRI, SAT, SUN, abc;
    }

    interface IWeek {
        String MON = "Mon"; // 传统的常量定义 @tradition
        String TUE = "Tue";
        String WED = "Wed";
        String THU = "Thu";
        String FRI = "Fri";
        String SAT = "Sat";
        String SUN = "Sun";
    }
}