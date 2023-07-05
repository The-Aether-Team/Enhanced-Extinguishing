package com.aetherteam.enhanced_extinguishing.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class ExtinguishedLanternBlock extends LanternBlock {
    public ExtinguishedLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId() {
        return Blocks.LANTERN.getDescriptionId();
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return Blocks.LANTERN.getCloneItemStack(state, target, level, pos, player);
    }
}
