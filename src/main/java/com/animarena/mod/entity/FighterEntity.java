package com.animarena.mod.entity;

import com.animarena.mod.ability.Ability;
import com.animarena.mod.ability.AbilityManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * الكلاس الأساسي لكل شخصيات المصارعة.
 * كل شخصية (نينجا سريع، ساحر عناصر، إلخ) بتعمل extend للكلاس ده
 * وتبعتله الـ Abilities بتاعتها وقيم الـ base stats.
 */
public abstract class FighterEntity extends HostileEntity {

    private final AbilityManager abilityManager;

    protected FighterEntity(EntityType<? extends HostileEntity> entityType, World world, List<Ability> abilities) {
        super(entityType, world);
        this.abilityManager = new AbilityManager(abilities);
    }

    /** لازم كل شخصية تحدد الـ base attributes بتاعتها (health, speed, damage, armor) */
    public static DefaultAttributeContainer.Builder createFighterAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void initGoals() {
        // ملاحقة أقرب هدف (شخصية تانية أو لاعب) والهجوم بالسلاح العادي لو قريب جداً
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, FighterEntity.class, 16.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));

        // الأهداف: شخصيات تانية الأول، وبعدين اللاعب لو مفيش شخصيات تانية قريبة
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, FighterEntity.class, true,
                (livingEntity) -> livingEntity != this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            LivingEntity target = this.getTarget();
            this.abilityManager.tick(this, target);
        }
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    @Override
    public boolean canEquip(net.minecraft.item.ItemStack stack, EquipmentSlot slot) {
        return false; // الشخصيات معندهاش تجهيز يتلبس، كل حاجة عبارة عن قدرات
    }
}
