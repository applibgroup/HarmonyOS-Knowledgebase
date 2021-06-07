package com.huawei.myapplication.slice;

import com.huawei.myapplication.ResUtil;
import com.huawei.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.text.Font;
import ohos.agp.utils.Color;
import ohos.global.resource.RawFileEntry;
import ohos.global.resource.Resource;

import java.io.*;

public class MainAbilitySlice extends AbilitySlice {
    private static final String RAW_FILE_PATH="resources/rawfile/MorganChalk-L3aJy.ttf";
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        init();
    }

    public void init() {
        Text text =(Text) findComponentById(ResourceTable.Id_text_helloworld);
        Font font = getFont("MorganChalk-L3aJy.ttf");
        text.setText(ResUtil.getString(this, ResourceTable.String_fontName));
        text.setFont(font);
        text.setTextSize(100);
        text.setTextColor(new Color(ResUtil.getColor(this, ResourceTable.Color_color_1)));
        Image img =(Image) findComponentById(ResourceTable.Id_img);
        img.setPixelMap(ResUtil.getPixelMap(this, ResourceTable.Media_Desert).get());
    }

    private Font getFont(String fontfamily) {
        byte[] buffer = null;
        int bytesRead = 0;
        FileOutputStream fileOutputStream = null;
        File file = new File(this.getCacheDir(), fontfamily);
            RawFileEntry rawFileEntry = this.getResourceManager().getRawFileEntry(RAW_FILE_PATH);
        try {
            Resource resource = rawFileEntry.openRawFile();
            buffer = new byte[(int)rawFileEntry.openRawFileDescriptor().getFileSize()];
            bytesRead = resource.read(buffer);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(buffer, 0, bytesRead);
            fileOutputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return new Font.Builder(file).makeItalic(true).build();
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
