package com.phasetranscrystal.material.system.material;

import com.phasetranscrystal.material.BreaRegistries;
import com.phasetranscrystal.material.ModDataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * ITypedMaterialObj材料类型对象接口<br>
 * 材料类型对象接口用于获取一个物品的材料信息。我们将其以接口形式提取出来以方便兼容。
 *
 * @see TypedMaterialInfo
 */
public interface ITypedMaterialObj {
    Optional<ResourceLocation> getMaterialId(ItemStack stack);

    default Optional<Material> getMaterial(ItemStack stack) {
        if(getMaterialId(stack).isPresent())
            return Optional.ofNullable(Registry$Material.MATERIAL.get(getMaterialId(stack).get()));
        return Optional.empty();
    }

    default void setMaterial(ItemStack stack, Material material) {
        if (stack.is((Item) this)) {
            //TODO 啊---
            stack.set(ModDataComponents.MATERIALS,material.id.toString());//.putString("material", material.id.toString());
        }
    }

    default void setMaterial(ItemStack stack) {
        setMaterial(stack, BreaRegistries.MaterialReg.MISSING.get());
    }

    default Material getMaterialOrMissing(ItemStack stack) {
        Material m = null;
        if(getMaterialId(stack).isPresent()){
            m = Registry$Material.MATERIAL.get(getMaterialId(stack).get());
        }
        return m == null ? BreaRegistries.MaterialReg.MISSING.get() : m;
    }

    /**
     * 注意：当不设置MaterialItemType时，请注意覆写{@link ITypedMaterialObj#getContent()}与{@link ITypedMaterialObj#getPurity()}方法
     */
    MaterialItemType getMIType();

    /**
     * 含量是该物品所含该种材料的量，单位为10/粒，与匠魂等模组的计量方法相同。<br>
     * 对于矿石等可增产物品的情况，其材料含量应当相同。这时请使用下面的纯度来调整直接产出。<br>
     * 当然 或许这个奇妙的设计一般都不会被使用。也许会被用在神秘或等价的兼容也说不定？
     */
    default long getContent() {
        return getMIType().content;
    }

    default float getPurity() {
        return getMIType().purity;
    }

    default float getAvailable() {
        return getContent() * getPurity();
    }
}
