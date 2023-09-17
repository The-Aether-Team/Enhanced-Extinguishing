package com.aetherteam.enhanced_extinguishing.block;

import com.aetherteam.enhanced_extinguishing.EnhancedExtinguishing;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExtinguishingBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EnhancedExtinguishing.MODID);

    public static final RegistryObject<Block> EXTINGUISHED_TORCH = BLOCKS.register("extinguished_torch", () -> new ExtinguishedTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).lootFrom(() -> Blocks.TORCH)));
    public static final RegistryObject<Block> EXTINGUISHED_WALL_TORCH = BLOCKS.register("extinguished_wall_torch", () -> new ExtinguishedWallTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).lootFrom(() -> Blocks.TORCH)));
    public static final RegistryObject<Block> EXTINGUISHED_LANTERN = BLOCKS.register("extinguished_lantern", () -> new ExtinguishedLanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY).noOcclusion().lootFrom(() -> Blocks.LANTERN)));
}