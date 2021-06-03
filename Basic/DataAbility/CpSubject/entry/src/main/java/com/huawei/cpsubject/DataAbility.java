package com.huawei.cpsubject;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.data.rdb.RawRdbPredicates;
import ohos.data.rdb.RdbStore;
import ohos.data.resultset.ResultSet;
import ohos.data.rdb.ValuesBucket;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;
import ohos.utils.PacMap;

import java.io.FileDescriptor;

public class DataAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "DataAbility");
    RdbStore rdbStore;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.info(LABEL_LOG, "DataAbility onStart");
        DbHelper dbHelper = new DbHelper(this);
        rdbStore = dbHelper.initRdb();
    }

    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {
        return rdbStore.query(new RawRdbPredicates("test"), columns);
    }

    @Override
    public int insert(Uri uri, ValuesBucket value) {
        HiLog.info(LABEL_LOG, "DataAbility insert");
        long res = rdbStore.insert("test",value);
        return Math.toIntExact(res);
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {
        //add custom impl.
        return 0;
    }

    @Override
    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {
        //add custom impl.
        return 0;
    }

    @Override
    public FileDescriptor openFile(Uri uri, String mode) {
        //add custom impl.
        return null;
    }

    @Override
    public String[] getFileTypes(Uri uri, String mimeTypeFilter) {
        //add custom impl.
        return new String[0];
    }

    @Override
    public PacMap call(String method, String arg, PacMap extras) {
        //add custom impl.
        return null;
    }

    @Override
    public String getType(Uri uri) {
        //add custom impl.
        return null;
    }
}