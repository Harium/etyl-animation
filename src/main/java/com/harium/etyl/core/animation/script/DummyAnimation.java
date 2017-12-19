package com.harium.etyl.core.animation.script;


import com.harium.etyl.commons.layer.Layer;

public class DummyAnimation extends LayerAnimation {

    public DummyAnimation(Layer target) {
        super(target);
    }

    public boolean animate(long now) {
        return true;
    }

    @Override
    public void calculate(double factor) {

    }

    @Override
    public void onAnimationStart(long now) {
        stop(now);
    }

    protected void startChildren() {
        root.start();
    }
}
