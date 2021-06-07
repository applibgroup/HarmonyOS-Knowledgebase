package com.huawei.dialogs.custom;

import com.huawei.dialogs.ResourceTable;
import com.huawei.dialogs.slice.MainAbilitySlice;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.LayoutScatter;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

import static com.huawei.dialogs.slice.MainAbilitySlice.DIALOG_BOX_CORNER_RADIUS;
import static com.huawei.dialogs.slice.MainAbilitySlice.DIALOG_BOX_WIDTH;
import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_CONTENT;


public class CustomDialog extends CommonDialog {
    Component component;
    Context context;
    public Button button1, button2;
    public CustomDialog(Context context) {
        super(context);
        this.context = context;
        initComponents();
        setSize(DIALOG_BOX_WIDTH, MATCH_CONTENT);
        setCornerRadius(DIALOG_BOX_CORNER_RADIUS);
    }

    public void initComponents() {
        component = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_custom_ability, null,true);
        button1 = (Button) component.findComponentById(ResourceTable.Id_btn_yes);
        button2 = (Button) component.findComponentById(ResourceTable.Id_btn_no);
        button1.setClickedListener(component1 -> click("Yes"));
        button2.setClickedListener(component1 -> click("No"));
        super.setContentCustomComponent(component);
    }

    public void click(String str) {
        if (str.equals("Yes")) {
            this.destroy();
        } else {
            new ToastDialog(context).setText("Clicked No").setDuration(500).show();
            this.destroy();
        }
    }
}
