package com.example.test.testproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ring,//光环外圈
            light,//移动光线
            ufo,//UFO
            building,//建筑
            bicycle,//自行车
            tree_one,//树（左边）
            tree_two,//树（右边）
            sun;//太阳




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ring = findViewById(R.id.ring);
        light = findViewById(R.id.light);
        ufo = findViewById(R.id.ufo);
        building = findViewById(R.id.building);
        bicycle = findViewById(R.id.bicycle);
        tree_one = findViewById(R.id.tree_one);
        tree_two = findViewById(R.id.tree_two);
        sun = findViewById(R.id.sun);

        Button btnPlay = findViewById(R.id.btn_play);

        initViewAnim();

        initValueAnim();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ufo.setAlpha(1f);
//                ufo.startAnimation(ufoFlyInAnimSet);
//                sun.startAnimation(sunRotateAnim);

                ufo.setAlpha(1f);
                bicycle.setAlpha(1f);
                animatorSet.start();
            }
        });

    }

    AnimatorSet animatorSet;

    /**
     * 初始化属性动画
     */
    private void initValueAnim() {
        //UFO动画
        float ufoWidth = dipToPx(this, 57);
        PropertyValuesHolder ufoTranslationX = PropertyValuesHolder.ofFloat(
                "translationX",
                -ufoWidth * 4.5f, ufoWidth * 0.5f, 0f,//UFO飞来
                0f,                                           //放射光环放大
                0f, 0f, 0f, 0f,                              //吸取物体
                0f,                                         //放射光环缩小
                ufoWidth * 0.2f,                           //UFO倾斜
                ufoWidth * 5f);                           //UFO飞走
        PropertyValuesHolder ufoRotation = PropertyValuesHolder.ofFloat("rotation",
                15f, 15f, 0f,  //UFO飞来
                0f,                    //放射光环放大
                0f, 0f, 0f, 0f,       //吸取物体
                0f,                  //放射光环缩小
                15f,                //UFO倾斜
                15f);              //UFO飞走
        ObjectAnimator ufoAnim = ObjectAnimator.ofPropertyValuesHolder(ufo, ufoTranslationX, ufoRotation);
        ufoAnim.setRepeatMode(ValueAnimator.RESTART);
        ufoAnim.setRepeatCount(10000);
        ufoAnim.setDuration(4000);


        //光环动画
        PropertyValuesHolder ringScale = PropertyValuesHolder.ofFloat("scaleX",
                0f, 0f, 0f,  //UFO飞来
                1f,                  //放射光环放大
                1f, 1f, 1f, 1f,     //吸取物体
                0f,                //放射光环缩小
                0f,               //UFO倾斜
                0f);             //UFO飞走
        PropertyValuesHolder ringAlpha = PropertyValuesHolder.ofFloat("alpha",
                0f, 0f, 0f,  //UFO飞来
                1f,                           //放射光环放大
                1f, 1f, 1f, 1f,              //吸取物体
                0f,                         //放射光环缩小
                0f,                        //UFO倾斜
                0f);                      //UFO飞走
        ObjectAnimator ringAnim = ObjectAnimator.ofPropertyValuesHolder(ring, ringScale, ringAlpha);
        ringAnim.setRepeatMode(ValueAnimator.RESTART);
        ringAnim.setRepeatCount(10000);
        ringAnim.setDuration(4000);


        //光线动画
        float lightHeight = dipToPx(this, 139);
        PropertyValuesHolder lightTranslationY = PropertyValuesHolder.ofFloat("translationY",
                0f, 0f, 0f,                                                            //UFO飞来
                0f,                                                                             //放射光环放大
                -lightHeight * 0.5f, -lightHeight * 1f, -lightHeight * 1.5f, -lightHeight * 2f,//吸取物体
                0f,                                                                           //放射光环缩小
                0f,                                                                          //UFO倾斜
                0f);                                                                        //UFO飞走
        PropertyValuesHolder lightAlpha = PropertyValuesHolder.ofFloat("alpha",
                0f, 0f, 0f,     //UFO飞来
                1f,                     //放射光环放大
                1f, 1f, 0.8f, 0f,      //吸取物体
                0f,                   //放射光环缩小
                0f,                  //UFO倾斜
                0f);                //UFO飞走
        ObjectAnimator lightAnim = ObjectAnimator.ofPropertyValuesHolder(light, lightTranslationY, lightAlpha);
        lightAnim.setRepeatMode(ValueAnimator.RESTART);
        lightAnim.setRepeatCount(10000);
        lightAnim.setDuration(4000);

        //自行车动画
        float bicycleHeight = dipToPx(this, 15);
        PropertyValuesHolder bicycleRotation = PropertyValuesHolder.ofFloat("rotation",
                0f, 0f, 0f,  //UFO飞来
                0f,                  //放射光环放大
                -20, -20, -20, -20, //吸取物体
                0f,                //放射光环缩小
                0f,               //UFO倾斜
                0f);             //UFO飞走
        PropertyValuesHolder bicycleTranslationY = PropertyValuesHolder.ofFloat("translationY",
                0f, 0f, 0f,                                                                         //UFO飞来
                0f,                                                                                         //放射光环放大
                -bicycleHeight * 1.25f, -bicycleHeight * 2.5f, -bicycleHeight * 3.75f, -bicycleHeight * 5f,//吸取物体
                0f,                                                                                       //放射光环缩小
                0f,                                                                                      //UFO倾斜
                0f);                                                                                    //UFO飞走
        PropertyValuesHolder bicycleAlpha = PropertyValuesHolder.ofFloat("alpha",
                1f, 1f, 1f,  //UFO飞来
                1f,                  //放射光环放大
                1f, 0.6f, 0.1f, 0f, //吸取物体
                0f,                //放射光环缩小
                0f,               //UFO倾斜
                0f);             //UFO飞走
        ObjectAnimator bicycleAnim = ObjectAnimator.ofPropertyValuesHolder(bicycle, bicycleRotation,
                bicycleTranslationY, bicycleAlpha);
        bicycleAnim.setRepeatMode(ValueAnimator.RESTART);
        bicycleAnim.setRepeatCount(10000);
        bicycleAnim.setDuration(4000);

        //树木动画
        float treeHeight = dipToPx(this, 12);
        float treeWidth = dipToPx(this, 9);
        PropertyValuesHolder treeOneRotation = PropertyValuesHolder.ofFloat("rotation",
                0f, 0f, 0f,  //UFO飞来
                0f,                  //放射光环放大
                0f, 10f, 0f, -10f,  //吸取物体
                0f,                //放射光环缩小
                0f,               //UFO倾斜
                0f);             //UFO飞走
        PropertyValuesHolder treeOneTranslationX = PropertyValuesHolder.ofFloat("translationX",
                0f, 0f, 0f,                              //UFO飞来
                0f,                                              //放射光环放大
                0f, 0f, -treeWidth * 1.25f, -treeWidth * 2.5f,  //吸取物体
                0f,                                            //放射光环缩小
                0f,                                           //UFO倾斜
                0f);                                         //UFO飞走
        PropertyValuesHolder treeOneTranslationY = PropertyValuesHolder.ofFloat("translationY",
                0f, 0f, 0f,                            //UFO飞来
                0f,                                            //放射光环放大
                0f, 0f, -treeHeight * 1.5f, -treeHeight * 3f, //吸取物体
                0f,                                          //放射光环缩小
                0f,                                         //UFO倾斜
                0f);                                       //UFO飞走
        PropertyValuesHolder treeAlpha = PropertyValuesHolder.ofFloat("alpha",
                1f, 1f, 1f, //UFO飞来
                1f,                 //放射光环放大
                1f, 1f, 0.6f, 0f,  //吸取物体
                0f,               //放射光环缩小
                0f,              //UFO倾斜
                0f);            //UFO飞走
        ObjectAnimator treeOneAnim = ObjectAnimator.ofPropertyValuesHolder(tree_one, treeOneRotation,
                treeOneTranslationX, treeOneTranslationY, treeAlpha);
        treeOneAnim.setRepeatMode(ValueAnimator.RESTART);
        treeOneAnim.setRepeatCount(10000);
        treeOneAnim.setDuration(4000);

        PropertyValuesHolder treeTwoRotation = PropertyValuesHolder.ofFloat("rotation",
                0f, 0f, 0f,  //UFO飞来
                0f,                  //放射光环放大
                0f, 10f, 0f, -15f,  //吸取物体
                0f,                //放射光环缩小
                0f,               //UFO倾斜
                0f);             //UFO飞走
        PropertyValuesHolder treeTwoTranslationX = PropertyValuesHolder.ofFloat("translationX",
                0f, 0f, 0f,                              //UFO飞来
                0f,                                              //放射光环放大
                0f, 0f, -treeWidth * 1.75f, -treeWidth * 3.5f,  //吸取物体
                0f,                                            //放射光环缩小
                0f,                                           //UFO倾斜
                0f);                                         //UFO飞走
        PropertyValuesHolder treeTwoTranslationY = PropertyValuesHolder.ofFloat("translationY",
                0f, 0f, 0f,                               //UFO飞来
                0f,                                               //放射光环放大
                0f, 0f, -treeHeight * 2.25f, -treeHeight * 4.5f, //吸取物体
                0f,                                             //放射光环缩小
                0f,                                            //UFO倾斜
                0f);                                          //UFO飞走
        ObjectAnimator treeTwoAnim = ObjectAnimator.ofPropertyValuesHolder(tree_two, treeTwoRotation,
                treeTwoTranslationX, treeTwoTranslationY, treeAlpha);
        treeTwoAnim.setRepeatMode(ValueAnimator.RESTART);
        treeTwoAnim.setRepeatCount(10000);
        treeTwoAnim.setDuration(4000);

        ObjectAnimator runAnim = ObjectAnimator.ofFloat(sun, "rotation", 0f, 360f);
        runAnim.setRepeatMode(ValueAnimator.RESTART);
        runAnim.setRepeatCount(10000);
        runAnim.setDuration(2000);

        animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(ufoAnim, bicycleAnim, lightAnim, ringAnim, treeOneAnim, treeTwoAnim, runAnim);

    }

    private AnimationSet ufoFlyInAnimSet;//UFO飞入（倾斜、平移）动画

    private RotateAnimation ufoFlyStopRotateAnim;//UFO停止动画

    private ScaleAnimation ringScaleBigAnim;   //UFO吸取光环放大 动画
    private ScaleAnimation ringScaleSmallAnim; //UFO吸取光环缩小 动画

    private AnimationSet ufoFlyOutAnimSet;//UFO飞入（倾斜、平移）动画

    /*---- 太阳顺时针旋转 动画 ----*/
    private RotateAnimation sunRotateAnim;

    /*---- UFO 光线上移 动画集合 ----*/
    private AnimationSet lightAnimSet;

    /*---- 自行车上移 动画集合 ----*/
    private AnimationSet bicycleAnimSet;

    /*---- 树木（旋转、平移） 动画集合 ----*/
    private AnimationSet treeAnimSet, treeTwoAnimSet;
    /**
     * 初始化View动画
     */
    private void initViewAnim() {
        /*---- UFO左侧3个控件宽度X轴平移飞入 动画 ----*/
        TranslateAnimation ufoFlyInTranslateAnim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, -4f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f);
        ufoFlyInTranslateAnim.setInterpolator(new OvershootInterpolator());
        ufoFlyInTranslateAnim.setDuration(1200);

        /*---- UFO顺时针倾斜15度 动画 ----*/
        RotateAnimation ufoFlyInRotateAnim = new RotateAnimation(0f, 15f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ufoFlyInRotateAnim.setInterpolator(new AccelerateInterpolator());
        ufoFlyInRotateAnim.setDuration(50);

        /*----UFO飞入（倾斜、平移）动画集合 ----*/
        ufoFlyInAnimSet = new AnimationSet(false);
        ufoFlyInAnimSet.addAnimation(ufoFlyInRotateAnim);
        ufoFlyInAnimSet.addAnimation(ufoFlyInTranslateAnim);
        ufoFlyInAnimSet.setFillAfter(true);//动画结束时，停留在最后一帧
        ufoFlyInAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ufo.setAlpha(1f);
                bicycle.setAlpha(1f);
                tree_one.setAlpha(1f);
                tree_two.setAlpha(1f);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ufo.startAnimation(ufoFlyStopRotateAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

         /*---- UFO停下并逆时针倾斜15度 动画 ----*/
        ufoFlyStopRotateAnim = new RotateAnimation(15f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ufoFlyStopRotateAnim.setInterpolator(new DecelerateInterpolator());
        ufoFlyStopRotateAnim.setFillAfter(true);
        ufoFlyStopRotateAnim.setDuration(500);
        ufoFlyStopRotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ring.setAlpha(1f);
                ring.startAnimation(ringScaleBigAnim);
                Log.d("=====UFO Fly Stop", "Rotate AnimationEnd");
//                ring_two.startAnimation(ringScaleBigAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        /*---- UFO吸取光环放大 动画 ----*/
        ringScaleBigAnim = new ScaleAnimation(0f, 1f, 1f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ringScaleBigAnim.setInterpolator(new AccelerateInterpolator());
        ringScaleBigAnim.setDuration(400);
        ringScaleBigAnim.setFillAfter(true);
        ringScaleBigAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                light.setAlpha(1f);
                light.startAnimation(lightAnimSet);
                bicycle.startAnimation(bicycleAnimSet);
                Log.d("=====UFO Ring", "Scale Big AnimationEnd");
//                ring_two.startAnimation(ringScaleSmallAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        /*---- UFO吸取光环缩小 动画 ----*/
        ringScaleSmallAnim = new ScaleAnimation(1f, 0f, 1f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ringScaleSmallAnim.setInterpolator(new AccelerateInterpolator());
        ringScaleSmallAnim.setDuration(400);
        ringScaleSmallAnim.setFillAfter(true);
        ringScaleSmallAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ufo.startAnimation(ufoFlyOutAnimSet);
                Log.d("=====UFO Ring", "Scale Small AnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        /*---- UFO顺时针倾斜15度 动画 ----*/
        RotateAnimation ufoFlyOutRotateAnim = new RotateAnimation(0f, 15f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ufoFlyOutRotateAnim.setInterpolator(new AccelerateInterpolator());
        ufoFlyOutRotateAnim.setDuration(300);

        /*---- UFO左侧3个控件宽度X轴平移飞出 动画 ----*/
        TranslateAnimation ufoFlyOutTranslateAnim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 4.5f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f);
        ufoFlyOutTranslateAnim.setInterpolator(new AccelerateInterpolator());
        ufoFlyOutTranslateAnim.setDuration(800);


        ufoFlyOutAnimSet = new AnimationSet(false);
        ufoFlyOutAnimSet.addAnimation(ufoFlyOutRotateAnim);
        ufoFlyOutAnimSet.addAnimation(ufoFlyOutTranslateAnim);
        ufoFlyOutAnimSet.setFillAfter(true);//动画结束时，停留在最后一帧

        /*---- 太阳顺时针旋转 动画 ----*/
        sunRotateAnim = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sunRotateAnim.setInterpolator(new LinearInterpolator());
        sunRotateAnim.setDuration(1500);
        sunRotateAnim.setRepeatMode(Animation.RESTART);
        sunRotateAnim.setRepeatCount(3);
        sunRotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ufo.startAnimation(ufoFlyInAnimSet);
                sun.startAnimation(sunRotateAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        /*---- 光线上方1个控件高度Y轴平移 动画 ----*/
        TranslateAnimation lightTranslateAnim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, -1f);
        lightTranslateAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        lightTranslateAnim.setDuration(800);

        /*---- 光线透明度 动画 ----*/
        AlphaAnimation lightAlphaAnim = new AlphaAnimation(0f, 0.8f);
        lightAlphaAnim.setInterpolator(new AccelerateInterpolator());
        lightAlphaAnim.setDuration(800);

        lightAnimSet = new AnimationSet(false);
        lightAnimSet.addAnimation(lightTranslateAnim);
        lightAnimSet.addAnimation(lightAlphaAnim);
        lightAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                light.setAlpha(0f);
//                tree_one.startAnimation(treeAnimSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lightAnimSet.setFillAfter(true);


         /*---- 自行车逆时针倾斜20度 动画 ----*/
        RotateAnimation bicycleRotateAnim = new RotateAnimation(0f, -20f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        bicycleRotateAnim.setInterpolator(new AccelerateInterpolator());
        bicycleRotateAnim.setDuration(50);

        /*---- 自行车上方5个控件高度Y轴平移 动画 ----*/
        TranslateAnimation bicycleTranslateAnim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, -5f);
        bicycleTranslateAnim.setInterpolator(new LinearInterpolator());
        bicycleTranslateAnim.setDuration(600);

        /*---- 自行车透明度 动画 ----*/
        AlphaAnimation bicycleAlphaAnim = new AlphaAnimation(1f, 0f);
        bicycleAlphaAnim.setInterpolator(new AccelerateInterpolator());
        bicycleAlphaAnim.setDuration(600);

        bicycleAnimSet = new AnimationSet(false);
        bicycleAnimSet.addAnimation(bicycleRotateAnim);
        bicycleAnimSet.addAnimation(bicycleTranslateAnim);
        bicycleAnimSet.addAnimation(bicycleAlphaAnim);
        bicycleAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bicycle.setAlpha(0f);
                tree_one.startAnimation(treeAnimSet);
                tree_two.startAnimation(treeTwoAnimSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        /*---- 树1 顺时针倾斜10度 动画 ----*/
        RotateAnimation treeRightRotateAnim = new RotateAnimation(0f, 10f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
        treeRightRotateAnim.setInterpolator(new OvershootInterpolator());
        treeRightRotateAnim.setDuration(100);

        /*---- 树1 逆时针倾斜20度 动画 ----*/
        RotateAnimation treeLeftRotateAnim = new RotateAnimation(10f, -20f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
        treeLeftRotateAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        treeLeftRotateAnim.setStartOffset(100);
        treeLeftRotateAnim.setDuration(200);

         /*---- 树1 上方5个控件高度Y轴平移 动画 ----*/
        TranslateAnimation treeTranslateAnim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, -2.5f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, -4f);
        treeTranslateAnim.setInterpolator(new LinearInterpolator());
        treeTranslateAnim.setStartOffset(100);
        treeTranslateAnim.setDuration(600);

        /*---- 树透明 动画 ----*/
        AlphaAnimation treeAlphaAnim = new AlphaAnimation(1f, 0f);
        treeTranslateAnim.setStartOffset(100);
        treeAlphaAnim.setDuration(600);

        /*---- 树1 被吸走 动画集合 ----*/
        treeAnimSet = new AnimationSet(false);
        treeAnimSet.addAnimation(treeRightRotateAnim);
        treeAnimSet.addAnimation(treeLeftRotateAnim);
        treeAnimSet.addAnimation(treeTranslateAnim);
        treeAnimSet.addAnimation(treeAlphaAnim);

        treeAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tree_one.setAlpha(0f);
                ring.startAnimation(ringScaleSmallAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


         /*---- 树2 顺时针倾斜10度 动画 ----*/
        RotateAnimation treeTwoRightRotateAnim = new RotateAnimation(0f, 10f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
        treeTwoRightRotateAnim.setInterpolator(new OvershootInterpolator());
        treeTwoRightRotateAnim.setDuration(100);

        /*---- 树2 逆时针倾斜20度 动画 ----*/
        RotateAnimation treeTwoLeftRotateAnim = new RotateAnimation(10f, -20f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
        treeTwoLeftRotateAnim.setInterpolator(new AccelerateInterpolator());
        treeTwoLeftRotateAnim.setStartOffset(100);
        treeTwoLeftRotateAnim.setDuration(200);

         /*---- 树2 上方5个控件高度Y轴平移 动画 ----*/
        TranslateAnimation treeTwoTranslateAnim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, -4f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, -6f);
        treeTwoTranslateAnim.setInterpolator(new LinearInterpolator());
        treeTwoTranslateAnim.setStartOffset(100);
        treeTwoTranslateAnim.setDuration(600);

        /*---- 树2 被吸走 动画集合 ----*/
        treeTwoAnimSet = new AnimationSet(false);
        treeTwoAnimSet.addAnimation(treeTwoRightRotateAnim);
        treeTwoAnimSet.addAnimation(treeTwoLeftRotateAnim);
        treeTwoAnimSet.addAnimation(treeTwoTranslateAnim);
        treeTwoAnimSet.addAnimation(treeAlphaAnim);
        treeTwoAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tree_two.setAlpha(0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
