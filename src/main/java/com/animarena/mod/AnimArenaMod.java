package com.animarena.mod;

import com.animarena.mod.entity.ModEntities;
import com.animarena.mod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimArenaMod implements ModInitializer {

    public static final String MOD_ID = "animarena";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("AnimArena: تحميل شخصيات المصارعة...");

        ModEntities.init();
        ModEntities.registerAttributes();
        ModItems.init();

        LOGGER.info("AnimArena: جاهز! 4 شخصيات متاحة للاستدعاء.");
    }
}
