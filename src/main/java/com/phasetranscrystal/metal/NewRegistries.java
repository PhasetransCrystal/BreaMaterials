package com.phasetranscrystal.metal;

import com.phasetranscrystal.metal.material.Material;
import com.phasetranscrystal.metal.mfeature.MaterialFeatureType;
import com.phasetranscrystal.metal.mitemtype.MaterialItemType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewRegistries {
    public static final Logger LOGGER = LogManager.getLogger("BREA:Material:Registry");

    public static final Registry<MaterialItemType> MATERIAL_ITEM_TYPE = new RegistryBuilder<>(Keys.MATERIAL_ITEM_TYPE).sync(true).create();
    public static final Registry<MaterialFeatureType<?>> MATERIAL_FEATURE = new RegistryBuilder<>(Keys.MATERIAL_FEATURE).sync(true).create();
    public static final Registry<Material> MATERIAL = new RegistryBuilder<>(Keys.MATERIAL).sync(true).create();

    public static class Keys{
        public static final ResourceKey<Registry<MaterialItemType>> MATERIAL_ITEM_TYPE = create("material_item_type");
        public static final ResourceKey<Registry<MaterialFeatureType<?>>> MATERIAL_FEATURE = create("material_feature");
        public static final ResourceKey<Registry<Material>> MATERIAL = create("material");

        public static <T> ResourceKey<Registry<T>> create(String name){
            return ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(BreaMetal.MODID,name));
        }
    }
}
