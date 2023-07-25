package com.rappytv.waila.util;

import net.labymod.api.reference.annotation.Referenceable;

@Referenceable
public interface IWailaApi {
    String getLookingAt(boolean fluid, int range);
}
