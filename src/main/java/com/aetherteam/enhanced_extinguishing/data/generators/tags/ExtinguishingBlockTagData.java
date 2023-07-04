package com.aetherteam.enhanced_extinguishing.data.generators.tags;

import com.aetherteam.enhanced_extinguishing.EnhancedExtinguishing;
import com.aetherteam.enhanced_extinguishing.block.ExtinguishingBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ExtinguishingBlockTagData  extends BlockTagsProvider {
    public ExtinguishingBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, EnhancedExtinguishing.MODID, helper);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(ExtinguishingBlocks.EXTINGUISHED_TORCH.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtinguishingBlocks.EXTINGUISHED_LANTERN.get());
    }
}
