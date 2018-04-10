package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class ShakeAnimation extends StrengthAnimation {

    private static final float INTERVAL = 0.25f;

    public ShakeAnimation(Layer target) {
        super(target);
    }

    public ShakeAnimation(Layer target, long time) {
        super(target, time);
    }

    protected float calculateValue(float factor) {
        float rFactor;
        float start, end;
        float value;

        if (factor <= INTERVAL) {
            rFactor = factor / INTERVAL;
            start = 0;
            end = strength;
        } else if (factor <= 0.75f) {
            rFactor = (factor - INTERVAL) / 0.5f;
            start = strength;
            end = -strength;
        } else {
            rFactor = (factor - 0.75f) / INTERVAL;
            start = -strength;
            end = 0;
        }

        value = start + (end - start) * rFactor;
        return value;
    }
}
