package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HorizontalShakeAnimationTest {

    private Layer layer;

    @Before
    public void setUp() {
        layer = new Layer();
    }

    @Test
    public void testConstructor() {
        HorizontalShakeAnimation animation = Animation.animate(layer).shakeHorizontal();
        Assert.assertNotNull(animation);
    }

    @Test
    public void testConstructorWithDuration() {
        int duration = 600;
        HorizontalShakeAnimation animation = Animation.animate(layer).shakeHorizontal(duration);
        Assert.assertEquals(duration, animation.getDuration());
    }

    @Test
    public void testCalculate() {
        int x = 99;
        int strength = 10;

        Layer layer = new Layer(x, 0, 0, 0);

        HorizontalShakeAnimation animation = new HorizontalShakeAnimation(layer);
        animation.setStrength(strength);

        animation.calculate(0);
        Assert.assertEquals(x, layer.getX());

        animation.calculate(0.10f);
        Assert.assertTrue(layer.getX() > x);
        Assert.assertTrue(layer.getX() < x + strength);

        animation.calculate(0.25f + 0.25f / 2);
        Assert.assertEquals(x + strength / 2, layer.getX());

        animation.calculate(0.5f);
        Assert.assertEquals(x, layer.getX());

        animation.calculate(0.60f);
        Assert.assertTrue(layer.getX() < x);
        Assert.assertTrue(layer.getX() > x - strength);

        animation.calculate(0.50f + 0.25f / 2);
        Assert.assertEquals(x - strength / 2, layer.getX());

        animation.calculate(0.75f);
        Assert.assertEquals(x - strength, layer.getX());

        animation.calculate(0.80f);
        Assert.assertTrue(layer.getX() < x);
        Assert.assertTrue(layer.getX() > x - strength);

        animation.calculate(1);
        Assert.assertEquals(x, layer.getX());
    }

    @Test
    public void testInterval() {
        int duration = 100;
        int strength = 20;
        Layer layer = new Layer(0, 0, 10, 10);

        HorizontalShakeAnimation animation = Animation.animate(layer).shakeHorizontal(duration);
        animation.loop(1).strength(strength).start();

        Animation.getInstance().update(0);
        Assert.assertEquals(0, layer.getX());
        Animation.getInstance().update(25);
        Assert.assertEquals(strength, layer.getX());
        Animation.getInstance().update(50);
        Animation.getInstance().update(75);
        Assert.assertEquals(-strength, layer.getX());
        Animation.getInstance().update(100);
        Assert.assertEquals(0, layer.getX());
        Animation.getInstance().update(101);
        Assert.assertTrue(animation.isStopped());
    }

}
