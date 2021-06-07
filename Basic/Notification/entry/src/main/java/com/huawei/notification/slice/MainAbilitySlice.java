package com.huawei.notification.slice;

import com.huawei.notification.ResourceTable;
import com.huawei.notification.Utils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.event.intentagent.IntentAgent;
import ohos.event.intentagent.IntentAgentConstant;
import ohos.event.intentagent.IntentAgentHelper;
import ohos.event.intentagent.IntentAgentInfo;
import ohos.event.notification.NotificationHelper;
import ohos.event.notification.NotificationRequest;
import ohos.event.notification.NotificationSlot;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;
import ohos.hiviewdfx.HiLog;
import ohos.media.image.PixelMap;
import ohos.rpc.RemoteException;
import ohos.utils.system.SystemCapability;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainAbilitySlice extends AbilitySlice {
    Button normalContent,pictureContent,multiLineContent,normalContentLow;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initComponents();
    }

    public void initComponents() {
        normalContent = (Button)findComponentById(ResourceTable.Id_normal_content);
        pictureContent = (Button)findComponentById(ResourceTable.Id_picture_content);
        multiLineContent = (Button)findComponentById(ResourceTable.Id_multiline_content);
        normalContentLow = (Button)findComponentById(ResourceTable.Id_normal_low_content);
        createNotificationSlot();
        normalContent.setClickedListener(component -> createNormalNotification("notification_002"));
        pictureContent.setClickedListener(component -> createPictureNotification());
        multiLineContent.setClickedListener(component -> createMultiLineNotification());
        normalContentLow.setClickedListener(component -> createNormalNotification("notification_001"));
    }

    private void createNotificationSlot() {
        NotificationSlot notificationSlot = new NotificationSlot("notification_001", "low priority", NotificationSlot.LEVEL_LOW);
        notificationSlot.setEnableVibration(true);
        notificationSlot.setEnableLight(true);
        try {
            NotificationHelper.addNotificationSlot(notificationSlot);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        NotificationSlot notificationSlot1 = new NotificationSlot("notification_002", "high priority", NotificationSlot.LEVEL_HIGH);
        notificationSlot1.setEnableVibration(true);
        notificationSlot1.setEnableLight(true);
        try {
            NotificationHelper.addNotificationSlot(notificationSlot1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void createPictureNotification() {
        Optional<PixelMap> pixelMap;
        NotificationRequest notificationRequest = new NotificationRequest();
        NotificationRequest.NotificationPictureContent content = new NotificationRequest.NotificationPictureContent();
        content.setTitle("Picture notification");
        content.setText("Hi, picture here");
        pixelMap = Utils.getPixelMapByResId(this, ResourceTable.Media_Desert);
        content.setBigPicture(pixelMap.get());
        NotificationRequest.NotificationContent notificationContent = new NotificationRequest.NotificationContent(content);
        notificationRequest.setTapDismissed(true);
        notificationRequest.setContent(notificationContent);
        try {
            NotificationHelper.publishNotification(notificationRequest);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void createMultiLineNotification() {
        NotificationRequest notificationRequest = new NotificationRequest();
        NotificationRequest.NotificationMultiLineContent content = new NotificationRequest.NotificationMultiLineContent();
        content.setTitle("MultiLine notification");
        addLines(content);
        NotificationRequest.NotificationContent notificationContent = new NotificationRequest.NotificationContent(content);
        notificationRequest.setTapDismissed(true);
        notificationRequest.setContent(notificationContent);
        try {
            NotificationHelper.publishNotification(notificationRequest);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addLines (NotificationRequest.NotificationMultiLineContent content){
        String[] strArr=null;
        ResourceManager manager = this.getResourceManager();
        try {
            strArr = manager.getElement(ResourceTable.Strarray_defstr).getStringArray();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotExistException e) {
            e.printStackTrace();
        } catch (WrongTypeException e) {
            e.printStackTrace();
        }
        for(String s : strArr) {
            content.addSingleLine(s);
        }
    }

    private void createNormalNotification(String id) {
        NotificationRequest notificationRequest = new NotificationRequest();
        NotificationRequest.NotificationNormalContent content = new NotificationRequest.NotificationNormalContent();
        content.setTitle("Notification");
        content.setText("Hi there");
        NotificationRequest.NotificationContent notificationContent = new NotificationRequest.NotificationContent(content);
        notificationRequest.setTapDismissed(true);
        notificationRequest.setSlotId(id);
        notificationRequest.setContent(notificationContent);
        notificationRequest.setIntentAgent(getLaunchAction());
        try {
            NotificationHelper.publishNotification(notificationRequest);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private IntentAgent getLaunchAction() {
        Operation operation = new Intent.OperationBuilder()
                                .withDeviceId("")
                                .withBundleName(this.getBundleName())
                                .withAbilityName("com.huawei.notification.SecondAbility")
                .build();
        Intent intent = new Intent();
        intent.setOperation(operation);
        List<Intent> intentList = new ArrayList<>();
        intentList.add(intent);
        List<IntentAgentConstant.Flags> flags = new ArrayList<>();
        flags.add(IntentAgentConstant.Flags.UPDATE_PRESENT_FLAG);
        IntentAgentInfo paramsInfo = new IntentAgentInfo(90, IntentAgentConstant.OperationType.START_ABILITY, flags, intentList, null);
        return IntentAgentHelper.getIntentAgent(this, paramsInfo);
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
