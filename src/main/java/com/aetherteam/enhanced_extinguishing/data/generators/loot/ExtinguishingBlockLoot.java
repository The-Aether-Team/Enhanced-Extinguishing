package com.aetherteam.enhanced_extinguishing.data.generators.loot;

import com.aetherteam.enhanced_extinguishing.block.ExtinguishingBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExtinguishingBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = new HashSet<>();

    public ExtinguishingBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.dropOther(ExtinguishingBlocks.EXTINGUISHED_TORCH.get(), Blocks.TORCH);
        this.dropOther(ExtinguishingBlocks.EXTINGUISHED_LANTERN.get(), Blocks.LANTERN);
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return ExtinguishingBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
    }
}
