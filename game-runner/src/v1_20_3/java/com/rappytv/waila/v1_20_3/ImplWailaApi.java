package com.rappytv.waila.v1_20_3;

import com.rappytv.waila.util.IWailaApi;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;

@Implements(IWailaApi.class)
public class ImplWailaApi implements IWailaApi {

    @Override
    public String getLookingAt(boolean fluid, int range) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;

        if(player == null) return null;
        HitResult result = player.pick(range, 0f, fluid);
        if(result.getType() != Type.BLOCK || minecraft.level == null) return null;

        BlockState state = minecraft.level.getBlockState(((BlockHitResult) result).getBlockPos());

        return state.getBlock().getName().getString();
    }
}
