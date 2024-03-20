package com.aetherteam.enhanced_extinguishing.data.generators;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.enhanced_extinguishing.EnhancedExtinguishing;
import com.aetherteam.enhanced_extinguishing.block.ExtinguishingBlocks;
import com.aetherteam.enhanced_extinguishing.data.providers.ExtinguishingRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ExtinguishingRecipeData extends ExtinguishingRecipeProvider {
    public ExtinguishingRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, EnhancedExtinguishing.MODID);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        convertPlacement(ExtinguishingBlocks.EXTINGUISHED_TORCH.get(), Blocks.TORCH, AetherTags.Biomes.ULTRACOLD).save(consumer, name("torch_extinguishing_conversion"));
        convertPlacement(ExtinguishingBlocks.EXTINGUISHED_WALL_TORCH.get(), Blocks.WALL_TORCH, AetherTags.Biomes.ULTRACOLD).save(consumer, name("wall_torch_extinguishing_conversion"));
        convertPlacement(ExtinguishingBlocks.EXTINGUISHED_LANTERN.get(), Blocks.LANTERN, AetherTags.Biomes.ULTRACOLD).save(consumer, name("lantern_extinguishing_conversion"));
    }
}
