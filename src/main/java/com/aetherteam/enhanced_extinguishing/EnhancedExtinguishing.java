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
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.resource.PathPackResources;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Mod(EnhancedExtinguishing.MODID)
public class EnhancedExtinguishing {
    public static final String MODID = "aether_enhanced_extinguishing";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnhancedExtinguishing() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DeferredRegister<?>[] registers = {
                ExtinguishingBlocks.BLOCKS,
        };

        for (DeferredRegister<?> register : registers) {
            register.register(modEventBus);
        }

        modEventBus.addListener(this::dataSetup);
        modEventBus.addListener(this::packSetup);
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
        generator.addProvider(event.includeServer(), new ExtinguishingRecipeData(packOutput));
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
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(EnhancedExtinguishing.MODID).getFile().getFileName() + ":" + resourcePath, true, resourcePath);
            PackMetadataSection metadata = new PackMetadataSection(Component.literal(""), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/extinguishing_recipe_override",
                            Component.literal(""),
                            true,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), true),
                            PackType.SERVER_DATA,
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    ));
        }
    }
}