package com.huawei.cpsubject;

import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.utils.net.Uri;

import java.util.ArrayList;

public class DbUtils {
    private static final Uri uri = Uri.parse("dataability:///com.huawei.cpsubject.DataAbility/test");
    public DbUtils() {
    }

    public static void insert(Context context, String name, String lastName) {
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context, uri);
        ValuesBucket valuesBucket = new ValuesBucket();
        valuesBucket.putString("name", name);
        valuesBucket.putString("lastname", lastName);
        try {
            dataAbilityHelper.insert(uri, valuesBucket);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(Context context) {
        ResultSet resultSet = null;
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context, uri);
        try {
            resultSet = dataAbilityHelper.query(uri, null, null);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
