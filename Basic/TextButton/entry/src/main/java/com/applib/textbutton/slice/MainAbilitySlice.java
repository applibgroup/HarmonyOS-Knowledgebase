package com.applib.textbutton.slice;

import com.applib.textbutton.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        Button bt = (Button)findComponentById(ResourceTable.Id_button_next);
//        bt.setClickedListener(new Component.ClickedListener() {
//            @Override
//            public void onClick(Component component) {
//                present(new NextAbilitySlice(), new Intent());
//            }
//        });
        bt.setClickedListener(list -> present(new NextAbilitySlice(), new Intent()));
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
