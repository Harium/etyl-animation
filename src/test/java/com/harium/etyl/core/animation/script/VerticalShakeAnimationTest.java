package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VerticalShakeAnimationTest {

    private Layer layer;

    @Before
    public void setUp() {
        layer = new Layer();
    }

    @Test
    public void testConstructor() {
        VerticalShakeAnimation animation = Animation.animate(layer).shakeVertical();
        Assert.assertNotNull(animation);
    }

    @Test
    public void testConstructorWithDuration() {
        int duration = 600;
        VerticalShakeAnimation animation = Animation.animate(layer).shakeVertical(duration);
        Assert.assertEquals(duration, animation.getDuration());
    }

    @Test
    public void testCalculate() {
        int y = 99;
        int strength = 10;

        Layer layer = new Layer(0, y, 0, 0);

        VerticalShakeAnimation animation = new VerticalShakeAnimation(layer);
        animation.setStrength(strength);

        animation.calculate(0);
        Assert.assertEquals(y, layer.getY());

        animation.calculate(0.10f);
        Assert.assertTrue(layer.getY() > y);
        Assert.assertTrue(layer.getY() < y + strength);

        animation.calculate(0.25f + 0.25f / 2);
        Assert.assertEquals(y + strength / 2, layer.getY());

        animation.calculate(0.5f);
        Assert.assertEquals(y, layer.getY());

        animation.calculate(0.60f);
        Assert.assertTrue(layer.getY() < y);
        Assert.assertTrue(layer.getY() > y - strength);

        animation.calculate(0.50f + 0.25f / 2);
        Assert.assertEquals(y - strength / 2, layer.getY());

        animation.calculate(0.75f);
        Assert.assertEquals(y - strength, layer.getY());

        animation.calculate(0.80f);
        Assert.assertTrue(layer.getY() < y);
        Assert.assertTrue(layer.getY() > y - strength);

        animation.calculate(1);
        Assert.assertEquals(y, layer.getY());
    }

    @Test
    public void testInterval() {
        int duration = 100;
        int strength = 20;
        Layer layer = new Layer(0, 0, 10, 10);

        VerticalShakeAnimation animation = Animation.animate(layer).shakeVertical(duration);
        animation.loop(1).strength(strength).start();

        Animation.getInstance().update(0);
        Assert.assertEquals(0, layer.getY());
        Animation.getInstance().update(25);
        Assert.assertEquals(strength, layer.getY());
        Animation.getInstance().update(75);
        Assert.assertEquals(-strength, layer.getY());
        Animation.getInstance().update(100);
        Assert.assertEquals(0, layer.getY());
        Animation.getInstance().update(101);
        Assert.assertTrue(animation.isStopped());
    }

}
