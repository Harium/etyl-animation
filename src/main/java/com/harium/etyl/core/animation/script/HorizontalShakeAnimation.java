package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public class HorizontalShakeAnimation extends ShakeAnimation {

    private int initialX = 0;

    public HorizontalShakeAnimation(Layer target) {
        super(target);
    }

    public HorizontalShakeAnimation(Layer target, long time) {
        super(target, time);
    }

    @Override
    public void calculate(double factor) {
        double value = calculateValue(factor, initialX);
        target.setX((int) value);
    }

    public HorizontalShakeAnimation strength(int strength) {
        this.strength = strength;
        return this;
    }

    /*@Override
    public void onAnimationStart(long now) {
        super.onAnimationStart(now);
        initialX = target.getX();
    }

    @Override
    public void onAnimationFinish(long now) {
        super.onAnimationFinish(now);
        target.setX(initialX);
    }*/

}
