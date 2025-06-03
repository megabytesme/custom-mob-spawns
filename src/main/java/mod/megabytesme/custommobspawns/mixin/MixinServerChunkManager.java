package mod.megabytesme.custommobspawns.mixin;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import mod.megabytesme.custommobspawns.CustomMobSpawns;

import java.util.List;

@Mixin(ServerChunkManager.class)
public abstract class MixinServerChunkManager {

    @Shadow private ServerWorld world;

    @Redirect(
        method = "tickChunks(Lnet/minecraft/util/profiler/Profiler;J)V", // Targets the private method
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/SpawnHelper;collectSpawnableGroups(Lnet/minecraft/world/SpawnHelper$Info;ZZZ)Ljava/util/List;"
        )
    )
    private List<SpawnGroup> customMobSpawns_redirectCollectSpawnableGroups(
            SpawnHelper.Info info,
            boolean originalSpawnAnimals,
            boolean originalSpawnMonsters,
            boolean originalRare
    ) {
        boolean customRareTick = this.world.getTime() % CustomMobSpawns.SPAWNS_CONFIG.rareSpawnTicksToWait == 0L;

        return SpawnHelper.collectSpawnableGroups(info, originalSpawnAnimals, originalSpawnMonsters, customRareTick);
    }
}
