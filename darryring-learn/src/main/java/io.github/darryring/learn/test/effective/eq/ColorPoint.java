package io.github.darryring.learn.test.effective.eq;

import java.awt.Point;
import java.awt.*;

public class ColorPoint extends Point {
    private Color color;

    public ColorPoint(int x, Color color) {
        super(x, 0);
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColorPoint)) {
            return false; // 这里就会出现问题
        }
        return super.equals(obj) && ((ColorPoint) obj).color == this.color;
    }
}
