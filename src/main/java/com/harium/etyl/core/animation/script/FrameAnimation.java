package com.harium.etyl.core.animation.script;

import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnFrameChangeListener;
import com.harium.etyl.layer.AnimatedLayer;

public class FrameAnimation extends SingleIntervalAnimation {

    private int frame = 0;
    protected AnimatedLayer target;
    private OnFrameChangeListener frameListener = new OnFrameChangeListener() {
        @Override
        public void onFrameChange(int currentFrame) {

        }
    };

    public FrameAnimation(long time) {
        super(time);
    }

    public FrameAnimation(AnimatedLayer target) {
        super(target.getSpeed() * target.getFrames());

        setTarget(target);
        this.loop = Animation.REPEAT_FOREVER;
    }

    public void setTarget(AnimatedLayer target) {
        this.target = target;

        this.startValue = 0;
        this.endValue = target.getFrames();

        this.restart();
    }

    @Override
    protected void update(float value) {
        int currentFrame = (int) (value % target.getFrames());
        if (frame!=currentFrame) {
            frame = currentFrame;
            frameListener.onFrameChange(currentFrame);
        }
        target.animateWithFrame(frame);
    }

    public FrameAnimation onFrameChange(OnFrameChangeListener frameListener) {
        this.frameListener = frameListener;
        return this;
    }

    public OnFrameChangeListener getFrameListener() {
        return frameListener;
    }

    public void setFrameListener(OnFrameChangeListener frameListener) {
        this.frameListener = frameListener;
    }

}
