package com.aetherteam.enhanced_extinguishing.block;

import com.aetherteam.enhanced_extinguishing.EnhancedExtinguishing;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ExtinguishingBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, EnhancedExtinguishing.MODID);

    public static final Supplier<Block> EXTINGUISHED_TORCH = BLOCKS.register("extinguished_torch", () -> new ExtinguishedTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).lootFrom(() -> Blocks.TORCH)));
    public static final Supplier<Block> EXTINGUISHED_WALL_TORCH = BLOCKS.register("extinguished_wall_torch", () -> new ExtinguishedWallTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).lootFrom(() -> Blocks.TORCH)));
    public static final Supplier<Block> EXTINGUISHED_LANTERN = BLOCKS.register("extinguished_lantern", () -> new ExtinguishedLanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY).noOcclusion().lootFrom(() -> Blocks.LANTERN)));
}