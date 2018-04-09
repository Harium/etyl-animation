package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import org.junit.Assert;
import org.junit.Test;

public class HorizontalShakeAnimationTest {

    @Test
    public void testConstructor() {
        HorizontalShakeAnimation animation = Animation.animate(null).shakeHorizontal();
        Assert.assertNotNull(animation);
    }

    @Test
    public void testConstructorWithDuration() {
        int duration = 600;
        HorizontalShakeAnimation animation = Animation.animate(null).shakeHorizontal(duration);
        Assert.assertEquals(duration, animation.getDuration());
    }

    @Test
    public void testCalculate() {
        int strength = 10;

        Layer layer = new Layer(0, 0, 0, 0);

        HorizontalShakeAnimation animation = new HorizontalShakeAnimation(layer);
        animation.setStrength(strength);

        // Expected
        animation.calculate(0);
        Assert.assertEquals(0, layer.getX());

        animation.calculate(0.10);
        Assert.assertTrue(layer.getX() > -strength);

        animation.calculate(0.25);
        Assert.assertEquals(-strength, layer.getX());

        animation.calculate(0.25 + 0.25 / 2);
        Assert.assertEquals(-strength / 2, layer.getX());

        animation.calculate(0.5);
        Assert.assertEquals(0, layer.getX());

        animation.calculate(0.60);
        Assert.assertTrue(layer.getX() < strength);

        animation.calculate(0.50 + 0.25 / 2);
        Assert.assertEquals(strength / 2, layer.getX());

        animation.calculate(0.75);
        Assert.assertEquals(strength, layer.getX());

        animation.calculate(0.80);
        Assert.assertTrue(layer.getX() < strength);

        animation.calculate(1);
        Assert.assertEquals(0, layer.getX());
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
        Assert.assertEquals(-strength, layer.getX());
        Animation.getInstance().update(50);
        Animation.getInstance().update(75);
        Assert.assertEquals(strength, layer.getX());
        Animation.getInstance().update(100);
        Assert.assertEquals(0, layer.getX());
        Animation.getInstance().update(101);
        Assert.assertTrue(animation.isStopped());
    }

}
