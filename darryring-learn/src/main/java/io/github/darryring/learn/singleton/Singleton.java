package io.github.darryring.learn.singleton;

/**
 * @author darryring
 **/
public class Singleton {
    private volatile static Singleton instance;

    private static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                    return instance;
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.toString());
    }
}