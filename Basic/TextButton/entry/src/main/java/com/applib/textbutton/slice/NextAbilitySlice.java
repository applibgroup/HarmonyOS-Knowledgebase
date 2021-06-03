package com.applib.textbutton.slice;

import com.applib.textbutton.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;


public class NextAbilitySlice extends AbilitySlice {
  @Override
  protected void onStart(Intent intent) {
    super.onStart(intent);
    super.setUIContent(ResourceTable.Layout_ability_next);
  }
}
