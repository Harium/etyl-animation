package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class OpacityAnimation extends SingleIntervalAnimation {

    protected static final int MAX_OPACITY = 0xff;

    public OpacityAnimation(Layer target) {
        super(target);
    }

    public OpacityAnimation(long time) {
        super(time);
        setInterval(0, MAX_OPACITY);
    }

    public OpacityAnimation(long delay, long time) {
        super(delay, time);
        setInterval(0, MAX_OPACITY);
    }

    public OpacityAnimation(Layer target, long time) {
        super(target, time);
        setInterval(0, MAX_OPACITY);
    }

    public OpacityAnimation(Layer target, long delay, long time) {
        super(target, delay, time);
        setInterval(0, MAX_OPACITY);
    }

    @Override
    protected void update(double value) {
        target.setOpacity((int) value);
    }

}
