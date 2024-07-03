package com.rappytv.waila.widget;

import com.rappytv.waila.WailaAddon;
import com.rappytv.waila.widget.WailaHudWidget.WailaHudWidgetConfig;
import net.labymod.api.Laby;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.client.gui.hud.hudwidget.HudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.widget.WidgetHudWidget;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.widget.widgets.hud.HudWidgetWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class WailaHudWidget extends WidgetHudWidget<WailaHudWidgetConfig> {

    private final WailaAddon addon;
    private final WailaWidgetType type;

    public WailaHudWidget(WailaAddon addon, HudWidgetCategory category, WailaWidgetType type) {
        super("waila_" + type.name().toLowerCase(), WailaHudWidgetConfig.class);
        this.addon = addon;
        this.type = type;

        bindCategory(category);
        setIcon(Icon.texture(ResourceLocation.create(
            "waila",
            String.format("textures/%s.png", type.name().toLowerCase())
        )));
    }

    @Override
    public void load(WailaHudWidgetConfig config) {
        super.load(config);

        config.range.addChangeListener((property, oldValue, newValue) ->
            Laby.labyAPI().minecraft().executeOnRenderThread(() ->
                this.requestUpdate("UPDATED_RANGE")
            )
        );
    }

    @Override
    public void initialize(HudWidgetWidget widget) {
        super.initialize(widget);

        widget.addId("waila");
    }

    @Override
    public void onTick(boolean isEditorContext) {
        super.onTick(isEditorContext);
    }

    @Override
    public boolean isVisibleInGame() {
        return true;
    }

    public static class WailaHudWidgetConfig extends HudWidgetConfig {

        @SwitchSetting
        @SliderSetting(min = 5, max = 20)
        private final ConfigProperty<Integer> range = new ConfigProperty<>(5);

        public int range() {
            return range.get();
        }
    }
}
