package com.aetherteam.enhanced_extinguishing.block;

import com.aetherteam.enhanced_extinguishing.EnhancedExtinguishing;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExtinguishingBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EnhancedExtinguishing.MODID);

    public static final RegistryObject<Block> EXTINGUISHED_TORCH = BLOCKS.register("extinguished_torch", () -> new ExtinguishedTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> EXTINGUISHED_WALL_TORCH = BLOCKS.register("extinguished_wall_torch", () -> new ExtinguishedWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD).lootFrom(EXTINGUISHED_TORCH)));
    public static final RegistryObject<Block> EXTINGUISHED_LANTERN = BLOCKS.register("extinguished_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).noOcclusion()));
}