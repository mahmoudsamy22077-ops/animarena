package com.animarena.mod.ability;

import com.animarena.mod.entity.FighterEntity;
import net.minecraft.entity.LivingEntity;

/**
 * واجهة عامة لأي قدرة خاصة تقدر شخصية تستخدمها.
 * كل شخصية بتاخد List<Ability> وتستخدمهم حسب الكول داون بتاعهم.
 */
public interface Ability {

    /** اسم القدرة (بيتعرض ممكن فوق الشخصية أو في اللوج) */
    String getName();

    /** المدة بالـ ticks (20 tick = ثانية واحدة) قبل ما القدرة تتكرر تاني */
    int getCooldownTicks();

    /** المدى اللي لازم يكون الهدف جواه عشان تستخدم القدرة */
    double getRange();

    /**
     * تنفيذ القدرة الفعلي.
     * @param caster الشخصية اللي بتستخدم القدرة
     * @param target الهدف الحالي
     */
    void execute(FighterEntity caster, LivingEntity target);
}
