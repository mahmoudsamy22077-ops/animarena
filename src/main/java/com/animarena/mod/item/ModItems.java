package com.animarena.mod.item;

import com.animarena.mod.entity.ModEntities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/** عناصر استحضار الشخصيات (زي spawn egg عادي بس بألوان كل شخصية) */
public class ModItems {

    public static final Item KAITO_SUMMON = register("kaito_windrunner_summon",
            new SpawnEggItem(ModEntities.KAITO_WINDRUNNER, 0x2E2E2E, 0x9FE7FF, new Item.Settings()));

    public static final Item DRAVEN_SUMMON = register("draven_ironfist_summon",
            new SpawnEggItem(ModEntities.DRAVEN_IRONFIST, 0x4A4A4A, 0xB33A3A, new Item.Settings()));

    public static final Item RYUA_SUMMON = register("ryua_emberweave_summon",
            new SpawnEggItem(ModEntities.RYUA_EMBERWEAVE, 0xFF6A00, 0xFFD84D, new Item.Settings()));

    public static final Item SELYS_SUMMON = register("selys_frostveil_summon",
            new SpawnEggItem(ModEntities.SELYS_FROSTVEIL, 0xCFEFFF, 0x4FA8D8, new Item.Settings()));

    private static Item register(String path, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("animarena", path), item);
    }

    public static void init() {
        // بينادى بس عشان يضمن تحميل الكلاس والـ static fields فوق
    }
}
