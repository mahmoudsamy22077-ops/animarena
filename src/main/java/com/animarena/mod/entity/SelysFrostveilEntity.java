package com.animarena.mod.entity;

import com.animarena.mod.ability.Ability;
import com.animarena.mod.ability.GuardianShieldAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

import java.util.List;

/**
 * سيليس فروستفيل - حارسة دفاعية بتستخدم درع جليدي وتعافي ذاتي.
 * شخصية أصلية بالكامل.
 *
 * القصة الخلفية: كاهنة معبد الشتاء الأبدي، بتستدعي طاقة الجليد
 * عشان تحمي نفسها وتصمد في المعارك الطويلة.
 */
public class SelysFrostveilEntity extends FighterEntity {

    public SelysFrostveilEntity(EntityType<? extends FighterEntity> entityType, World world) {
        super(entityType, world, List.of(
                (Ability) new GuardianShieldAbility(140, 6.0) // كول داون (7 ثواني), مدى تفعيل الدرع لما عدو يقرب
        ));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return FighterEntity.createFighterAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 45.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.5)
                .add(EntityAttributes.GENERIC_ARMOR, 5.0);
    }
}
