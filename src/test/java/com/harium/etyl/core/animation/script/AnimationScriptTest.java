package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnAnimationFinishListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimationScriptTest {

    private AnimationScript script;

    @Before
    public void setUp() {
        script = new AnimationScript() {
            @Override
            public void calculate(double factor) {}
        };
    }

    @Test
    public void testFactor() {
        script.setDuration(1000);
        float factor = script.factor(1100);
        Assert.assertEquals(1.1f, factor, 0.0001);
    }

    @Test
    public void testFactorWithStart() {
        script.start(200);
        script.setDuration(1000);
        float factor = script.factor(1100);
        Assert.assertEquals(0.9f, factor, 0.0001);
    }

    @Test
    public void testOnFinish() {
        OnAnimationFinishListener listener = new OnAnimationFinishListener() {
            @Override
            public void onAnimationFinish(long now) {}
        };

        Layer layer = new Layer();
        AnimationScript script = Animation.animate(layer).onFinish(listener);
        Assert.assertEquals(listener, script.getListener());
    }

}
