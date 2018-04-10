package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public class VerticalShakeAnimation extends ShakeAnimation {

    private int initialY = 0;

    public VerticalShakeAnimation(Layer target) {
        super(target);
        initialY = target.getY();
    }

    public VerticalShakeAnimation(Layer target, long time) {
        super(target, time);
        initialY = target.getY();
    }

    @Override
    public void calculate(float factor) {
        float value = calculateValue(factor);
        target.setY(initialY + (int) value);
    }

    public VerticalShakeAnimation strength(int strength) {
        this.strength = strength;
        return this;
    }

    @Override
    public void onAnimationStart(long now) {
        super.onAnimationStart(now);
        if (currentLoop == 0) {
            initialY = target.getY();
        }
    }

    @Override
    public void onAnimationComplete(long now) {
        super.onAnimationComplete(now);
        target.setY(initialY);
    }

}
