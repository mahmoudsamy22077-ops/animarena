package com.animarena.mod.ability;

import com.animarena.mod.entity.FighterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

/**
 * قدرة إطلاق كورة نار عن بعد. مناسبة لشخصية الساحر العنصري.
 */
public class FireballAbility implements Ability {

    private final int cooldownTicks;
    private final double range;

    public FireballAbility(int cooldownTicks, double range) {
        this.cooldownTicks = cooldownTicks;
        this.range = range;
    }

    @Override
    public String getName() {
        return "Ember Bolt";
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
        if (!(caster.getWorld() instanceof ServerWorld serverWorld)) return;

        Vec3d direction = target.getPos().subtract(caster.getPos()).normalize();

        SmallFireballEntity fireball = new SmallFireballEntity(serverWorld, caster,
                direction.x, direction.y, direction.z);
        fireball.setPosition(caster.getX(), caster.getBodyY(0.6), caster.getZ());
        fireball.setVelocity(direction.multiply(1.2));

        serverWorld.spawnEntity(fireball);
        serverWorld.playSound(null, caster.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT,
                SoundCategory.HOSTILE, 1.0F, 1.0F);
    }
}
