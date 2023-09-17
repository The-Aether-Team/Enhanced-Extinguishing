package com.aetherteam.enhanced_extinguishing.data.providers;

import com.aetherteam.aether.recipe.AetherRecipeSerializers;
import com.aetherteam.aether.recipe.builder.BiomeParameterRecipeBuilder;
import com.aetherteam.nitrogen.recipe.BlockStateIngredient;
import com.aetherteam.nitrogen.recipe.builder.BlockStateRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public abstract class ExtinguishingRecipeProvider extends RecipeProvider {
    private static String ID;

    public ExtinguishingRecipeProvider(PackOutput output, String id) {
        super(output);
        ID = id;
    }

    protected static ResourceLocation name(String name) {
        return new ResourceLocation(ID, name);
    }

    protected static BlockStateRecipeBuilder convertPlacement(Block result, Block ingredient, TagKey<Biome> biome) {
        return BiomeParameterRecipeBuilder.recipe(BlockStateIngredient.of(ingredient), result, biome, AetherRecipeSerializers.PLACEMENT_CONVERSION.get());
    }
}
