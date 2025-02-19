package lol.shmokey.explosivearrow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExplosiveArrow implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final lol.shmokey.explosivearrow.EAConfig CONFIG = lol.shmokey.explosivearrow.EAConfig.createAndLoad();
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static ExplosiveArrowItem EXPLOSIVE_ARROW_ITEM;
	public static EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY;
	/*
	public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of("explosivearrow", "explosive_arrow"),
			EntityType.Builder.<ExplosiveArrowEntity>create(ExplosiveArrowEntity::new, SpawnGroup.MISC)
					.dimensions(0.5F, 0.5F) // dimensions in Minecraft
					.maxTrackingRange(4)// units of the projectile
					.trackingTickInterval(10)
					.build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), Identifier.of("explosivearrow", "explosive_arrow"))));

	*/
	@Override

	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		EXPLOSIVE_ARROW_ITEM = (ExplosiveArrowItem) registerItem("explosive_arrow", new Item.Settings());

		// Register Explosive Arrow Entity
		EXPLOSIVE_ARROW_ENTITY = Registry.register(
				Registries.ENTITY_TYPE,
				Identifier.of("explosivearrow", "explosive_arrow"),
				EntityType.Builder.<ExplosiveArrowEntity>create(ExplosiveArrowEntity::new, SpawnGroup.MISC)
						.dimensions(0.5F, 0.5F)
						.maxTrackingRange(4)
						.trackingTickInterval(10)
						.build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), Identifier.of("explosivearrow", "explosive_arrow"))));

		// EXPLOSIVE_ARROW_ITEM);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(EXPLOSIVE_ARROW_ITEM));
		DispenserBlock.registerBehavior(EXPLOSIVE_ARROW_ITEM, new ProjectileDispenserBehavior(EXPLOSIVE_ARROW_ITEM) {
			protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
				ExplosiveArrowEntity arrowEntity = new ExplosiveArrowEntity(world, position.getX(), position.getY(), position.getZ());
				arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
				return arrowEntity;
			}
		});

		LOGGER.info("Hello Fabric world!");
	}

	private static Item registerItem(String name, Item.Settings settings) {
		Identifier id = Identifier.of("explosivearrow", "explosive_arrow");
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
		Item.Settings updatedSettings = settings.registryKey(key);
		return Registry.register(Registries.ITEM, key, new ExplosiveArrowItem(updatedSettings));
	}
}
