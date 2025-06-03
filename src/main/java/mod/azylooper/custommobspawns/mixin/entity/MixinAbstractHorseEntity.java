package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractHorseEntity.class)
public abstract class MixinAbstractHorseEntity extends MobEntity {
        protected MixinAbstractHorseEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected abstract boolean getHorseFlag(int bitmask);

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        boolean currentlySaddled = this.getHorseFlag(4);
        return !(CustomMobSpawns.SPAWNS_CONFIG.passivePersistent || currentlySaddled);
    }
}
