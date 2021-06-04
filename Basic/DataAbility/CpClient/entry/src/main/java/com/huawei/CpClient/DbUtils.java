package com.huawei.CpClient;

import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.resultset.ResultSet;
import ohos.utils.net.Uri;

public class DbUtils {
    private static final Uri uri = Uri.parse("dataability:///com.huawei.cpsubject.DataAbility/test");

    public DbUtils() {
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
