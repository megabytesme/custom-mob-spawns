package mod.megabytesme.custommobspawns.mixin.entity;

import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;

import mod.megabytesme.custommobspawns.CustomMobSpawns;

@Mixin(AmbientEntity.class)
public abstract class MixinAmbientEntity extends MobEntity {

    protected MixinAmbientEntity() {
        super(null, null);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !CustomMobSpawns.SPAWNS_CONFIG.ambientPersistent;
    }
    
}
