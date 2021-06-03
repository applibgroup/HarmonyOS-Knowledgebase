package com.fractalwrench.harmonybootstrap.sample;

import com.fractalwrench.harmonybootstrap.sample.slice.AnimationSlice;
import com.fractalwrench.harmonybootstrap.sample.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(AnimationSlice.class.getName());
    }
}
