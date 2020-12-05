package io.github.darryring.learn.proxy;

import org.junit.jupiter.api.Test;

/**
 * Created by darryring on 2019-11-09 12:03
 *
 * @explain 代理模式
 */
class ProxyDesignPattern1Test {

    // DO 面向对象传参
    class Wizard {
        private final String name;

        Wizard(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // 面向接口编程
    interface WizardTower {
        void enter(Wizard wizard);
    }

    // impl 实现类
    class IvoryTower implements WizardTower {
        public void enter(Wizard wizard) {
            System.out.print(String.format("%s enters the tower.", wizard));
        }
    }

    // proxy design pattern
    class WizardTowerProxy implements WizardTower {
        private final WizardTower tower;

        WizardTowerProxy(WizardTower tower) {
            this.tower = tower;
        }

        @Override
        public void enter(Wizard wizard) {
            System.out.print(">>>>>>>>>>>> ");
            tower.enter(wizard);
            System.out.println(" <<<<<<<<<<<");
        }
    }

    void x1(WizardTower proxy) {
        proxy.enter(new Wizard("Red wizard"));
        proxy.enter(new Wizard("White wizard"));
        proxy.enter(new Wizard("Black wizard"));
        proxy.enter(new Wizard("Green wizard"));
        proxy.enter(new Wizard("Brown wizard"));
    }

    @Test
    void t1() {
        WizardTowerProxy proxy = new WizardTowerProxy(new IvoryTower());
        x1(proxy);
    }
}
