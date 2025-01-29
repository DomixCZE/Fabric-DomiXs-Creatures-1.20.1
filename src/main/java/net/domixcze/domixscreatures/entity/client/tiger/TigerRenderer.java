package net.domixcze.domixscreatures.entity.client.tiger;

import net.domixcze.domixscreatures.entity.custom.TigerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class TigerRenderer extends GeoEntityRenderer<TigerEntity> {
    public TigerRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new TigerModel());
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
        this.shadowRadius = 0.8F;
    }
}