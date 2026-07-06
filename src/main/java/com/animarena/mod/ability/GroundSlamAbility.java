package com.animarena.mod.ability;

import com.animarena.mod.entity.FighterEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;

import java.util.List;

/**
 * قدرة ضربة أرضية (AOE) بتضرب كل حاجة حواليها بدايرة معينة.
 * مناسبة للشخصية القوية/الضخمة (tank-type).
 */
public class GroundSlamAbility implements Ability {

    private final double damage;
    private final double radius;
    private final int cooldownTicks;
    private final double range;

    public GroundSlamAbility(double damage, double radius, int cooldownTicks, double range) {
        this.damage = damage;
        this.radius = radius;
        this.cooldownTicks = cooldownTicks;
        this.range = range;
    }

    @Override
    public String getName() {
        return "Ground Slam";
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
        Box area = caster.getBoundingBox().expand(radius);
        List<LivingEntity> nearby = caster.getWorld().getEntitiesByClass(
                LivingEntity.class, area, e -> e != caster && e.isAlive());

        for (LivingEntity entity : nearby) {
            entity.damage(caster.getDamageSources().mobAttack(caster), (float) damage);
            entity.setVelocity(entity.getVelocity().add(0, 0.4, 0));
            entity.velocityModified = true;
        }

        if (caster.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
                    caster.getX(), caster.getY() + 0.1, caster.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
            serverWorld.playSound(null, caster.getBlockPos(), SoundEvents.ENTITY_RAVAGER_STUNNED,
                    SoundCategory.HOSTILE, 1.2F, 0.7F);
        }
    }
}
