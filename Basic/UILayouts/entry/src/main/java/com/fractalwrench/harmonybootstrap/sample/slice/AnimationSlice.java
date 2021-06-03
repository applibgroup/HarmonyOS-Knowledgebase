package com.fractalwrench.harmonybootstrap.sample.slice;

import com.fractalwrench.harmonybootstrap.sample.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorGroup;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.element.FrameAnimationElement;

public class AnimationSlice extends AbilitySlice implements Component.ClickedListener {
    private Button targetButton1;
    private Button targetButton2;
    private Button targetButton3;
    private Image imageComponent;
    private AnimatorValue button1Animation;
    private AnimatorProperty button2Animation;
    private AnimatorGroup button3Animation;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_anim);
        targetButton1 = (Button) findComponentById(ResourceTable.Id_target_button1);
        targetButton1.setClickedListener(this::onClick);
        targetButton2 = (Button) findComponentById(ResourceTable.Id_target_button2);
        targetButton2.setClickedListener(this::onClick);
        targetButton3 = (Button) findComponentById(ResourceTable.Id_target_button3);
        targetButton3.setClickedListener(this::onClick);
        imageComponent = (Image) findComponentById(ResourceTable.Id_image_component);

        //AnimatorValue Example
        button1Animation = new AnimatorValue();
        button1Animation.setDuration(2000);
        button1Animation.setLoopedCount(2);
        button1Animation.setCurveType(Animator.CurveType.BOUNCE);

        button1Animation.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float value) {
                //animating button position
                targetButton1.setContentPosition((int) (500 * value), targetButton1.getContentPositionY());

                //animating width and height
                //targetButton1.setWidth((int) (500*value));
                //targetButton1.setHeight((int) (500*value));

                //animating alpha property
                //targetButton1.setAlpha(value);
            }
        });

        //AnimatorProperty Example
        button2Animation = targetButton2.createAnimatorProperty();  //0r button2Animation.setTarget(targetButton2)
        button2Animation.setDuration(2000).moveFromX(50).moveToX(1000).rotate(90).alpha(0).setLoopedCount(5);

        //AnimatorGroup Example
        button3Animation = new AnimatorGroup();
        button3Animation.runSerially(
                targetButton1.createAnimatorProperty().
                        moveFromY(targetButton1.getContentPositionY()).moveToY(1000),
                targetButton2.createAnimatorProperty().
                        moveFromY(targetButton2.getContentPositionY()).moveToY(1000),
                targetButton3.createAnimatorProperty().
                        moveFromY(targetButton3.getContentPositionY()).moveToY(1000)

        );
        button3Animation.setDuration(2000);
        button3Animation.setLoopedCount(1);
        button3Animation.setCurveType(Animator.CurveType.BOUNCE);

        //FrameAnimationElement example
        FrameAnimationElement frameAnimationElement =
                new FrameAnimationElement(getContext(), ResourceTable.Graphic_animation_element);
        imageComponent.setBackground(frameAnimationElement);
        //frameAnimationElement.start();

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()){
            case ResourceTable.Id_target_button1:
                button1Animation.start();
                break;
            case ResourceTable.Id_target_button2:
                button2Animation.start();
                break;
            case ResourceTable.Id_target_button3:
                button3Animation.start();
                break;
        }
    }
}