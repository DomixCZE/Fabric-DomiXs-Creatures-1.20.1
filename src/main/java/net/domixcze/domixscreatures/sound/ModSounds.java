package net.domixcze.domixscreatures.sound;

import net.domixcze.domixscreatures.DomiXsCreatures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ZAP= registerSoundEvent("zap");

    public static final SoundEvent EEL_ATTACK = registerSoundEvent("eel_attack");

    public static final SoundEvent SPECTRAL_BAT_SCREECH = registerSoundEvent("spectral_bat_screech");

    public static final SoundEvent WHALE_AMBIENT = registerSoundEvent("whale_ambient");
    public static final SoundEvent WHALE_HURT = registerSoundEvent("whale_hurt");
    public static final SoundEvent WHALE_DEATH = registerSoundEvent("whale_death");

    public static final SoundEvent CROCODILE_AMBIENT = registerSoundEvent("crocodile_ambient");
    public static final SoundEvent CROCODILE_HURT = registerSoundEvent("crocodile_hurt");
    public static final SoundEvent CROCODILE_DEATH = registerSoundEvent("crocodile_death");

    public static final SoundEvent TIGER_HURT = registerSoundEvent("tiger_hurt");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(DomiXsCreatures.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        DomiXsCreatures.LOGGER.info("Registering Sounds for " + DomiXsCreatures.MOD_ID);
    }
}