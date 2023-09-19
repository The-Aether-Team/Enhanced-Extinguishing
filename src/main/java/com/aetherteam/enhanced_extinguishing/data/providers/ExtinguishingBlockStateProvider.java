package com.aetherteam.enhanced_extinguishing.data.providers;

//import com.aetherteam.enhanced_extinguishing.block.ExtinguishedWallTorchBlock;
//import net.minecraft.core.Direction;
//import net.minecraft.data.PackOutput;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.LanternBlock;
//import net.minecraftforge.client.model.generators.BlockStateProvider;
//import net.minecraftforge.client.model.generators.ConfiguredModel;
//import net.minecraftforge.client.model.generators.ModelFile;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.registries.ForgeRegistries;
//
//public abstract class ExtinguishingBlockStateProvider extends BlockStateProvider {
//    public ExtinguishingBlockStateProvider(PackOutput output, String id, ExistingFileHelper helper) {
//        super(output, id, helper);
//    }
//
//    public String name(Block block) {
//        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
//        if (location != null) {
//            return location.getPath();
//        } else {
//            throw new IllegalStateException("Unknown block: " + block.toString());
//        }
//    }
//
//    public ResourceLocation texture(String name) {
//        return this.modLoc("block/" + name);
//    }
//
//    public void torch(Block block) {
//        ModelFile model = this.models().withExistingParent(this.name(block), this.mcLoc("block/template_torch")).texture("torch", this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
//        this.simpleBlock(block, model);
//    }
//
//    public void wallTorch(Block block, Block torch) {
//        ModelFile model = this.models().withExistingParent(this.name(block), this.mcLoc("block/template_torch_wall")).texture("torch", this.texture(this.name(torch))).renderType(new ResourceLocation("cutout"));
//        this.getVariantBuilder(block)
//                .partialState().with(ExtinguishedWallTorchBlock.FACING, Direction.EAST).addModels(ConfiguredModel.builder().modelFile(model).build())
//                .partialState().with(ExtinguishedWallTorchBlock.FACING, Direction.NORTH).addModels(ConfiguredModel.builder().modelFile(model).rotationY(270).build())
//                .partialState().with(ExtinguishedWallTorchBlock.FACING, Direction.SOUTH).addModels(ConfiguredModel.builder().modelFile(model).rotationY(90).build())
//                .partialState().with(ExtinguishedWallTorchBlock.FACING, Direction.WEST).addModels(ConfiguredModel.builder().modelFile(model).rotationY(180).build());
//    }
//
//    public void lantern(Block block, String hangingName) {
//        ModelFile model = this.models().withExistingParent(this.name(block), this.mcLoc("block/template_lantern")).texture("lantern", this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
//        ModelFile hanging = this.models().withExistingParent(hangingName, this.mcLoc("block/template_hanging_lantern")).texture("lantern", this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
//        this.getVariantBuilder(block)
//                .partialState().with(LanternBlock.HANGING, false).addModels(ConfiguredModel.builder().modelFile(model).build())
//                .partialState().with(LanternBlock.HANGING, true).addModels(ConfiguredModel.builder().modelFile(hanging).build());
//    }
//}
