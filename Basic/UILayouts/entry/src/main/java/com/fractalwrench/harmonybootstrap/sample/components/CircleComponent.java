package com.fractalwrench.harmonybootstrap.sample.components;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.app.Context;

public class CircleComponent extends Component implements Component.DrawTask {
    private static final String BOOTSTRAP_CIRCLE_COLOR = "circleColor";
    private static final String BOOTSTRAP_CIRCLE_LABEL = "circleLabel";
    private static final String BOOTSTRAP_LABEL_COLOR = "labelColor";
    private static final String BOOTSTRAP_CIRCLE_RADIUS = "radius";

    private String circleText = "Hello, I'm Green Circle";
    private Color circleColor = Color.GREEN;
    private Color labelColor = Color.BLACK;
    private int radius = 100;
    private Paint circlePaint;

    public CircleComponent(Context context) {
        super(context);
        init(null);
    }

    public CircleComponent(Context context, AttrSet attrSet) {
        super(context, attrSet);
        init(attrSet);
    }

    public CircleComponent(Context context, AttrSet attrSet, String styleName) {
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
            this.radius = attrSet.getAttr(BOOTSTRAP_CIRCLE_RADIUS).isPresent() ? attrSet.getAttr(
                    BOOTSTRAP_CIRCLE_RADIUS).get().getIntegerValue() : 100;

        }
        addDrawTask(this::onDraw);
    }


    @Override
    public void onDraw(Component component, Canvas canvas) {
        //get half of the width and height as we are working with a circle
        int componentWidthHalf = getEstimatedWidth() / 4;
        int componentHeightHalf = getEstimatedHeight() / 4;

        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        /*int radius = 0;
        if (componentWidthHalf > componentHeightHalf)
            radius = componentWidthHalf;
        else
            radius = componentHeightHalf;*/

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
