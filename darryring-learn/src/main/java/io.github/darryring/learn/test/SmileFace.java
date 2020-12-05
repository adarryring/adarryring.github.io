package io.github.darryring.learn.test;

import java.applet.Applet;
import java.awt.*;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-12-05 09:24
 */

//Applet的小应用程序
//Graphics允许咋应用程序上还进行制图
public class SmileFace extends Applet {
    //SmileFace指明他是applet的一个子类
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public void paint(Graphics g) {
        //重新定义Applet的一个方法，对象g可以当成制图的画板
// Color c=new Color(234,66,44);
        g.setColor(Color.yellow);//填充颜色
        g.fillOval(35, 15, 50, 50);//在坐标（35，15）处画一个50*50的圆，
        g.setColor(Color.black);
        g.fillOval(50, 30, 5, 5);
        g.fillOval(65, 30, 5, 5);//画眼睛
        g.drawArc(50, 40, 20, 10, 190, 160);//画弧形（微笑）
    }
}