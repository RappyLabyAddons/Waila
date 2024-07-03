package com.rappytv.waila.widget;

import net.labymod.api.client.gui.hud.hudwidget.HudWidget.Updatable;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;

public class WailaWidget extends HorizontalListWidget implements Updatable {

    private final WailaHudWidget hudWidget;

    public WailaWidget(WailaHudWidget hudWidget) {
        this.hudWidget = hudWidget;
    }

    @Override
    public void initialize(Parent parent) {
        super.initialize(parent);

        if(hudWidget.result.icon() != null)
            addEntry(new IconWidget(hudWidget.result.icon()));
        else addId("no-icon");

        ComponentWidget widget = ComponentWidget.text(hudWidget.result.name());
        addEntry(widget).addId("waila-label");
    }

    @Override
    public void update(String reason) {
        if(reason.equals(WailaHudWidget.BLOCK_CHANGE_REASON))
            removeId("no-icon");
        reInitialize();
    }
}
