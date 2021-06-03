package com.fractalwrench.harmonybootstrap.sample.slice;

import com.fractalwrench.harmonybootstrap.sample.ResourceTable;
import com.fractalwrench.harmonybootstrap.sample.components.CustomComponent;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        DependentLayout rootLayout = (DependentLayout) findComponentById(ResourceTable.Id_layoutRoot);

        // Component creation via java code
        Button button = new Button(this);
        DependentLayout.LayoutConfig layoutConfig = new DependentLayout.LayoutConfig
                (200, 200);
        layoutConfig.addRule(DependentLayout.LayoutConfig.CENTER_IN_PARENT);
        button.setTextSize(50);
        button.setPadding(30, 30, 30, 30);
        button.setText("I'm an OHOS Buton component!");
        button.setBackground(new ShapeElement(this, ResourceTable.Graphic_background_ability_main));
        button.setLayoutConfig(layoutConfig);


        //adding to Container
        rootLayout.addComponent(button);
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
