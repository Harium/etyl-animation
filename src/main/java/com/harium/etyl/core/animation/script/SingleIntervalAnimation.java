package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class SingleIntervalAnimation extends LayerAnimation {

    protected float startValue = 0;
    protected float endValue = 0;

    public SingleIntervalAnimation(Layer target) {
        super(target);
    }

    public SingleIntervalAnimation(long time) {
        super(time);
    }

    public SingleIntervalAnimation(long delay, long time) {
        super(delay, time);
    }

    public SingleIntervalAnimation(Layer target, long time) {
        super(target, time);
    }

    public SingleIntervalAnimation(Layer target, long delay, long time) {
        super(delay, time);
        setTarget(target);
    }

    @Override
    public void calculate(float factor) {
        float value = (float) interpolator.interpolate(startValue, endValue, factor);
        update(value);
    }

    /**
     * This method override is needed
     */
    @Override
    public SingleIntervalAnimation during(long duration) {
        super.during(duration);
        return this;
    }

    public SingleIntervalAnimation from(float value) {
        setStartValue(value);
        return this;
    }

    public SingleIntervalAnimation to(float value) {
        setEndValue(value);
        return this;
    }

    protected abstract void update(float value);

    public void setInterval(float startValue, float endValue) {
        this.startValue = startValue;
        this.endValue = endValue;

        //Update for the first value
        if (target != null) {
            calculate(0);
        }
    }

    public float getStartValue() {
        return startValue;
    }

    public void setStartValue(float startValue) {
        this.startValue = startValue;
    }

    public float getEndValue() {
        return endValue;
    }

    public void setEndValue(float endValue) {
        this.endValue = endValue;
    }
}
