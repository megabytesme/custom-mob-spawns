package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PigEntity.class)
public abstract class MixinPigEntity extends MobEntity {
    @Shadow
    private SaddledComponent saddledComponent;

    protected MixinPigEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !(CustomMobSpawns.SPAWNS_CONFIG.passivePersistent);
    }
}
