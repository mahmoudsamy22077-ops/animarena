package com.animarena.mod.entity;

import com.animarena.mod.ability.Ability;
import com.animarena.mod.ability.FireballAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

import java.util.List;

/**
 * ريوا إمبرويف - ساحرة عناصر بتحارب عن بعد بكرات نار.
 * شخصية أصلية بالكامل.
 *
 * القصة الخلفية: آخر ناجية من معبد النار القديم، بتتحكم في اللهب
 * بعد ما اندمجت مع روح طائر الفينيق الحارس للمعبد.
 */
public class RyuaEmberweaveEntity extends FighterEntity {

    public RyuaEmberweaveEntity(EntityType<? extends FighterEntity> entityType, World world) {
        super(entityType, world, List.of(
                (Ability) new FireballAbility(50, 12.0) // كول داون (2.5 ثانية), مدى بعيد جداً
        ));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return FighterEntity.createFighterAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0)   // حياة قليلة (هشة عن بعد)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.28)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)  // ضعيفة قريب
                .add(EntityAttributes.GENERIC_ARMOR, 1.0);
    }
}
