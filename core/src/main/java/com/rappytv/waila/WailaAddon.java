package com.rappytv.waila;

import com.rappytv.waila.config.WailaConfig;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class WailaAddon extends LabyAddon<WailaConfig> {

    @Override
    protected void enable() {
        registerSettingCategory();
    }

    @Override
    protected Class<? extends WailaConfig> configurationClass() {
        return WailaConfig.class;
    }
}
