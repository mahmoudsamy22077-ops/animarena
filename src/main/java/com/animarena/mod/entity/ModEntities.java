package com.animarena.mod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/** تسجيل كل الـ Entity Types بتاعة الشخصيات */
public class ModEntities {

    public static final EntityType<KaitoWindrunnerEntity> KAITO_WINDRUNNER = register(
            "kaito_windrunner", KaitoWindrunnerEntity::new, EntityDimensions.fixed(0.6F, 1.8F));

    public static final EntityType<DravenIronfistEntity> DRAVEN_IRONFIST = register(
            "draven_ironfist", DravenIronfistEntity::new, EntityDimensions.fixed(0.9F, 2.2F));

    public static final EntityType<RyuaEmberweaveEntity> RYUA_EMBERWEAVE = register(
            "ryua_emberweave", RyuaEmberweaveEntity::new, EntityDimensions.fixed(0.6F, 1.8F));

    public static final EntityType<SelysFrostveilEntity> SELYS_FROSTVEIL = register(
            "selys_frostveil", SelysFrostveilEntity::new, EntityDimensions.fixed(0.6F, 1.8F));

    private static <T extends FighterEntity> EntityType<T> register(
            String path, FabricEntityTypeBuilder.EntityFactory<T> factory, EntityDimensions dimensions) {

        EntityType<T> type = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier("animarena", path),
                FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, factory)
                        .dimensions(dimensions)
                        .build());
        return type;
    }

    /** لازم تتنادى من مركز المود عشان تسجل الـ attributes بتاعة كل شخصية */
    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(KAITO_WINDRUNNER, KaitoWindrunnerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(DRAVEN_IRONFIST, DravenIronfistEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(RYUA_EMBERWEAVE, RyuaEmberweaveEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(SELYS_FROSTVEIL, SelysFrostveilEntity.createAttributes());
    }

    public static void init() {
        // بينادى بس عشان يضمن تحميل الكلاس والـ static fields فوق
    }
}
