package com.huawei.cpsubject.slice;

import com.huawei.cpsubject.*;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.data.rdb.RdbPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "MainAbilitySlice");
    private TextField mName;

    private TextField mLastName;

    private PeopleAdapter peopleAdapter;

    private Button addPersonButton;
    List<Person> queryResult;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        initView();
        setUpViews();
        showData();
    }

    private void initView() {
        ComponentContainer rootLayout = (ComponentContainer) LayoutScatter.getInstance(this)
                .parse(ResourceTable.Layout_ability_main, null, false);
        addPersonButton = (Button) rootLayout.findComponentById(ResourceTable.Id_add_button);
        mName = (TextField) rootLayout.findComponentById(ResourceTable.Id_name_text);
        mLastName = (TextField) rootLayout.findComponentById(ResourceTable.Id_lastName_text);
        ListContainer listContainer = (ListContainer) rootLayout.findComponentById(ResourceTable.Id_people_list);
        peopleAdapter = new PeopleAdapter(this);
        listContainer.setItemProvider(peopleAdapter);
        super.setUIContent(rootLayout);
    }

    public void setUpViews() {
        addPersonButton.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                DbUtils.insert(MainAbilitySlice.this, mName.getText(), mLastName.getText());
                showData();
            }
        });
    }

    private void showData() {
        queryData();
    }

    private void queryData() {
        ResultSet resultSet = DbUtils.query(MainAbilitySlice.this);
        queryResult = new ArrayList<Person>();
        if(resultSet == null) {
            HiLog.error(LABEL_LOG, "resultset empty");
            return;
        }
        if(resultSet.getRowCount()==0) {
            HiLog.error(LABEL_LOG, "row count 0");
            return;
        }
        if (resultSet.getRowCount() > 0) {
            while (resultSet.goToNextRow()) {
                    Person person = new Person();
                    person.setName(resultSet.getString(resultSet.getColumnIndexForName("name")));
                    person.setLastName(resultSet.getString(resultSet.getColumnIndexForName("lastname")));
                    queryResult.add(person);
                }
            }
        peopleAdapter.setPeople(queryResult);
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
