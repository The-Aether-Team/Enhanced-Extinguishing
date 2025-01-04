package com.aetherteam.enhanced_extinguishing;

import com.aetherteam.aetherfabric.events.AddPackFindersEvent;
import com.aetherteam.aetherfabric.registries.DeferredRegister;
import com.aetherteam.enhanced_extinguishing.block.ExtinguishingBlocks;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class)
public class EnhancedExtinguishing implements ModInitializer, ClientModInitializer {
    public static final String MODID = "aether_enhanced_extinguishing";
    private static final Logger LOGGER = LogUtils.getLogger();

    public void onInitialize() {
        DeferredRegister<?>[] registers = {
                ExtinguishingBlocks.BLOCKS,
        };

        for (DeferredRegister<?> register : registers) {
            register.addEntriesToRegistry();
        }

        //bus.addListener(this::dataSetup);
        AddPackFindersEvent.EVENT.register(this::packSetup);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ExtinguishingBlocks.EXTINGUISHED_TORCH.get(), ExtinguishingBlocks.EXTINGUISHED_WALL_TORCH.get());
    }

//    public void dataSetup(GatherDataEvent event) {
//        DataGenerator generator = event.getGenerator();
//        ExistingFileHelper fileHelper = event.getExistingFileHelper();
//        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
//        PackOutput packOutput = generator.getPackOutput();
//
//        // Client Data
//        generator.addProvider(event.includeClient(), new ExtinguishingBlockStateData(packOutput, fileHelper));
//        generator.addProvider(event.includeClient(), new ExtinguishingLanguageData(packOutput));
//
//        // Server Data
//        generator.addProvider(event.includeServer(), new ExtinguishingRecipeData(packOutput, lookupProvider));
//        generator.addProvider(event.includeServer(), new ExtinguishingBlockTagData(packOutput, lookupProvider, fileHelper));
//
//        // pack.mcmeta
//        generator.addProvider(true, new PackMetadataGenerator(packOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(
//                Component.translatable("pack.aether_enhanced_extinguishing.mod.description"),
//                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
//                Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));
//    }

    public void packSetup(AddPackFindersEvent event) {
        // Data Packs
        this.setupRecipeOverridePack(event);
    }

    private void setupRecipeOverridePack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = FabricLoader.getInstance().getModContainer(EnhancedExtinguishing.MODID).orElseThrow().findPath("packs/recipe_override").orElseThrow();
            PackMetadataSection metadata = new PackMetadataSection(Component.literal(""), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), Optional.empty());
            event.addRepositorySource((source) ->
                    source.accept(new Pack(
                            new PackLocationInfo("builtin/extinguishing_recipe_override", Component.literal(""), PackSource.BUILT_IN, Optional.empty()),
                            new PathPackResources.PathResourcesSupplier(resourcePath),
                            new Pack.Metadata(metadata.description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of()),
                            new PackSelectionConfig(true, Pack.Position.TOP, false)
                    )));
        }
    }
}