package lol.shmokey.explosivearrow.client;

import lol.shmokey.explosivearrow.ExplosiveArrow;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import lol.shmokey.explosivearrow.ExplosiveArrowRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ExplosiveArrowClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ExplosiveArrow.EXPLOSIVE_ARROW_ENTITY,
                (context) -> new ExplosiveArrowRenderer(context));
        // older versions may have to use
        /*
         * EntityRendererRegistry.INSTANCE.register(ProjectileTutorialMod.
         * PackedSnowballEntityType, (context) ->
         * new FlyingItemEntityRenderer(context));
         */
    }
}
