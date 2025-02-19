package lol.shmokey.explosivearrow;

import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ArrowEntityRenderState;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class ExplosiveArrowRenderer extends ProjectileEntityRenderer<ExplosiveArrowEntity, ArrowEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.of("explosivearrow","textures/entity/projectiles/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public ArrowEntityRenderState createRenderState() {
        return new ArrowEntityRenderState();
    }

}
