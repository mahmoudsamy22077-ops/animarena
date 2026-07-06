package com.animarena.mod.ability;

import com.animarena.mod.entity.FighterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

/**
 * قدرة درع ذاتي: امتصاص ضرر + تعافي مؤقت.
 * مناسبة للشخصية الدفاعية (guardian-type). القدرة دي بتتفعل على النفس مش على الهدف.
 */
public class GuardianShieldAbility implements Ability {

    private final int cooldownTicks;
    private final double range;

    public GuardianShieldAbility(int cooldownTicks, double range) {
        this.cooldownTicks = cooldownTicks;
        this.range = range;
    }

    @Override
    public String getName() {
        return "Guardian Shield";
    }

    @Override
    public int getCooldownTicks() {
        return cooldownTicks;
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public void execute(FighterEntity caster, LivingEntity target) {
        // القدرة دي بتتفعل على caster نفسه لما عدو يبقى قريب (مش لازم هجوم مباشر)
        caster.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        caster.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 1));

        if (caster.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING,
                    caster.getX(), caster.getBodyY(0.5), caster.getZ(), 20, 0.4, 0.4, 0.4, 0.05);
            serverWorld.playSound(null, caster.getBlockPos(), SoundEvents.ITEM_TOTEM_USE,
                    SoundCategory.HOSTILE, 0.6F, 1.4F);
        }
    }
}
