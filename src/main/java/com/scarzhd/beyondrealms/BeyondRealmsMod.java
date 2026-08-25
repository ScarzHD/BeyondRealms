package com.scarzhd.beyondrealms;

import com.scarzhd.beyondrealms.boss.BossDrops;
import com.scarzhd.beyondrealms.registry.ModBlocks;
import com.scarzhd.beyondrealms.registry.ModEntities;
import com.scarzhd.beyondrealms.registry.ModGenetics;
import com.scarzhd.beyondrealms.registry.ModItems;
import com.scarzhd.beyondrealms.registry.ModRealmContent;
import com.scarzhd.beyondrealms.world.ModWorldgen;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeyondRealmsMod implements ModInitializer {
    public static final String MOD_ID = "beyondrealms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModItems.initialize();
        ModRealmContent.initialize();
        ModEntities.initialize();
        ModGenetics.initialize();
        ModWorldgen.initialize();
        BossDrops.initialize();
        LOGGER.info("Beyond Realms 1.4.0 loaded for Minecraft 26.2");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
