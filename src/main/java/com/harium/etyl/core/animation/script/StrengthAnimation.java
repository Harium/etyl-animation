package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class StrengthAnimation extends LayerAnimation {

    protected int strength = 0;

    public StrengthAnimation(Layer target) {
        super(target);
    }

    public StrengthAnimation(Layer target, long time) {
        super(target, time);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public StrengthAnimation strength(int strength) {
        this.strength = strength;
        return this;
    }

    @Override
    public StrengthAnimation loop(int loop) {
        super.loop(loop);
        return this;
    }
}
