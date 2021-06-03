package com.huawei.CpClient.slice;

import com.huawei.CpClient.DbUtils;
import com.huawei.CpClient.PeopleAdapter;
import com.huawei.CpClient.Person;
import com.huawei.CpClient.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    Button querypersonButton;
    PeopleAdapter peopleAdapter;
    List<Person> queryResult;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        initView();
    }

    private void initView() {
        ComponentContainer rootLayout = (ComponentContainer) LayoutScatter.getInstance(this)
                .parse(ResourceTable.Layout_ability_main, null, false);
        querypersonButton = (Button) rootLayout.findComponentById(ResourceTable.Id_query_button);
        querypersonButton.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                ResultSet resultSet = DbUtils.query(MainAbilitySlice.this);
                if(resultSet == null) {
                    return;
                }
                if(resultSet.getRowCount()==0) {
                    return;
                }
                resultSet.goToFirstRow();
                for(int i=0;i<resultSet.getRowCount();i++) {
                    if (resultSet.getRowCount() > 0) {
                        queryResult = new ArrayList<Person>();
                        Person person = new Person();
                        person.setName(resultSet.getString(resultSet.getColumnIndexForName("name")));
                        person.setLastName(resultSet.getString(resultSet.getColumnIndexForName("lastname")));
                        resultSet.goToNextRow();
                        queryResult.add(person);
                    }
                }
                peopleAdapter.setPeople(queryResult);
            }
        });
        ListContainer listContainer = (ListContainer) rootLayout.findComponentById(ResourceTable.Id_people_list);
        peopleAdapter = new PeopleAdapter(this);
        listContainer.setItemProvider(peopleAdapter);
        super.setUIContent(rootLayout);;
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
