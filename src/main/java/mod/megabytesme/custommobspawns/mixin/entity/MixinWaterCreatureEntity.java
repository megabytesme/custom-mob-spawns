package mod.megabytesme.custommobspawns.mixin.entity;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import org.spongepowered.asm.mixin.Mixin;

import mod.megabytesme.custommobspawns.CustomMobSpawns;

@Mixin(WaterCreatureEntity.class)
public abstract class MixinWaterCreatureEntity extends MobEntity {

    protected MixinWaterCreatureEntity() {
        super(null, null);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !CustomMobSpawns.SPAWNS_CONFIG.waterPersistent;
    }
    
}
