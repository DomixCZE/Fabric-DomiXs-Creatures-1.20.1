package net.domixcze.domixscreatures.entity.ai;

import net.domixcze.domixscreatures.entity.custom.WispEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class WispMeleeAttackGoal extends MeleeAttackGoal {

    private final WispEntity wisp;

    public WispMeleeAttackGoal(WispEntity wisp, double speed, boolean pauseWhenMobIdle) {
        super(wisp, speed, pauseWhenMobIdle);
        this.wisp = wisp;
    }

    @Override
    public boolean canStart() {
        if (!this.wisp.isWearingSkull()) {
            return false;
        }

        if (this.wisp.isTamed()) {
            LivingEntity owner = this.wisp.getOwner();
            if (owner != null) {
                LivingEntity ownerTarget = owner.getAttacking();
                if (ownerTarget != null && ownerTarget != this.wisp) {
                    this.wisp.setTarget(ownerTarget);
                }
            }
        }
        return this.wisp.getTarget() != null && this.wisp.getTarget() != this.wisp && super.canStart();
    }
}