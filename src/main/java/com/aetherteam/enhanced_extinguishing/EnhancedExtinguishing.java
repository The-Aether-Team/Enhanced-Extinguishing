package com.aetherteam.enhanced_extinguishing;

import com.aetherteam.enhanced_extinguishing.block.ExtinguishingBlocks;
import com.aetherteam.enhanced_extinguishing.data.generators.ExtinguishingBlockStateData;
import com.aetherteam.enhanced_extinguishing.data.generators.ExtinguishingLanguageData;
import com.aetherteam.enhanced_extinguishing.data.generators.ExtinguishingRecipeData;
import com.aetherteam.enhanced_extinguishing.data.generators.tags.ExtinguishingBlockTagData;
import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Mod(EnhancedExtinguishing.MODID)
public class EnhancedExtinguishing {
    public static final String MODID = "aether_enhanced_extinguishing";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnhancedExtinguishing(IEventBus bus, Dist dist) {
        DeferredRegister<?>[] registers = {
                ExtinguishingBlocks.BLOCKS,
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }

        bus.addListener(this::dataSetup);
        bus.addListener(this::packSetup);
    }

    public void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();

        // Client Data
        generator.addProvider(event.includeClient(), new ExtinguishingBlockStateData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ExtinguishingLanguageData(packOutput));

        // Server Data
        generator.addProvider(event.includeServer(), new ExtinguishingRecipeData(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ExtinguishingBlockTagData(packOutput, lookupProvider, fileHelper));

        // pack.mcmeta
        PackMetadataGenerator packMeta = new PackMetadataGenerator(packOutput);
        Map<PackType, Integer> packTypes = Map.of(PackType.SERVER_DATA, SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
        packMeta.add(PackMetadataSection.TYPE, new PackMetadataSection(Component.translatable("pack.aether_enhanced_extinguishing.mod.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES), packTypes));
        generator.addProvider(true, packMeta);
    }

    public void packSetup(AddPackFindersEvent event) {
        // Data Packs
        this.setupRecipeOverridePack(event);
    }

    private void setupRecipeOverridePack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = ModList.get().getModFileById(EnhancedExtinguishing.MODID).getFile().findResource("packs/recipe_override");
            PackMetadataSection metadata = new PackMetadataSection(Component.literal(""), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/extinguishing_recipe_override",
                            Component.literal(""),
                            true,
                            new PathPackResources.PathResourcesSupplier(resourcePath, true),
                            new Pack.Info(metadata.description(), metadata.packFormat(PackType.SERVER_DATA), metadata.packFormat(PackType.CLIENT_RESOURCES), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), true),
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    ));
        }
    }
}