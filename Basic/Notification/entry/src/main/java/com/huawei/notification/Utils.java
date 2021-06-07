package com.huawei.notification;

import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.global.resource.ResourceManager;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Rect;
import ohos.media.image.common.Size;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

public class Utils {

    private static final int HILOG_TYPE = 3;

    private static final int HILOG_DOMAIN = 0xD000F00;

    private static final HiLogLabel LABEL = new HiLogLabel(HILOG_TYPE, HILOG_DOMAIN, "[Utils]");

    private Utils() {
    }

    public static Optional<PixelMap> getPixelMapByResId(Context context, int resourceId) {
        ResourceManager resourceManager = context.getResourceManager();
        Optional<PixelMap> pixelMap = Optional.empty();
        if (resourceManager == null) {
            return Optional.empty();
        }
        try {
            Resource resource = resourceManager.getResource(resourceId);
            if (resource == null) {
                return Optional.empty();
            }
            pixelMap = preparePixelmap(resource);
        } catch (IOException | NotExistException e) {
            e.printStackTrace();
        }
        return Optional.of(pixelMap.get());
    }

    public static Optional<PixelMap> preparePixelmap(Resource resource) throws IOException {
        byte[] bytes = null;
        try {
            if (resource != null) {
                bytes = readBytes(resource);
                resource.close();
            }
        } catch (IOException e) {
            HiLog.error(LABEL, "get pixelmap failed, read resource bytes failed");
            return Optional.empty();
        }
        if (bytes == null) {
            HiLog.error(LABEL, "get pixelmap failed, read resource bytes is null");
            return Optional.empty();
        }
        ImageSource.SourceOptions srcOpts = new ImageSource.SourceOptions();
        ImageSource imageSource = ImageSource.create(bytes, srcOpts);
        if (imageSource == null) {
            HiLog.error(LABEL, "get pixelmap failed, image source is null");
        }
        ImageSource.DecodingOptions decodingOpts = new ImageSource.DecodingOptions();
        decodingOpts.desiredSize = new Size(0, 0);
        decodingOpts.desiredRegion = new Rect(0, 0, 0, 0);
        decodingOpts.desiredPixelFormat = PixelFormat.ARGB_8888;
        PixelMap decodePixelMap = null;
        if (imageSource != null) {
            decodePixelMap = imageSource.createPixelmap(decodingOpts);
        }
        return Optional.ofNullable(decodePixelMap);
    }

    private static byte[] readBytes(Resource resource) {
        final int bufferSize = 1024;
        final int ioEnd = -1;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes = new byte[bufferSize];
        byte[] bytesArray = new byte[0];
        while (true) {
            try {
                int readLen = resource.read(bytes, 0, bufferSize);
                if (readLen == ioEnd) {
                    bytesArray = output.toByteArray();
                    break;
                }
                output.write(bytes, 0, readLen);
            } catch (IOException e) {
                break;
            } finally {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
        }
        return bytesArray;
    }

}
