package com.phasetranscrystal.material.system.material;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Deprecated(forRemoval = true)
public final class MaterialFeatureHandle<I extends IMaterialFeature<I>>{
    public static final Logger LOGGER = LogManager.getLogger("BREA:Material/MFH");

    public final ResourceLocation id;
    public final Class<I> clazz;
    private List<Holder<MaterialItemType>> typeSet;
    private ImmutableSet<MaterialItemType> typeInsSet;

    @SafeVarargs
    public MaterialFeatureHandle(ResourceLocation id, Class<I> clazz, Holder<MaterialItemType>... types){
        this.typeSet = new ArrayList<>(List.of(types));
        typeSet.addAll(System$Material.MIT4MF_ADDITION.get(id));
        this.clazz = clazz;
        this.id = id;
    }

    public MaterialFeatureHandle(String modid,String path,Class<I> clazz){
        this(ResourceLocation.fromNamespaceAndPath(modid,path),clazz);
    }

    public ImmutableSet<MaterialItemType> getOrCreateSet(){
        if(typeInsSet == null){
            typeInsSet = typeSet.stream().map(Holder::value).collect(ImmutableSet.toImmutableSet());
            typeSet = null;
        }
        return typeInsSet;
    }
}
