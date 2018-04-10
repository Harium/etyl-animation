package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public class HorizontalShakeAnimation extends ShakeAnimation {

    private int initialX = 0;

    public HorizontalShakeAnimation(Layer target) {
        super(target);
        initialX = target.getX();
    }

    public HorizontalShakeAnimation(Layer target, long time) {
        super(target, time);
        initialX = target.getX();
    }

    @Override
    public void calculate(float factor) {
        float value = calculateValue(factor);
        target.setX(initialX + (int) value);
    }

    public HorizontalShakeAnimation strength(int strength) {
        this.strength = strength;
        return this;
    }

    @Override
    public void onAnimationStart(long now) {
        super.onAnimationStart(now);
        if (currentLoop == 0) {
            initialX = target.getX();
        }
    }

    @Override
    public void onAnimationComplete(long now) {
        super.onAnimationComplete(now);
        target.setX(initialX);
    }

}
