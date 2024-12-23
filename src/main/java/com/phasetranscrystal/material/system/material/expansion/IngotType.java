package com.phasetranscrystal.material.system.material.expansion;

import com.phasetranscrystal.material.ModBusConsumer;
import com.phasetranscrystal.material.system.material.Material;
import com.phasetranscrystal.material.system.material.MaterialItemType;
import com.phasetranscrystal.material.system.material.client.TMIModel;
import com.phasetranscrystal.material.system.material.datagen.MitModelGen;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.HashMap;

public class IngotType extends MaterialItemType {

    public static final Logger LOGGER = LogManager.getLogger("BREA:Material:MaterialItemType/Ingot");

    public final HashMap<Material, IngotItem> WITH_MATERIAL = new HashMap<>();
    public final HashMap<ResourceLocation, IngotItem> WITH_RESOURCE = new HashMap<>();

    public IngotType(float purity, ResourceLocation id) {
        super(90, purity, id);
    }

    @Override
    public void secondaryRegistry(RegisterEvent event, Material material) {
        Registry<Item> registry = (Registry<Item>) ModBusConsumer.REGS_MAP.get(Registries.ITEM);

        if (material.id.toString().equals("brea:missing")) {
            return;
        }
        IngotItem ingot = new IngotItem(material);
        ResourceLocation location = material.id.withPath(id -> id + "_material_ingot");
        Registry.register(registry, location, ingot);
        WITH_MATERIAL.put(material, ingot);
        WITH_RESOURCE.put(location, ingot);
    }

    @Override
    public void gatherKeyForDatagen(MitModelGen ins) {
        super.gatherKeyForDatagen(ins);
//        for (ResourceLocation id : WITH_RESOURCE.keySet()) {
//            ins.basicItem(id);
//        }
    }

    @VisibleForTesting
    @Override
    public void consumeModelReg(ModelEvent.ModifyBakingResult event) {
        super.consumeModelReg(event);
        ModelBakery bakery = event.getModelBakery();
        for (IngotItem item : WITH_RESOURCE.values()) {
            event.getModels().put(ModelResourceLocation.inventory(BuiltInRegistries.ITEM.getKey(item)), new TMIModel(bakery, this));
        }
    }

    @Override
    public void attachToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        super.attachToCreativeTab(event);
        for (IngotItem item : WITH_RESOURCE.values()) {
            event.accept(item);
        }
    }
}
