package com.harium.etyl.core.animation;

import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.commons.module.Module;
import com.harium.etyl.core.animation.script.AnimationScript;
import com.harium.etyl.core.animation.script.LayerAnimation;
import com.harium.etyl.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Animation implements Module {

    public static final int REPEAT_FOREVER = -1;

    private static Animation instance;

    /* package */ List<AnimationExecution> scripts = new ArrayList<AnimationExecution>();

    private Animation() {
        super();
    }

    public static Animation getInstance() {
        if (instance == null) {
            instance = new Animation();
        }

        return instance;
    }

    public static LayerAnimation animate(Layer layer) {
        LayerAnimation script = new LayerAnimation(layer);
        return script;
    }

    public void update(long now) {
        Iterator<AnimationExecution> iterator = scripts.listIterator();
        while (iterator.hasNext()) {
            AnimationExecution execution = iterator.next();

            if (!execution.execute(now)) {
                if (repeatLogic(execution, now)) {
                    iterator.remove();
                }
            }
        }
    }

    private boolean repeatLogic(AnimationExecution execution, long now) {
        AnimationScript script = execution.getScript();

        if (script.getRepeat() == REPEAT_FOREVER) {
            execution.restart(now);
            // Keep the object in the list
            return false;
        } else if (execution.getRepeated() < script.getRepeat() - 1) {
            execution.repeat(now);
            script.tick(now);
            // Keep the object in the list
            return false;
        } else {
            // Animation is over
            // Notify onAnimationFinish
            script.stop(now);
            appendChildren(script);
        }

        return true;
    }

    private void appendChildren(AnimationScript script) {
        if (script.getNext() == null) {
            return;
        }
        
        for (AnimationScript s : script.getNext()) {
            scripts.add(new AnimationExecution(s));
            s.restart();
        }
    }

    public void add(AnimationScript script) {
        scripts.add(new AnimationExecution(script));
    }

    public void remove(AnimationScript script) {
        for (AnimationExecution execution : scripts) {
            if (execution.getScript() == script) {
                scripts.remove(script);
                break;
            }
        }
    }

    public int size() {
        return scripts.size();
    }

    public void clear() {
        scripts.clear();
    }

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public void updateMouse(PointerEvent event) {
    }

    @Override
    public void updateKeyboard(KeyEvent event) {
    }

    @Override
    public void updateGuiEvent(GUIEvent event) {
    }

    @Override
    public void init(Context context) {
    }

    @Override
    public void dispose(Context context) {

    }
}
