package com.huawei.dialogs.slice;

import com.huawei.dialogs.ResourceTable;
import com.huawei.dialogs.custom.CustomDialog;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.element.ElementScatter;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.IDialog;
import ohos.agp.window.dialog.ListDialog;
import ohos.agp.window.dialog.ToastDialog;


import java.util.ArrayList;
import java.util.List;

import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_CONTENT;

public class MainAbilitySlice extends AbilitySlice {

    public static final float DIALOG_BOX_CORNER_RADIUS = 36.0f;
    public static final int DIALOG_BOX_WIDTH = 984;
    private Button commonDialogButton;
    private Button listDialogButton;
    private Button customDialogButton;
    private Button toastDialog;
    private Button listMultiSelectButton;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initComponents();
    }
    public void initComponents() {
        commonDialogButton = (Button)findComponentById(ResourceTable.Id_common_dialog);
        listDialogButton = (Button)findComponentById(ResourceTable.Id_list_dialog);
        customDialogButton = (Button)findComponentById(ResourceTable.Id_custom_dialog);
        toastDialog = (Button)findComponentById(ResourceTable.Id_toast_dialog);
        listMultiSelectButton = (Button) findComponentById(ResourceTable.Id_list_multiselect_dialog);
        commonDialogButton.setClickedListener(component -> showCommonDialog());
        listDialogButton.setClickedListener(component -> showListDialog());
        customDialogButton.setClickedListener(component -> showCustomDialog());
        toastDialog.setClickedListener(component -> showToastDialog());
        listMultiSelectButton.setClickedListener(component -> showListMultiSelectDialog());
    }

    private void showListMultiSelectDialog() {
    String[] items = new String[]{"item1", "item2", "item3", "item4"};
    boolean[] selected = new boolean[]{false, false, false, false};
    List<String> selectedItems = new ArrayList<>();
    ListDialog listDialog = new ListDialog(this);
    listDialog.setTitleText("List MultiSelect dialog");
    listDialog.setAlignment(TextAlignment.CENTER);
    listDialog.setMultiSelectItems(items, selected);
    listDialog.setAutoClosable(true);
    listDialog.setSize(DIALOG_BOX_WIDTH, MATCH_CONTENT);
    listDialog.setOnMultiSelectListener((iDialog, i, b) -> multiSelect(items[i], selectedItems, listDialog.getItemComponent(i)));
    listDialog.show();
    }

    private void multiSelect(String item, List<String> selectedItems, Component itemComponent) {
        if(selectedItems.contains(item)) {
            selectedItems.remove(item);
            itemComponent.setBackground(ElementScatter.getInstance(this).parse(ResourceTable.Graphic_button_unselected_background));
        } else {
          selectedItems.add(item);
          itemComponent.setBackground(ElementScatter.getInstance(this).parse(ResourceTable.Graphic_button_selected_background));
        }
    }

    private void showCustomDialog() {
        CustomDialog customDialog = new CustomDialog(this);
        customDialog.setAutoClosable(true);
        customDialog.show();
    }

    private void showCommonDialog() {
        CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setTitleText("Common Dialog");
        commonDialog.setContentText("This is a common dialog");
        commonDialog.setAutoClosable(true);
        commonDialog.setSize(DIALOG_BOX_WIDTH, MATCH_CONTENT);
        commonDialog.setButton(IDialog.BUTTON1, "Yes", (iDialog, i) -> {
            showToast("Clicked Yes button");
            iDialog.destroy();
        });
        commonDialog.setButton(IDialog.BUTTON2, "No", (iDialog, i) -> {
            showToast("Clicked No button");
            iDialog.destroy();
        });
        commonDialog.setButton(IDialog.BUTTON3, "Maybe", (iDialog, i) -> {
            showToast("Clicked Maybe button");
            iDialog.destroy();
        });
        commonDialog.show();
    }

    private void showToastDialog() {
        Component toastComponent = LayoutScatter.getInstance(this).parse(ResourceTable.Layout_toast_ui, null, false);
        new ToastDialog(this).setDuration(500).setComponent(toastComponent).show();
    }

    private void showListDialog() {
        String[] list = new String[]{"item1", "item2", "item 3"};
        ListDialog listDialog = new ListDialog(this);
        listDialog.setTitleText("List Dialog");
        listDialog.setItems(list);
        listDialog.setAutoClosable(true);
        listDialog.setOnSingleSelectListener(((iDialog, i) -> {
            showToast(list[i]);
        }));
        listDialog.show();
    }

    private void showToast(String msg) {
        new ToastDialog(this).setText(msg).setDuration(500).show();
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
