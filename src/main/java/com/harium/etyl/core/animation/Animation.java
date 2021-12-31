package com.harium.etyl.core.animation;

import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.graphics.Graphics;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.commons.module.Module;
import com.harium.etyl.core.animation.script.AnimationScript;
import com.harium.etyl.core.animation.script.LayerAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
        ListIterator<AnimationExecution> iterator = scripts.listIterator();
        while (iterator.hasNext()) {
            AnimationExecution execution = iterator.next();

            // execution.execute returns if script is stopped
            if (execution.execute(now)) {
                if (repeatLogic(execution, now)) {
                    // Stop script
                    execution.getScript().stop(now);
                    // Remove script
                    iterator.remove();
                    // Append children if any
                    appendChildren(execution.getScript(), iterator);
                }
            }
        }
    }

    private boolean repeatLogic(AnimationExecution execution, long now) {
        AnimationScript script = execution.getScript();

        if (script.getLoop() == REPEAT_FOREVER) {
            script.onAnimationComplete(now);
            execution.restart(now);
            // Keep execution in the list
            return false;
        } else if (script.getCurrentLoop() < script.getLoop() - 1) {
            script.onAnimationComplete(now);
            execution.repeat(now);
            script.tick(now);
            // Keep execution in the list
            return false;
        } else {
            // Force animation to 1
            //script.calculate(1);

            // Remove execution from the list
            return true;
        }
    }

    private void appendChildren(AnimationScript script, ListIterator<AnimationExecution> iterator) {
        if (script.getNext() == null) {
            return;
        }
        
        for (AnimationScript s : script.getNext()) {
            iterator.add(new AnimationExecution(s));
            s.restart();
        }
    }

    public void add(AnimationScript script) {
        scripts.add(new AnimationExecution(script));
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
