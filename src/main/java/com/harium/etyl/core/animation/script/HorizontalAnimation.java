package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class HorizontalAnimation extends SingleIntervalAnimation {

    {
        startValue = UNDEFINED;
    }

    public HorizontalAnimation(Layer target) {
        super(target);
    }

    public HorizontalAnimation(long delay, long time) {
        super(delay, time);
    }

    public HorizontalAnimation(Layer target, long time) {
        super(target, time);
    }

    @Override
    protected void update(float value) {
        target.setX((int) value);
    }

    @Override
    public void onAnimationStart(long now) {
        super.onAnimationStart(now);
        if (startValue == UNDEFINED) {
            startValue = target.getX();
        }
    }

}
