package com.harium.etyl.core.animation;

import com.harium.etyl.core.animation.script.AnimationScript;

public class AnimationExecution {

    private long correction = 0;
    private boolean started = false;

    private AnimationScript script;

    public AnimationExecution(AnimationScript script) {
        super();
        this.script = script;
    }

    public AnimationExecution(AnimationScript script, long correction) {
        super();
        this.script = script;
        this.correction = correction;
    }

    public boolean execute(long now) {
        if (!started) {
            start(now);
            // script.onAnimationStart(now);
            // Is called in script.start(now) method
        }

        if (!script.isStopped()) {
            script.tick(now);
            return script.isStopped();
        }

        return true;
    }

    public void repeat(long now) {
        calculateCorrection(now);

        script.setCurrentLoop(script.getCurrentLoop() + 1);
        script.start(now + correction);
    }

    public long getCorrection() {
        return correction;
    }

    public void setCorrection(long correction) {
        this.correction = correction;
    }

    public AnimationScript getScript() {
        return script;
    }

    public void setScript(AnimationScript script) {
        this.script = script;
    }

    public boolean isStarted() {
        return started;
    }

    public void start(long now) {
        script.start(now + correction);
        correction = 0;
        started = true;
    }

    public void restart(long now) {
        calculateCorrection(now);
        script.restart();
        started = false;
    }

    private void calculateCorrection(long now) {
        float lastFactor = script.factor(now);
        lastFactor -= Math.floor(lastFactor);
        correction = (long) -(script.getDuration() * lastFactor);
        script.calculate(lastFactor);
    }
}
