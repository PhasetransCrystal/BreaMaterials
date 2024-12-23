package com.phasetranscrystal.material.system.material.expansion.materialfeature;

import com.phasetranscrystal.material.BreaRegistries;
import com.phasetranscrystal.material.system.material.IMaterialFeature;
import com.phasetranscrystal.material.system.material.MaterialFeatureType;
import com.phasetranscrystal.material.system.material.MaterialItemType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class MetalMF implements IMaterialFeature<MetalMF> {
    public MetalMF(){}

    @Override
    public DeferredHolder<MaterialFeatureType<?>, MaterialFeatureType<MetalMF>> getType() {
//        return Registries.MaterialReg.METAL;
        return BreaRegistries.MaterialReg.METAL;
    }

    @Override
    public HashSet<MaterialItemType> forItemTypes() {
        if(types == null){
            types = new HashSet<>();
//            types.addAll(List.of(Registries.MaterialReg.INGOT.get()));
            types.add(BreaRegistries.MaterialReg.INGOT.get());
        }
        return types;
    }

    @Override
    public @Nullable List<ResourceLocation> dependencies() {
        return List.of(BreaRegistries.MaterialReg.PHASE_TRANSIT.getId(), BreaRegistries.MaterialReg.THERMO.getId());
    }

    private HashSet<MaterialItemType> types;
}
