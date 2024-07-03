package com.rappytv.waila.widget;

import com.rappytv.waila.WailaAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.util.I18n;

public class OldWidget extends TextHudWidget<TextHudWidgetConfig> {

    private final WailaAddon addon;
    private final boolean fluid;
    private TextLine line;
    private String lookingAt;

    public OldWidget(WailaAddon addon, HudWidgetCategory category, boolean fluid) {
        super("waila_" + (fluid ? "fluid" : "block"));

        this.addon = addon;
        this.fluid = fluid;
        bindCategory(category);
        setIcon(Icon.texture(ResourceLocation.create(
            "waila",
            String.format("textures/%s.png", fluid ? "fluid" : "grass")
        )));
    }

    @Override
    public void load(TextHudWidgetConfig config) {
        super.load(config);

        line = super.createLine(
            Component.translatable("waila.widget." + (fluid ? "fluid" : "block")),
            I18n.translate("waila.widget.placeholder")
        );
    }

    @Override
    public void onTick(boolean isEditorContext) {
        String name;
        int range = 5;

        if(isEditorContext)
            name = I18n.translate("waila.widget.placeholder");
        else {
            if(fluid) {
                String fluid = addon.api().getLookingAt(true, range);
                String block = addon.api().getLookingAt(false, range);

                if(fluid != null && fluid.equals(block))
                    name = null;
                else
                    name = addon.api().getLookingAt(true, range);
            } else
                name = addon.api().getLookingAt(false, range);
        }

        lookingAt = name != null ? name : I18n.translate("waila.widget.placeholder");
        line.updateAndFlush(lookingAt);
    }

    @Override
    public boolean isVisibleInGame() {
        return
            lookingAt != null &&
                !lookingAt.equals(I18n.translate("waila.widget.placeholder"));
    }
}
