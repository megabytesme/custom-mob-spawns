package mod.megabytesme.custommobspawns.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import mod.megabytesme.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SpawnHelper.Info.class, priority = 999)
public abstract class MixinSpawnHelperInfo {
    @Shadow private int spawningChunkCount;
    @Shadow private Object2IntOpenHashMap<SpawnGroup> groupToCount;

    private static final int VANILLA_CHUNK_AREA_EQUIVALENT = 289;

    @Inject(
        method = "isBelowCap(Lnet/minecraft/entity/SpawnGroup;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void customMobSpawns_modifyIsBelowCap(
            SpawnGroup group,
            CallbackInfoReturnable<Boolean> cir) {

        int customDenominator = (int)Math.pow(CustomMobSpawns.SPAWNS_CONFIG.chunkConstant, 2.0);
        
        if (customDenominator <= 0) { 
            customDenominator = VANILLA_CHUNK_AREA_EQUIVALENT;
        }
        
        int customCap = group.getCapacity() * this.spawningChunkCount / customDenominator;
        boolean isBelowCustomCap = this.groupToCount.getInt(group) < customCap;
        cir.setReturnValue(isBelowCustomCap);
    }
}
