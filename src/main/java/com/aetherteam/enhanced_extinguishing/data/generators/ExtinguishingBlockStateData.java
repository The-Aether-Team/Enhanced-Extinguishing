package com.aetherteam.enhanced_extinguishing.data.generators;

import com.aetherteam.enhanced_extinguishing.EnhancedExtinguishing;
import com.aetherteam.enhanced_extinguishing.block.ExtinguishingBlocks;
import com.aetherteam.enhanced_extinguishing.data.providers.ExtinguishingBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ExtinguishingBlockStateData extends ExtinguishingBlockStateProvider {
    public ExtinguishingBlockStateData(PackOutput output, ExistingFileHelper helper) {
        super(output, EnhancedExtinguishing.MODID, helper);
    }

    @Override
    public void registerStatesAndModels() {
        this.torch(ExtinguishingBlocks.EXTINGUISHED_TORCH.get());
        this.wallTorch(ExtinguishingBlocks.EXTINGUISHED_WALL_TORCH.get(), ExtinguishingBlocks.EXTINGUISHED_TORCH.get());
        this.lantern(ExtinguishingBlocks.EXTINGUISHED_LANTERN.get(), "extinguished_hanging_lantern");
    }
}
