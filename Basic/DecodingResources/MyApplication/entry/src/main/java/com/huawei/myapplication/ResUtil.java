package com.huawei.myapplication;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.PixelMapElement;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.VectorElement;
import ohos.agp.render.Arc;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.Rect;
import ohos.agp.utils.TextAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.global.resource.*;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Size;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;


public class ResUtil {
    private static final String TAG = "ResUtil";

    private static final String CITY_ID_ATTR = "cityId_";

    private static final String STRING_ID_ATTR = "String_";

    private static Map<Integer, PixelMap> imageCache = new HashMap();


    private ResUtil() {
    }

    /**
     * get the path from id
     *
     * @param context the context
     * @param id the id
     * @return the path from id
     */
    public static String getPathById(Context context, int id) {
        String path = "";
        if (context == null) {
            LogUtil.error(TAG, "getPathById -> get null context");
            return path;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getPathById -> get null ResourceManager");
            return path;
        }
        try {
            path = manager.getMediaPath(id);
        } catch (IOException e) {
            LogUtil.error(TAG, "getPathById -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getPathById -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getPathById -> WrongTypeException");
        }
        return path;
    }

    /**
     * get the new color
     *
     * @param context the context
     * @param id the id
     * @return the color
     */
    public static Color getNewColor(Context context, int id) {
        Color result = new Color(getColor(context, id));
        return result;
    }

    /**
     * get the color
     *
     * @param context the context
     * @param id the id
     * @return the color
     */
    public static int getColor(Context context, int id) {
        int result = 0;
        if (context == null) {
            LogUtil.error(TAG, "getColor -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getColor -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getColor();
        } catch (IOException e) {
            LogUtil.error(TAG, "getColor -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getColor -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getColor -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the dimen value
     *
     * @param context the context
     * @param id the id
     * @return get the float dimen value
     */
    public static float getDimen(Context context, int id) {
        float result = 0;
        if (context == null) {
            LogUtil.error(TAG, "getDimen -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getDimen -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getFloat();
        } catch (IOException e) {
            LogUtil.error(TAG, "getDimen -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getDimen -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getDimen -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the boolean value
     *
     * @param context the context
     * @param id the id
     * @return get the boolean dimen value
     */
    public static boolean getBoolean(Context context, int id) {
        boolean result = false;
        if (context == null) {
            LogUtil.error(TAG, "getBoolean -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getBoolean -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getBoolean();
        } catch (IOException e) {
            LogUtil.error(TAG, "getBoolean -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getBoolean -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getBoolean -> WrongTypeException");
        }
        return result;
    }


    /**
     * get string
     *
     * @param context the context
     * @param id the string id
     * @return string of the given id
     */
    public static String getString(Context context, int id) {
        String result = "";
        if (context == null) {
            LogUtil.error(TAG, "getString -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getString -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getString();
        } catch (IOException e) {
            LogUtil.error(TAG, "getString -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getString -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getString -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the string array
     *
     * @param context the context
     * @param id the string array id
     * @return the string array
     */
    public static String[] getStringArray(Context context, int id) {
        String[] result = null;
        if (context == null) {
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            return result;
        }
        try {
            result = manager.getElement(id).getStringArray();
        } catch (IOException e) {
        } catch (NotExistException e) {
        } catch (WrongTypeException e) {
        }
        return result;
    }

    /**
     * get the int
     *
     * @param context the context
     * @param id the int array
     * @return the int array
     */
    public static int getInt(Context context, int id) {
        int result = 0;
        if (context == null) {
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            return result;
        }
        try {
            result = manager.getElement(id).getInteger();
        } catch (IOException e) {
        } catch (NotExistException e) {
        } catch (WrongTypeException e) {
        }
        return result;
    }

    /**
     * get the int array
     *
     * @param context the context
     * @param id the int array
     * @return the int array
     */
    public static int[] getIntArray(Context context, int id) {
        int[] result = null;
        if (context == null) {
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            return result;
        }
        try {
            result = manager.getElement(id).getIntArray();
        } catch (IOException e) {
        } catch (NotExistException e) {
        } catch (WrongTypeException e) {
        }
        return result;
    }

    /**
     * get the vector drawable
     *
     * @param context the context
     * @param id the drawable id
     * @return the vector drawable
     */
    public static VectorElement getVectorDrawable(Context context, int id) {
        if (context == null) {
            return null;
        }
        return new VectorElement(context, id);
    }

    /**
     * get the pixel map
     *
     * @param context the context
     * @param id the id
     * @return the pixel map
     */
    public static Optional<PixelMap> getPixelMap(Context context, int id) {
        String path = getPathById(context, id);
        if (LogUtil.TextUtils.isEmpty(path)) {
            return Optional.empty();
        }
        RawFileEntry assetManager = context.getResourceManager().getRawFileEntry(path);
        ImageSource.SourceOptions options = new ImageSource.SourceOptions();
        options.formatHint = "image/png";
        ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
        try {
            Resource asset = assetManager.openRawFile();
            ImageSource source = ImageSource.create(asset, options);
            return Optional.ofNullable(source.createPixelmap(decodingOptions));
        } catch (IOException e) {
        }
        return Optional.empty();
    }

    /**
     * get the Pixel Map Element
     *
     * @param context the context
     * @param resId the res id
     * @return the Pixel Map Element
     */
    public static PixelMapElement getPixelMapDrawable(Context context, int resId) {
        Optional<PixelMap> optional = getPixelMap(context, resId);
        return optional.map(PixelMapElement::new).orElse(null);
    }

    /**
     * get the Element
     *
     * @param color the color
     * @return the Element
     */
    public static Element buildDrawableByColor(int color) {
        ShapeElement drawable = new ShapeElement();
        drawable.setShape(ShapeElement.RECTANGLE);
        drawable.setRgbColor(RgbColor.fromArgbInt(color));
        return drawable;
    }

    /**
     * get the Element By ColorRadius
     *
     * @param color the color
     * @param radius the radius
     * @return the Element By ColorRadius
     */
    public static Element buildDrawableByColorRadius(int color, float radius) {
        ShapeElement drawable = new ShapeElement();
        drawable.setShape(ShapeElement.RECTANGLE);
        drawable.setRgbColor(RgbColor.fromArgbInt(color));
        drawable.setCornerRadius(radius);
        return drawable;
    }

    /**
     * get the ShapeElement
     *
     * @param thickness the thickness
     * @param inside the inside color
     * @param border the border color
     * @param startAngle the start angle
     * @param sweepAngle the sweep angle
     * @return the ShapeElement
     */
    public static ShapeElement getCustomArcGradientDrawable(int thickness, Color inside, Color border, float startAngle,
                                                            float sweepAngle) {
        ShapeElement drawable = new ShapeElement();
        drawable.setShape(ShapeElement.ARC);
        drawable.setRgbColor(RgbColor.fromArgbInt(inside.getValue())); // keep it transparent for main(inner) part
        drawable.setArc(new Arc(startAngle, sweepAngle, false));
        drawable.setStroke(thickness, RgbColor.fromArgbInt(border.getValue()));
        return drawable;
    }

    /**
     * get the Element
     * @param strokeWidth thickness
     * @param inside the inside color
     * @param border the border color
     * @param rect the rect
     * @return the Element
     */
    public static Element getCustomCircleGradientDrawable(int strokeWidth, Color inside, Color border, Rect rect){
        ShapeElement element = new ShapeElement();
        element.setShape(ShapeElement.OVAL);
        element.setRgbColor(RgbColor.fromArgbInt(inside.getValue()));
        element.setStroke(strokeWidth, RgbColor.fromArgbInt(border.getValue()));
        element.setBounds(rect);
        return element;
    }

    /**
     * get the Element
     * @param inside the inside color
     * @param rect the inside rect
     * @return the Element
     */
    public static Element getCustomRectGradientDrawable(Color inside, Rect rect){
        ShapeElement element = new ShapeElement();
        element.setShape(ShapeElement.RECTANGLE);
        element.setRgbColor(RgbColor.fromArgbInt(inside.getValue()));
        element.setBounds(rect);
        return element;
    }

    public static void createTabIcon(AbilitySlice abilitySlice, TabList.Tab tab, int i) {
        if (tab == null) {
            return;
        }
        try {
            PixelMap pixelMap = createByResourceId(abilitySlice, i, "image/png");
            PixelMapElement pixelMapElement = new PixelMapElement(pixelMap);
            pixelMapElement.setBounds(0, 0, 50, 50);
            tab.setAroundElements(pixelMapElement, null, null, null);
            tab.setPadding(30,0,0,0);
        } catch (NotExistException | IOException e) {
        }
    }

    public static void createIcons(AbilitySlice abilitySlice, TabList.Tab tab, int i) {
        if (tab == null) {
            return;
        }
        try {
            PixelMap pixelMap = createByResourceId(abilitySlice, i, "image/png");
            PixelMapElement pixelMapElement = new PixelMapElement(pixelMap);
            pixelMapElement.setBounds(0, 0, 70, 70);
            tab.setIconElement(pixelMapElement);
            tab.setPadding(5,5,5,5);
        } catch (NotExistException | IOException e) {
        }
    }

    public static PixelMap createByResourceId(AbilitySlice abilitySlice, int i, String str) throws IOException, NotExistException {
        if (abilitySlice == null) {
            throw new IOException();
        } else if (imageCache.containsKey(Integer.valueOf(i))) {
            return imageCache.get(Integer.valueOf(i));
        } else {
            ResourceManager resourceManager = abilitySlice.getResourceManager();
            if (resourceManager != null) {
                Resource resource = resourceManager.getResource(i);
                if (resource != null) {
                    ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
                    sourceOptions.formatHint = str;
                    ImageSource create = ImageSource.create(readResource(resource), sourceOptions);
                    resource.close();
                    if (create != null) {
                        ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
                        decodingOptions.desiredSize = new Size(0, 0);
                        decodingOptions.desiredRegion = new ohos.media.image.common.Rect(0, 0, 0, 0);
                        decodingOptions.desiredPixelFormat = PixelFormat.ARGB_8888;
                        PixelMap pixelMap = create.createPixelmap(decodingOptions);
                        imageCache.put(Integer.valueOf(i), pixelMap);
                        return pixelMap;
                    }
                    throw new FileNotFoundException();
                }
                throw new IOException();
            }
            throw new IOException();
        }
    }

    private static byte[] readResource(Resource resource) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = resource.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e) {
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void showToast(Context abilityContext, String str, int duration) {
        Text text = new Text(abilityContext);
        text.setWidth(ComponentContainer.LayoutConfig.MATCH_PARENT);
        text.setHeight(ComponentContainer.LayoutConfig.MATCH_CONTENT);
        text.setTextSize(48);
        text.setText(str);
        text.setMultipleLine(true);
        text.setTextAlignment(TextAlignment.CENTER);
        ShapeElement shapeElement = (ShapeElement) buildDrawableByColor(Color.WHITE.getValue());
        text.setBackground(shapeElement);
        DirectionalLayout directionalLayout = new DirectionalLayout(abilityContext);
        directionalLayout.setBackground(shapeElement);
        DirectionalLayout.LayoutConfig layoutConfig = new DirectionalLayout.LayoutConfig
                (DirectionalLayout.LayoutConfig.MATCH_PARENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT);
        layoutConfig.setMarginBottom(100);
        directionalLayout.setLayoutConfig(layoutConfig);
        directionalLayout.setPadding(20, 30, 20, 30);
        directionalLayout.addComponent(text);
        ToastDialog toastDialog = new ToastDialog(abilityContext);
        toastDialog.setComponent(directionalLayout).setDuration(duration).setAutoClosable(true)
                .setAlignment(LayoutAlignment.BOTTOM).setTransparent(true).show();
    }

    /**
     * get res id by reflect
     *
     * @param resClass res class
     * @param resName res name
     * @return res id
     */
    public static OptionalInt getResIdByReflect(Class resClass, String resName) { return null;}


    /**
     * get native city name
     *
     * @param context the context
     * @param cityId cityId
     * @return city name from cityId
     */
    public static String getNativeCityName(Context context, String cityId) { return null;}

    /**
     * find view by id
     *
     * @param view rootView
     * @param id res id
     * @param <T> type
     * @return view
     */
    public static <T extends Component> T findViewById(Component view, int id) {
        if (view == null) {
            return null;
        }
        return (T) view.findComponentById(id);
    }
}

