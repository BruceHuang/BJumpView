package org.hbb.bjumpviewlib;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;

/**
 * Created by  hxq on 17/2/12.
 */
public class JumpViewManager {
    private static JumpViewManager ourInstance = null;

    private final Context context;
    private Handler handler = new Handler();

    private LinearLayout parentLinearLayout;

    private int maxAnimLayoutCount = 2;

    private int TAG_KEY = 1;


    private final int[] numbers = {
            R.drawable.jv_0,
            R.drawable.jv_1,
            R.drawable.jv_2,
            R.drawable.jv_3,
            R.drawable.jv_4,
            R.drawable.jv_5,
            R.drawable.jv_6,
            R.drawable.jv_7,
            R.drawable.jv_8,
            R.drawable.jv_9
    };

    private JumpViewManager(Context context) {
        this.context = context;
    }

    public static synchronized JumpViewManager getInstance(Context context) {
        if (null == ourInstance) {
            ourInstance = new JumpViewManager(context);
        }
        return ourInstance;
    }

    public LinearLayout getParentLinearLayout() {
        return parentLinearLayout;
    }

    public void setParentLinearLayout(LinearLayout parentLinearLayout) {
        this.parentLinearLayout = parentLinearLayout;
    }


    private void loadImage(String uri, ImageView imageView) {
        String uriToLoad = uri;

        if (TextUtils.isEmpty(uriToLoad)) {
            uriToLoad = "http://www.hbbtool.com/img/1.png";
        }
       // Glide.with(context).load(uriToLoad).into(imageView);
    }


    private void dispGiftCount(LinearLayout linearLayout, String hits) {
        linearLayout.setVisibility(View.GONE);
        int count = linearLayout.getChildCount();

        for (int i = 0; i < count; i++) {
            linearLayout.getChildAt(i).setVisibility(View.GONE);
        }

        int stringLen = hits.length();

        for (int i = 0; i < stringLen; i++) {
            int k = Integer.parseInt(hits.charAt(i) + "");
            ImageView imageView = (ImageView) linearLayout.getChildAt(i);
            imageView.setVisibility(View.VISIBLE);

            if ((imageView.getTag() != null) && (((Integer) imageView.getTag()).intValue() == k)) {
                continue;
            } else {
                switch (k) {
                    case 0:
                        imageView.setImageResource(this.numbers[0]);
                        continue;
                    case 1:
                        imageView.setImageResource(this.numbers[1]);
                        continue;
                    case 2:
                        imageView.setImageResource(this.numbers[2]);
                        continue;
                    case 3:
                        imageView.setImageResource(this.numbers[3]);
                        continue;
                    case 4:
                        imageView.setImageResource(this.numbers[4]);
                        continue;
                    case 5:
                        imageView.setImageResource(this.numbers[5]);
                        continue;
                    case 6:
                        imageView.setImageResource(this.numbers[6]);
                        continue;
                    case 7:
                        imageView.setImageResource(this.numbers[7]);
                        continue;
                    case 8:
                        imageView.setImageResource(this.numbers[8]);
                        continue;
                    case 9:
                        imageView.setImageResource(this.numbers[9]);
                        continue;

                }

                imageView.setTag(Integer.valueOf(k));
            }

        }
        linearLayout.setVisibility(View.VISIBLE);
    }


    /**
     * @param view
     * @param giftBean
     * @param linearlayout
     */
    public void reLoadGiftView(View view, GiftBean giftBean, LinearLayout linearlayout) {


        view.setTag(giftBean);

        dispGiftCount((LinearLayout) view.findViewById(R.id.hit_layout), giftBean.getHits());

        ((TextView) view.findViewById(R.id.board_gift_msg_tv)).setText((new StringBuilder()).append("送出\t").append(giftBean.getGiftname()).toString());

        LinearLayout board_gift_icon_layout_num = (LinearLayout) view.findViewById(R.id.board_gift_icon_layout_num);

        Animation anim_live_view_out = AnimationUtils.loadAnimation(this.context, R.anim.anim_jump_view_out);
        anim_live_view_out.setStartOffset(1000l + 500L);

        view.startAnimation(anim_live_view_out);

        SpringSystem springSystem = SpringSystem.create();
        Spring spring = springSystem.createSpring();
        spring.setSpringConfig(SpringConfig.fromBouncinessAndSpeed(20D, 3.5D));
        spring.addListener(new ScaleSpringListener(board_gift_icon_layout_num));

        anim_live_view_out.setAnimationListener(new AnimateOutListener(spring, linearlayout, view));

    }


    public LayoutTransition createLatyoutTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(2, null);
        layoutTransition.setAnimator(3, null);
        PropertyValuesHolder left = PropertyValuesHolder.ofInt("left", new int[]{0, 1});
        PropertyValuesHolder top = PropertyValuesHolder.ofInt("top", new int[]{0, 1});
        PropertyValuesHolder right = PropertyValuesHolder.ofInt("right", new int[]{0, 1});
        PropertyValuesHolder bottom = PropertyValuesHolder.ofInt("bottom", new int[]{0, 1});
        layoutTransition.setAnimator(0, ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{left, top, right, bottom, PropertyValuesHolder.ofFloat("scaleX", new float[]{1.0F, 1.0F, 1.0F})}).setDuration(layoutTransition.getDuration(0)));
        layoutTransition.setAnimator(1, ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{left, top, right, bottom, PropertyValuesHolder.ofFloat("scaleX", new float[]{1.0F, 1.0F, 1.0F})}).setDuration(layoutTransition.getDuration(1)));
        return layoutTransition;
    }

    public View generagteView(GiftBean giftBean, LinearLayout linearlayout) {
        View view;

        view = LayoutInflater.from(this.context).inflate(R.layout.jump_view, null);
        view.setTag(giftBean);
       // loadImage("http://www.hbbtool.com/img/1.png", (ImageView) view.findViewById(R.id.board_gift_avatar_iv));

        ((TextView) view.findViewById(R.id.board_gift_nick_tv)).setText(giftBean.getNkName()); //nick name = ncnm
        ((TextView) view.findViewById(R.id.board_gift_msg_tv)).setText((new StringBuilder()).append("送出\t").append(giftBean.getGiftname()).toString());

        LinearLayout hit_layout = (LinearLayout) view.findViewById(R.id.hit_layout);
        for (int i = 0; i < 5; i++) {
            ImageView imageview = new ImageView(this.context);
            imageview.setVisibility(View.GONE);
            hit_layout.addView(imageview);
        }

        dispGiftCount(hit_layout, giftBean.getHits());


        //礼物父容器
        view.findViewById(R.id.board_gift_content_layer).startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.anim_jump_enter_left));
//      礼物数量 （x 10）
        LinearLayout board_gift_icon_layout_num = (LinearLayout) view.findViewById(R.id.board_gift_icon_layout_num);
        board_gift_icon_layout_num.setVisibility(View.INVISIBLE);

        ImageView board_gift_icon_fadein_iv = (ImageView) view.findViewById(R.id.board_gift_icon_fadein_iv);

        //礼物的图片anim
        Animation anim_gift_icon_left = AnimationUtils.loadAnimation(this.context, R.anim.anim_jump_icon_left);
        board_gift_icon_fadein_iv.setAnimation(anim_gift_icon_left);


        SpringSystem springSystem = SpringSystem.create();

        Spring spring = springSystem.createSpring();
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(20D, 3.5D));

        spring.addListener(new ScaleSpringListener(board_gift_icon_layout_num));
        anim_gift_icon_left.setAnimationListener(new AnimateLeftInListener(board_gift_icon_layout_num, spring));

        Animation anim_live_view_out = AnimationUtils.loadAnimation(this.context, R.anim.anim_jump_view_out);

        anim_live_view_out.setStartOffset(1000L + 1000L);

        anim_live_view_out.setAnimationListener(new OrgAnimateOutListener(linearlayout, view));
        view.startAnimation(anim_live_view_out);

        return view;
    }


    private class AnimateLeftInListener
            implements Animation.AnimationListener {

        final LinearLayout linearLayout;
        final Spring spring;

        public void onAnimationEnd(Animation animation) {
            linearLayout.setVisibility(View.VISIBLE);
            spring.setVelocity(1.8D);

            spring.setEndValue(1.0D);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        AnimateLeftInListener(LinearLayout linearlayout, Spring spring) {
            super();
            this.linearLayout = linearlayout;
            this.spring = spring;

        }
    }

    private class OrgAnimateOutListener
            implements Animation.AnimationListener {

        final LinearLayout a;
        final View b;

        public void onAnimationEnd(Animation animation) {

            Log.d("anim", "onAnimationEnd ===== " + a.toString());

            class Runnable1 implements Runnable {


                public void run() {
                    a.removeView(b);
                }

                Runnable1() {

                    super();
                }
            }

            (new Handler()).post(new Runnable1());
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        OrgAnimateOutListener(LinearLayout linearlayout, View view) {
            super();
            a = linearlayout;
            b = view;

        }
    }


    //放入队列
    public void playAnimation(GiftBean giftBean, LinearLayout linearLayout) {

        int count = linearLayout.getChildCount();


        for (int i = 0; i < count; i++) {
            View view = linearLayout.getChildAt(i);
            GiftBean giftFromTag = (GiftBean) (view.getTag());
            if (giftBean.getUid().equals(giftFromTag.getUid()) && giftBean.getGfid().equals(giftFromTag.getGfid())) {
                handler.postDelayed(new SameGiftUpdateRunnable(view, giftBean, linearLayout), 200L);
                return;
            }
        }

        if (count < maxAnimLayoutCount) {
            View secondView = generagteView(giftBean, linearLayout);
            linearLayout.addView(secondView);
        } else if (count == maxAnimLayoutCount) {

            int oldderIndex = 0;
            long dtime = 0;

            for (int i = 0; i < maxAnimLayoutCount; i++) {
                View view = linearLayout.getChildAt(i);
                GiftBean giftFromTag = (GiftBean) (view.getTag());

                if (null == giftFromTag) continue;

                long dtimeTmp = giftBean.getTime() - giftFromTag.getTime();
                if (dtimeTmp > dtime) {
                    oldderIndex = i;
                    dtime = dtimeTmp;
                } else {

                }

            }


            View view = linearLayout.getChildAt(oldderIndex);

            view.clearAnimation();
            linearLayout.removeView(view);
            View nextFirstView = generagteView(giftBean, linearLayout);
            linearLayout.addView(nextFirstView, oldderIndex);


        }

    }


    private class SameGiftUpdateRunnable implements Runnable {

        final View view;
        final GiftBean giftBean;
        final LinearLayout linearlayout;

        public void run() {

            reLoadGiftView(view, giftBean, linearlayout);
        }

        SameGiftUpdateRunnable(View view, GiftBean giftBean, LinearLayout linearlayout) {
            super();
            this.view = view;
            this.giftBean = giftBean;
            this.linearlayout = linearlayout;

        }
    }


    private class ScaleSpringListener extends SimpleSpringListener {


        private LinearLayout linearlayout;

        public ScaleSpringListener(LinearLayout linearlayout) {
            super();
            this.linearlayout = linearlayout;
        }


        @Override
        public void onSpringUpdate(Spring spring) {

            float f1 = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 2D, 1.0D, 2D, 1.0D);
            this.linearlayout.setScaleX(f1);
            this.linearlayout.setScaleY(f1);

        }

    }

    private class AnimateOutListener
            implements Animation.AnimationListener {

        final Spring spring;
        final LinearLayout linearlayout;
        final View view;


        public void onAnimationEnd(Animation animation) {


            class RemoveRunnable
                    implements Runnable {


                public void run() {
                    linearlayout.removeView(view);
                }

                RemoveRunnable() {
                    super();
                }
            }

            (new Handler()).post(new RemoveRunnable());
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            spring.setVelocity(1.8D);
            spring.setEndValue(1.0d);

        }

        AnimateOutListener(Spring spring, LinearLayout linearlayout, View view) {
            super();
            this.spring = spring;
            this.linearlayout = linearlayout;
            this.view = view;

        }
    }


}
