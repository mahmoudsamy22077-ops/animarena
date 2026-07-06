package com.animarena.mod.entity;

import com.animarena.mod.ability.Ability;
import com.animarena.mod.ability.GroundSlamAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

import java.util.List;

/**
 * درافن أيرونفيست - محارب ضخم قوي بيعتمد على الضربات الأرضية اللي بتضرب في دايرة حواليه.
 * شخصية أصلية بالكامل.
 *
 * القصة الخلفية: حداد سابق حول جسمه لدرع حي عن طريق طقوس قديمة،
 * بقى بطيء بس قوته المدمرة مفيش زيها.
 */
public class DravenIronfistEntity extends FighterEntity {

    public DravenIronfistEntity(EntityType<? extends FighterEntity> entityType, World world) {
        super(entityType, world, List.of(
                (Ability) new GroundSlamAbility(8.0, 3.5, 100, 3.0) // ضرر, نطاق الدايرة, كول داون (5 ثواني), مدى التفعيل
        ));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return FighterEntity.createFighterAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 60.0)   // حياة عالية جداً
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2) // بطيء
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0)  // ضرر عادي عالي
                .add(EntityAttributes.GENERIC_ARMOR, 6.0);
    }
}
