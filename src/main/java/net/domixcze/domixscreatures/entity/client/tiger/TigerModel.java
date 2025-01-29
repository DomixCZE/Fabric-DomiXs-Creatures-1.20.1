package net.domixcze.domixscreatures.entity.client.tiger;

import net.domixcze.domixscreatures.DomiXsCreatures;
import net.domixcze.domixscreatures.entity.custom.TigerEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TigerModel extends GeoModel<TigerEntity> {

    @Override
    public Identifier getModelResource(TigerEntity animatable) {
        if (animatable.isBaby()) {
            return new Identifier(DomiXsCreatures.MOD_ID, "geo/baby_tiger.geo.json");
        } else if (animatable.getVariant() == TigerVariants.DREAM || animatable.getVariant() == TigerVariants.ALBINO_DREAM) {
            return new Identifier(DomiXsCreatures.MOD_ID, "geo/dream_tiger.geo.json");
        } else {
            return new Identifier(DomiXsCreatures.MOD_ID, "geo/tiger.geo.json");
        }
    }

    @Override
    public Identifier getTextureResource(TigerEntity animatable) {
        if (animatable.isBaby()) {
            if (animatable.getVariant() == TigerVariants.ALBINO) {
                return new Identifier(DomiXsCreatures.MOD_ID, "textures/entity/baby_tiger_albino.png");
            } else {
                return new Identifier(DomiXsCreatures.MOD_ID, "textures/entity/baby_tiger.png");
            }
        } else {
            return switch (animatable.getVariant()) {
                case ALBINO -> new Identifier(DomiXsCreatures.MOD_ID, "textures/entity/tiger_albino.png");
                case DREAM -> new Identifier(DomiXsCreatures.MOD_ID, "textures/entity/dream_tiger.png");
                case ALBINO_DREAM -> new Identifier(DomiXsCreatures.MOD_ID, "textures/entity/dream_tiger_albino.png");
                default -> new Identifier(DomiXsCreatures.MOD_ID, "textures/entity/tiger.png");
            };
        }
    }

    @Override
    public Identifier getAnimationResource(TigerEntity animatable) {
        if (animatable.isBaby()) {
            return new Identifier(DomiXsCreatures.MOD_ID, "animations/baby_tiger.animation.json");
        } else if (animatable.getVariant() == TigerVariants.DREAM || animatable.getVariant() == TigerVariants.ALBINO_DREAM) {
            return new Identifier(DomiXsCreatures.MOD_ID, "animations/dream_tiger.animation.json");
        } else {
            return new Identifier(DomiXsCreatures.MOD_ID, "animations/tiger.animation.json");
        }
    }
}