package com.animarena.mod.ability;

import com.animarena.mod.entity.FighterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

/**
 * قدرة اندفاع سريع تجاه الهدف مع ضربة قوية وتأثير دفع للخلف.
 * مناسبة للشخصية السريعة (ninja-type).
 */
public class DashStrikeAbility implements Ability {

    private final double damage;
    private final int cooldownTicks;
    private final double range;

    public DashStrikeAbility(double damage, int cooldownTicks, double range) {
        this.damage = damage;
        this.cooldownTicks = cooldownTicks;
        this.range = range;
    }

    @Override
    public String getName() {
        return "Dash Strike";
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
        Vec3d direction = target.getPos().subtract(caster.getPos()).normalize();

        // اندفاع سريع تجاه الهدف
        caster.setVelocity(direction.multiply(1.8, 0.15, 1.8));
        caster.velocityModified = true;

        // ضربة فورية بعد الاندفاع
        target.damage(caster.getDamageSources().mobAttack(caster), (float) damage);

        // دفع الهدف للخلف
        target.setVelocity(target.getVelocity().add(direction.multiply(0.6, 0.2, 0.6)));
        target.velocityModified = true;

        if (caster.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SWEEP_ATTACK,
                    target.getX(), target.getBodyY(0.5), target.getZ(), 3, 0.2, 0.2, 0.2, 0.0);
            serverWorld.playSound(null, caster.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                    SoundCategory.HOSTILE, 1.0F, 1.2F);
        }
    }
}
