package lol.shmokey.explosivearrow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExplosiveArrow implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final ExplosiveArrowItem EXPLOSIVE_ARROW_ITEM = new ExplosiveArrowItem(new FabricItemSettings());
	public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("explosivearrow", "explosive_arrow"),
			FabricEntityTypeBuilder.<ExplosiveArrowEntity>create(SpawnGroup.MISC, ExplosiveArrowEntity::new)
					.dimensions(EntityDimensions.fixed(0.5F, 0.5F)) // dimensions in Minecraft units of the projectile
					.trackRangeBlocks(4).trackedUpdateRate(10) 
					.build());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registries.ITEM, new Identifier("explosivearrow", "explosive_arrow"), EXPLOSIVE_ARROW_ITEM);
		// EXPLOSIVE_ARROW_ITEM);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(EXPLOSIVE_ARROW_ITEM));
		DispenserBlock.registerBehavior(EXPLOSIVE_ARROW_ITEM, new ProjectileDispenserBehavior() {
			protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
				ExplosiveArrowEntity arrowEntity = new ExplosiveArrowEntity(world, position.getX(), position.getY(), position.getZ());
				arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
				return arrowEntity;
			}
		});

		LOGGER.info("Hello Fabric world!");
	}
}
