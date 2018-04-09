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
    public void testRemoveIfFinished() {
        int duration = 1;

        LayerAnimation a1 = new LayerAnimation(duration);

        animation.add(a1);
        animation.update(0);
        Assert.assertEquals(1, animation.scripts.size());
        animation.update(duration + 1);
        Assert.assertEquals(0, animation.scripts.size());
    }

    @Test
    public void testAddChildren() {
        int duration = 1;

        LayerAnimation a1 = new LayerAnimation(duration);

        LayerAnimation a11 = new LayerAnimation(duration);
        a1.addNext(a11);

        animation.add(a1);
        animation.update(0);
        Assert.assertEquals(1, animation.scripts.size());
        animation.update(duration + 1);

        // a11 was added
        Assert.assertEquals(1, animation.scripts.size());
        Assert.assertEquals(a11, animation.scripts.get(0).getScript());
    }

}
