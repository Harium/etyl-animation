package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class FadeInAnimation extends OpacityAnimation {

    public FadeInAnimation(Layer target) {
        super(target);
    }

    public FadeInAnimation(long time) {
        super(time);
    }

    public FadeInAnimation(long delay, long time) {
        super(delay, time);
    }

    public FadeInAnimation(Layer target, long time) {
        super(target, time);
    }

    public FadeInAnimation(Layer target, long delay, long time) {
        super(target, delay, time);
    }

    @Override
    public void onAnimationStart(long now) {
        super.onAnimationStart(now);
        startValue = 0;
        endValue = MAX_OPACITY;
    }

    @Override
    public void onAnimationFinish(long now) {
        super.onAnimationFinish(now);
        this.update(endValue);
    }
}
