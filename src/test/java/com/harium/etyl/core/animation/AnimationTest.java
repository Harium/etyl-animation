package com.harium.etyl.core.animation;

import com.harium.etyl.core.animation.script.LayerAnimation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimationTest {

    private Animation animation;

    @Before
    public void setUp() {
        animation = Animation.getInstance();
        animation.clear();
    }

    @Test
    public void testInit() {
        Assert.assertNotNull(animation.scripts);
    }

    @Test
    public void testUpdate() {
        int duration = 1;

        LayerAnimation a1 = new LayerAnimation(duration);

        animation.add(a1);
        animation.update(0);
        Assert.assertEquals(1, animation.scripts.size());
        animation.update(duration + 1);
        Assert.assertEquals(0, animation.scripts.size());
    }

}
