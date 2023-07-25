package com.rappytv.waila;

import com.rappytv.waila.config.WailaConfig;
import com.rappytv.waila.core.generated.DefaultReferenceStorage;
import com.rappytv.waila.util.IWailaApi;
import com.rappytv.waila.widget.WailaBlockWidget;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class WailaAddon extends LabyAddon<WailaConfig> {

    private IWailaApi api;

    @Override
    protected void enable() {
        api = ((DefaultReferenceStorage) this.referenceStorageAccessor()).iWailaApi();

        registerSettingCategory();
        labyAPI().hudWidgetRegistry().register(new WailaBlockWidget(this, false));
        labyAPI().hudWidgetRegistry().register(new WailaBlockWidget(this, true));
    }

    @Override
    protected Class<? extends WailaConfig> configurationClass() {
        return WailaConfig.class;
    }

    public IWailaApi api() {
        return api;
    }
}
