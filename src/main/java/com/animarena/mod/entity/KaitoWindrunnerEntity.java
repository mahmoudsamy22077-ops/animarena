package com.animarena.mod.entity;

import com.animarena.mod.ability.Ability;
import com.animarena.mod.ability.DashStrikeAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

import java.util.List;

/**
 * كايتو ويندرانر - محارب نينجا سريع جداً بيعتمد على الاندفاع والضربات المتتالية.
 * شخصية أصلية 100% (اسم، تصميم، وقصة خاصة بالمود).
 *
 * القصة الخلفية: نشأ في قرية جبلية مخفية، تدرب على فن "خطوة الريح"
 * اللي بتخليه يتحرك أسرع من عين الخصم.
 */
public class KaitoWindrunnerEntity extends FighterEntity {

    public KaitoWindrunnerEntity(EntityType<? extends FighterEntity> entityType, World world) {
        super(entityType, world, List.of(
                (Ability) new DashStrikeAbility(6.0, 60, 4.0) // ضرر, كول داون (3 ثواني), مدى
        ));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return FighterEntity.createFighterAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0)   // حياة أقل (هش)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.42) // سريع جداً
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)  // ضرر عادي أقل
                .add(EntityAttributes.GENERIC_ARMOR, 0.0);
    }
}
