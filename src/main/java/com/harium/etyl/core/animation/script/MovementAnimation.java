package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public class MovementAnimation extends LayerAnimation {

    protected float startX = UNDEFINED;
    protected float endX = 0;

    protected float startY = UNDEFINED;
    protected float endY = 0;

    public MovementAnimation(Layer target) {
        super(target);
    }

    public MovementAnimation(Layer target, long time) {
        this(target);
        this.duration = time;
    }

    @Override
    public void onAnimationStart(long now) {
        super.onAnimationStart(now);
        if (startX == UNDEFINED) {
            startX = target.getX();
        }
        if (startY == UNDEFINED) {
            startY = target.getY();
        }
    }

    public void calculate(float factor) {
        double valueX = interpolator.interpolate(startX, endX, factor);
        double valueY = interpolator.interpolate(startY, endY, factor);

        target.setLocation((int) valueX, (int) valueY);
    }

    public MovementAnimation from(int x, int y) {
        this.startX = x;
        this.startY = y;

        return this;
    }

    public MovementAnimation to(int x, int y) {
        this.endX = x;
        this.endY = y;

        return this;
    }
}