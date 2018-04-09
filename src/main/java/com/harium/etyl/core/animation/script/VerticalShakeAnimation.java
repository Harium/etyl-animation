package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public class VerticalShakeAnimation extends ShakeAnimation {

    private int initialY = 0;

    public VerticalShakeAnimation(Layer target) {
        super(target);
    }

    public VerticalShakeAnimation(Layer target, long time) {
        super(target, time);
    }

    @Override
    public void calculate(double factor) {
        double value = calculateValue(factor, initialY);
        target.setY((int) value);
    }

    public VerticalShakeAnimation strength(int strength) {
        this.strength = strength;
        return this;
    }

}
