package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class ShakeAnimation extends StrengthAnimation {

    public ShakeAnimation(Layer target) {
        super(target);
    }

    public ShakeAnimation(Layer target, long time) {
        super(target, time);
    }

    protected double calculateValue(double factor, double initial) {
        double value;

        if (factor <= 0.25) {
            double sub = factor / 0.25;
            value = initial - strength * sub;
        } else if (factor >= 0.75) {
            double sub = (1 - factor) * 4;
            value = initial + strength * sub;
        } else {
            double startValue = initial - strength;
            double endValue = initial + strength;
            value = startValue + (endValue - startValue) * factor;
        }

        return value;
    }
}
