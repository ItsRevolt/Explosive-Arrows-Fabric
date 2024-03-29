package lol.shmokey.explosivearrow;

import io.wispforest.owo.Owo;
import io.wispforest.owo.config.ConfigWrapper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;

public class ExplosiveArrowEntity extends PersistentProjectileEntity {
    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>) entityType, world);
    }

    public ExplosiveArrowEntity(World world, LivingEntity owner) {
        super(ExplosiveArrow.EXPLOSIVE_ARROW_ENTITY, owner, world);
    }

    public ExplosiveArrowEntity(World world, double x, double y, double z) {
        super(ExplosiveArrow.EXPLOSIVE_ARROW_ENTITY, x, y, z, world);
    }



    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + .5f, this.getZ(), 0.0, 0.0, 0.0);
        }

    }

    @Override
    public ItemStack asItemStack() {
        return new ItemStack(ExplosiveArrow.EXPLOSIVE_ARROW_ITEM);
    }


    @Override
    public void onBlockHit(BlockHitResult hitResult) {
        super.onBlockHit(hitResult);
        Vec3d targetPos = hitResult.getPos();
        explode(targetPos);
    }
    @Override
    public void onEntityHit(EntityHitResult hitResult) {
        super.onEntityHit(hitResult);
        Vec3d targetPos = hitResult.getPos();
        explode(targetPos);
    }

    private void explode(Vec3d targetPos) {
        if (this.isSubmergedInWater()) return;
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(this, targetPos.x, targetPos.y, targetPos.z, ExplosiveArrow.CONFIG.ExplosiveStrength(), ExplosionSourceType.TNT);
        }
        this.remove(RemovalReason.DISCARDED);
    }
}
