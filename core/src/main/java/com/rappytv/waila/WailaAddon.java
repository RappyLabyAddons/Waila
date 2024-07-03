package com.rappytv.waila;

import com.rappytv.waila.WailaAddon.WailaConfig;
import com.rappytv.waila.core.generated.DefaultReferenceStorage;
import com.rappytv.waila.util.IWailaApi;
import com.rappytv.waila.widget.WailaHudWidget;
import com.rappytv.waila.widget.OldWidget;
import com.rappytv.waila.widget.WailaWidgetType;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.configuration.loader.annotation.Exclude;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class WailaAddon extends LabyAddon<WailaConfig> {

    private IWailaApi api;

    @Override
    protected void enable() {
        registerSettingCategory();
        api = ((DefaultReferenceStorage) this.referenceStorageAccessor()).iWailaApi();

        HudWidgetCategory category = new HudWidgetCategory("waila");
        labyAPI().hudWidgetRegistry().categoryRegistry().register(category);
        labyAPI().hudWidgetRegistry().register(new OldWidget(this, category, false));
        labyAPI().hudWidgetRegistry().register(new OldWidget(this, category, true));
        for(WailaWidgetType type : WailaWidgetType.values())
            labyAPI().hudWidgetRegistry().register(new WailaHudWidget(this, category, type));
    }

    @Override
    protected Class<? extends WailaConfig> configurationClass() {
        return WailaConfig.class;
    }

    public IWailaApi api() {
        return api;
    }

    public static class WailaConfig extends AddonConfig {

        @Exclude
        private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

        @Override
        public ConfigProperty<Boolean> enabled() {
            return enabled;
        }
    }
}
