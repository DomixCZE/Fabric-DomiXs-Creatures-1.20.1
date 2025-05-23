package net.domixcze.domixscreatures.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.alias.StructurePoolAliasLookup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.DimensionPadding;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class GroundStructures extends Structure {
    public static final MapCodec<GroundStructures> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(GroundStructures.configCodecBuilder(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
                    DimensionPadding.CODEC.optionalFieldOf("dimension_padding", JigsawStructure.DEFAULT_DIMENSION_PADDING).forGetter(structure -> structure.dimensionPadding),
                    StructureLiquidSettings.codec.optionalFieldOf("liquid_settings", JigsawStructure.DEFAULT_LIQUID_SETTINGS).forGetter(structure -> structure.liquidSettings)
            ).apply(instance, GroundStructures::new));

    private final RegistryEntry<StructurePool> startPool;
    private final Optional<Identifier> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Type> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    private final DimensionPadding dimensionPadding;
    private final StructureLiquidSettings liquidSettings;

    public GroundStructures(Structure.Config config,
                            RegistryEntry<StructurePool> startPool,
                            Optional<Identifier> startJigsawName,
                            int size,
                            HeightProvider startHeight,
                            Optional<Heightmap.Type> projectStartToHeightmap,
                            int maxDistanceFromCenter,
                            DimensionPadding dimensionPadding,
                            StructureLiquidSettings liquidSettings)
    {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.dimensionPadding = dimensionPadding;
        this.liquidSettings = liquidSettings;
    }

    private static boolean extraSpawningChecks(Structure.Context context) {
        ChunkPos chunkPos = context.chunkPos();
        int startX = chunkPos.getStartX();
        int startZ = chunkPos.getStartZ();

        if (context.chunkGenerator().getHeightInGround(
                startX,
                startZ,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                context.world(),
                context.noiseConfig()) > 80) {
            return false;
        }

        int heightThreshold = 1;

        int[] sampleX = {startX, startX + 15, startX + 15, startX};
        int[] sampleZ = {startZ, startZ, startZ + 15, startZ + 15};

        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for (int i = 0; i < sampleX.length; i++) {
            int height = context.chunkGenerator().getHeightInGround(sampleX[i], sampleZ[i], Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, context.world(), context.noiseConfig());
            minHeight = Math.min(minHeight, height);
            maxHeight = Math.max(maxHeight, height);
        }

        return (maxHeight - minHeight) <= heightThreshold;
    }

    @Override
    public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context) {

        if (!GroundStructures.extraSpawningChecks(context)) {
            return Optional.empty();
        }

        int startY = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));

        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getStartX(), startY, chunkPos.getStartZ());

        Optional<StructurePosition> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        context,
                        this.startPool,
                        this.startJigsawName,
                        this.size,
                        blockPos,
                        false,
                        this.projectStartToHeightmap,
                        this.maxDistanceFromCenter,
                        StructurePoolAliasLookup.EMPTY,
                        this.dimensionPadding,
                        this.liquidSettings
                );

        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> getType() {
        return ModStructures.GROUND_STRUCTURES;
    }
}