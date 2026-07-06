package com.animarena.mod.ability;

import com.animarena.mod.entity.FighterEntity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * بيتابع الكول داون لكل قدرة لكل شخصية على حدة.
 * كل FighterEntity عنده نسخة واحدة من الكلاس ده.
 */
public class AbilityManager {

    private final List<Ability> abilities;
    private final Map<Ability, Integer> cooldowns = new HashMap<>();

    public AbilityManager(List<Ability> abilities) {
        this.abilities = abilities;
    }

    /** ينادى كل tick من داخل الـ Entity. بيقلل الكول داون وبيجرب يستخدم قدرة لو الهدف في المدى. */
    public void tick(FighterEntity caster, LivingEntity target) {
        // تقليل كل الكول داونز
        for (Ability ability : abilities) {
            cooldowns.merge(ability, -1, (oldVal, dec) -> Math.max(0, oldVal + dec));
        }

        if (target == null || !target.isAlive()) return;

        double distSq = caster.squaredDistanceTo(target);

        for (Ability ability : abilities) {
            int remaining = cooldowns.getOrDefault(ability, 0);
            if (remaining <= 0 && distSq <= ability.getRange() * ability.getRange()) {
                ability.execute(caster, target);
                cooldowns.put(ability, ability.getCooldownTicks());
                break; // قدرة واحدة بس في كل tick عشان منغرقش الهدف بكل حاجة مرة واحدة
            }
        }
    }

    public List<Ability> getAbilities() {
        return abilities;
    }
}
