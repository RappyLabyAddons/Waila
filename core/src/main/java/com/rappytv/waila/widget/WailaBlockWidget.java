package com.rappytv.waila.widget;

import com.rappytv.waila.WailaAddon;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.util.I18n;

public class WailaBlockWidget extends TextHudWidget<TextHudWidgetConfig> {

    private final WailaAddon addon;
    private final boolean fluid;
    private TextLine line;
    private String lookingAt;

    public WailaBlockWidget(WailaAddon addon, boolean fluid) {
        super("waila_" + (fluid ? "fluid" : "block"));

        this.addon = addon;
        this.fluid = fluid;
    }

    @Override
    public void load(TextHudWidgetConfig config) {
        super.load(config);

        String translation = "waila.widget." + (fluid ? "fluid" : "block");
        line = super.createLine(
            I18n.translate(translation + ".name"),
            I18n.translate(translation + ".placeholder")
        );
    }

    @Override
    public void onTick(boolean isEditorContext) {
        if(isEditorContext)
            lookingAt = "";
        else
            lookingAt = addon.api().getLookingAt(fluid, addon.configuration().range().get());

        line.updateAndFlush(lookingAt);
    }

    @Override
    public boolean isVisibleInGame() {
        return
            lookingAt != null &&
            !lookingAt.equals("Air");
    }
}
