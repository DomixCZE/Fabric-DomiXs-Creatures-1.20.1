package net.domixcze.domixscreatures.entity.ai;

import net.domixcze.domixscreatures.entity.custom.CrocodileEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CrocodileMeleeAttackGoal extends MeleeAttackGoal {
    private final CrocodileEntity crocodile;

    public CrocodileMeleeAttackGoal(CrocodileEntity crocodile, double speed, boolean pauseWhenMobIdle) {
        super(crocodile, speed, pauseWhenMobIdle);
        this.crocodile = crocodile;
    }

    @Override
    public boolean canStart() {
        if (this.crocodile.isBaby()) {
            return false;
        }

        LivingEntity target = this.crocodile.getTarget();
        if (target instanceof PlayerEntity player && player.isSneaking() || this.crocodile.isSleeping()) {
            return false;
        }
        return super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        LivingEntity target = this.crocodile.getTarget();
        if (target != null && this.crocodile.isAlive()) {
            return true;
        }
        return super.shouldContinue();
    }
}