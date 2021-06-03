package com.fractalwrench.harmonybootstrap.sample.components;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.Rect;
import ohos.agp.utils.TextAlignment;
import ohos.app.Context;

public class CustomComponent extends Component implements Component.DrawTask, Component.EstimateSizeListener {

    private static final String BOOTSTRAP_CIRCLE_COLOR = "circleColor";
    private static final String BOOTSTRAP_CIRCLE_LABEL = "circleLabel";
    private static final String BOOTSTRAP_LABEL_COLOR = "labelColor";

    private String circleText = "Hello, I'm Green Circle";
    private Color circleColor = Color.GREEN;
    private Color labelColor = Color.BLACK;
    private Paint circlePaint;

    public CustomComponent(Context context) {
        super(context);
        init(null);
    }

    public CustomComponent(Context context, AttrSet attrSet) {
        super(context, attrSet);
        init(attrSet);
    }

    public CustomComponent(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        init(attrSet);
    }

    private void init(AttrSet attrSet) {
        circlePaint = new Paint();
        if (attrSet != null) {
            this.circleText = attrSet.getAttr(BOOTSTRAP_CIRCLE_LABEL).isPresent() ? attrSet.getAttr(
                    BOOTSTRAP_CIRCLE_LABEL).get().getStringValue() : "Hello, I'm Green Circle";
            this.circleColor = attrSet.getAttr(BOOTSTRAP_CIRCLE_COLOR).isPresent() ? attrSet.getAttr(
                    BOOTSTRAP_CIRCLE_COLOR).get().getColorValue() : Color.GREEN;
            this.labelColor = attrSet.getAttr(BOOTSTRAP_LABEL_COLOR).isPresent() ? attrSet.getAttr(
                    BOOTSTRAP_LABEL_COLOR).get().getColorValue() : Color.BLACK;

        }
        setEstimateSizeListener(this::onEstimateSize);
        addDrawTask(this::onDraw);
    }

    @Override
    public boolean onEstimateSize(int widthEstimateConfig, int heightEstimateConfig) {
        //Do Size Estimation here and don't forgot to call setEstimatedSize(width, height)
        int width = Component.EstimateSpec.getSize(widthEstimateConfig);
        int height = Component.EstimateSpec.getSize(heightEstimateConfig);
        setEstimatedSize(
                Component.EstimateSpec.getChildSizeWithMode(width, width, Component.EstimateSpec.NOT_EXCEED),
                Component.EstimateSpec.getChildSizeWithMode(height, height, Component.EstimateSpec.NOT_EXCEED));

        //onLayout
        Rect currentPosition = getComponentPosition();
        int newLeft = currentPosition.right - getEstimatedWidth();
        int newTop = currentPosition.bottom - getEstimatedHeight();
        onLayout(newLeft, newTop, currentPosition.right, currentPosition.bottom);

        // Return true if you have manually estimated size
        return true;
    }

    protected void onLayout(int l, int t, int r, int b) {
        //Laying out child in case of Component Container
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        //get half of the width and height as we are working with a circle
        int componentWidthHalf = getEstimatedWidth() / 4;
        int componentHeightHalf = getEstimatedHeight() / 4;

        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        int radius = 0;
        if (componentWidthHalf > componentHeightHalf)
            radius = componentWidthHalf;
        else
            radius = componentHeightHalf;

        //set circle properties
        circlePaint.setStyle(Paint.Style.FILL_STYLE);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);

        //draw the circle
        canvas.drawCircle(getEstimatedWidth() / 2, getEstimatedHeight() / 2, radius, circlePaint);

        //set the text color using the color specified
        circlePaint.setColor(labelColor);

        //set text properties
        circlePaint.setTextAlign(TextAlignment.CENTER);
        circlePaint.setTextSize(50);

        //draw the text using the string attribute and chosen properties
        canvas.drawText(circlePaint, circleText, getEstimatedWidth() / 2, getEstimatedHeight() / 2);
    }

}
