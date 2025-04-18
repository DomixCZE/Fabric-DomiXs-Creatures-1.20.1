package net.domixcze.domixscreatures.util;

import net.domixcze.domixscreatures.DomiXsCreatures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> CROCODILE_EGG_HATCHABLE =
                createTag("crocodile_egg_hatchable");

        public static final TagKey<Block> WORM_SPAWNABLE_ON =
                createTag("worm_spawnable_on");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(DomiXsCreatures.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CROCODILE_FOR_BREEDING =
                createTag("crocodile_for_breeding");

        public static final TagKey<Item> PREVENTS_BLEEDING =
                createTag("prevents_bleeding");

        public static final TagKey<Item> ANTLERS =
                createTag("antlers");

        public static final TagKey<Item> ALL_ITEMS =
                createTag("all_items");

        public static final TagKey<Item> CONDUCTIVE_ITEMS =
                createTag("conductive_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(DomiXsCreatures.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> SPAWNS_SAVANNA_CROCODILE = createTag("spawns_savanna_crocodile");
        public static final TagKey<Biome> SPAWNS_ABYSS_EEL = createTag("spawns_abyss_eel");
        public static final TagKey<Biome> SPAWNS_YELLOW_EEL = createTag("spawns_yellow_eel");

        private static TagKey<Biome> createTag(String name) {
            return TagKey.of(RegistryKeys.BIOME, new Identifier(DomiXsCreatures.MOD_ID, name));
        }
    }
}