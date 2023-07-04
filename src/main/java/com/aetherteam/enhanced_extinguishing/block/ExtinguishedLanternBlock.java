package com.aetherteam.enhanced_extinguishing.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;

public class ExtinguishedLanternBlock extends LanternBlock {
    public ExtinguishedLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId() {
        return Blocks.LANTERN.getDescriptionId();
    }
}
