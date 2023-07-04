package com.aetherteam.enhanced_extinguishing.data.generators;

import com.aetherteam.enhanced_extinguishing.EnhancedExtinguishing;
import com.aetherteam.enhanced_extinguishing.block.ExtinguishingBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ExtinguishingLanguageData extends LanguageProvider {
    public ExtinguishingLanguageData(PackOutput output) {
        super(output, EnhancedExtinguishing.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addBlock(ExtinguishingBlocks.EXTINGUISHED_TORCH, "Torch");
        this.addBlock(ExtinguishingBlocks.EXTINGUISHED_WALL_TORCH, "Wall Torch");
        this.addBlock(ExtinguishingBlocks.EXTINGUISHED_LANTERN, "Lantern");
        this.addPackDescription("mod", "The Aether: Enhanced Extinguishing Resources");
    }

    public void addPackDescription(String packName, String description) {
        this.add("pack." + EnhancedExtinguishing.MODID + "." + packName + ".description", description);
    }
}
