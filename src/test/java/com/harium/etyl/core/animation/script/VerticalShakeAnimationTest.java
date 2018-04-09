package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import org.junit.Assert;
import org.junit.Test;

public class VerticalShakeAnimationTest {

    @Test
    public void testConstructor() {
        VerticalShakeAnimation animation = Animation.animate(null).shakeVertical();
        Assert.assertNotNull(animation);
    }

    @Test
    public void testConstructorWithDuration() {
        int duration = 600;
        VerticalShakeAnimation animation = Animation.animate(null).shakeVertical(duration);
        Assert.assertEquals(duration, animation.getDuration());
    }

    @Test
    public void testCalculate() {
        int strength = 10;

        Layer layer = new Layer(0, 0, 0, 0);

        VerticalShakeAnimation animation = new VerticalShakeAnimation(layer);
        animation.setStrength(strength);

        // Expected
        animation.calculate(0);
        Assert.assertEquals(0, layer.getY());

        animation.calculate(0.10);
        Assert.assertTrue(layer.getY() > -strength);

        animation.calculate(0.25);
        Assert.assertEquals(-strength, layer.getY());

        animation.calculate(0.30);
        Assert.assertTrue(layer.getY() > -strength);

        animation.calculate(0.5);
        Assert.assertEquals(0, layer.getY());

        animation.calculate(0.60);
        Assert.assertTrue(layer.getY() < strength);

        animation.calculate(0.75);
        Assert.assertEquals(strength, layer.getY());

        animation.calculate(0.80);
        Assert.assertTrue(layer.getY() < strength);

        animation.calculate(1);
        Assert.assertEquals(0, layer.getY());
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
        Assert.assertEquals(-strength, layer.getY());
        Animation.getInstance().update(75);
        Assert.assertEquals(strength, layer.getY());
        Animation.getInstance().update(100);
        Assert.assertEquals(0, layer.getY());
        Animation.getInstance().update(101);
        Assert.assertTrue(animation.isStopped());
    }

}
