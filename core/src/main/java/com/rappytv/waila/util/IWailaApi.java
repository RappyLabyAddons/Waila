package com.rappytv.waila.util;

import net.labymod.api.reference.annotation.Referenceable;
import org.jetbrains.annotations.Nullable;

@Referenceable
public interface IWailaApi {

    @Nullable
    String getLookingAt(boolean fluid, int range);
}
