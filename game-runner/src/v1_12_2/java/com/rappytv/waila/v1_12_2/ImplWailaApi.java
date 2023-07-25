package com.rappytv.waila.v1_12_2;

import com.rappytv.waila.util.IWailaApi;
import net.labymod.api.models.Implements;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

@Implements(IWailaApi.class)
public class ImplWailaApi implements IWailaApi {

    @Override
    public String getLookingAt(boolean fluid, int range) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;

        if(player == null) return null;
        RayTraceResult result = rayTrace(player, range, fluid);
        if(result == null || result.typeOfHit != Type.BLOCK || minecraft.world == null) return null;
        IBlockState state = minecraft.world.getBlockState(result.getBlockPos());

        return state.getBlock().getLocalizedName();
    }

    @Nullable
    private RayTraceResult rayTrace(EntityPlayer player, double range, boolean fluid) {
        Vec3d positionEyes = player.getPositionEyes(0f);
        Vec3d look = player.getLook(0f);
        Vec3d rangeLook = positionEyes.add(look.x * range, look.y * range, look.z * range);
        return player.world.rayTraceBlocks(positionEyes, rangeLook, fluid, false, true);
    }
}
