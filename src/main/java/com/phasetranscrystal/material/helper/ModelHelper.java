package com.phasetranscrystal.material.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.resources.ResourceLocation;

public class ModelHelper {
    public static ItemTransforms copyDefaultItemTransforms() {
        return new ItemTransforms(((SimpleBakedModel) Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("minecraft","gold_ingot"), "inventory"))).getTransforms());
    }
}
