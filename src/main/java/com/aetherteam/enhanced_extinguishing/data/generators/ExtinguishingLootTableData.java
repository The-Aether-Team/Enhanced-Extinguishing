package com.aetherteam.enhanced_extinguishing.data.generators;

import com.aetherteam.enhanced_extinguishing.data.generators.loot.ExtinguishingBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.HashSet;
import java.util.List;

public class ExtinguishingLootTableData {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, new HashSet<>(), List.of(
                new LootTableProvider.SubProviderEntry(ExtinguishingBlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }
}
