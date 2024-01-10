package lol.shmokey.explosivearrow;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "explosivearrow")
@Config(name = "ea-config", wrapperName = "EAConfig")
public class EAConfigModel {

    public float ExplosiveStrength = 4f;
}
