package com.panthole.qxgift.core;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by panlingyue on 16/08/13
 */
public class AnimatorHelper {

    public static final int DURATION = 200;

    public static void crossfadeViews(View container, final View viewToFadeIn, final View viewToFadeOut,
                                      int currentHeight, int desiredHeight) {
        Animator heightAnimator = createHeightAnimator(container, currentHeight, desiredHeight);
        ObjectAnimator fadeInX = ObjectAnimator.ofFloat(viewToFadeIn, "scaleX", 0f, 1.2f, 1f);
        ObjectAnimator fadeInY = ObjectAnimator.ofFloat(viewToFadeIn, "scaleY", 0f, 1.2f, 1f);
        fadeInX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //viewToFadeIn.setAlpha(0f);
                viewToFadeIn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ObjectAnimator alphaX = ObjectAnimator.ofFloat(viewToFadeOut, "alpha", 1f, 0f);
        ObjectAnimator fadeoutX = ObjectAnimator.ofFloat(viewToFadeIn, "scaleX", 1f, 0f);
        ObjectAnimator fadeoutY = ObjectAnimator.ofFloat(viewToFadeIn, "scaleY", 1f, 0f);
        alphaX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                viewToFadeOut.setVisibility(View.GONE);
                viewToFadeOut.setAlpha(1f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.setDuration(DURATION);
        set.playTogether(fadeInX, fadeInY, alphaX);
        set.start();
    }

    private static Animator createHeightAnimator(final View view, int from, int to) {
        ValueAnimator animator = ValueAnimator.ofInt(from, to);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                view.getLayoutParams().height = value;
                view.requestLayout();
            }
        });
        return animator;
    }

    public static Animator createRecyclerViewAnimator(final LinearLayoutManager layoutManager,
                                                      View item,
                                                      final RecyclerView mRecyclerView,
                                                      final int position) {
        ValueAnimator animator = ValueAnimator.ofInt(
                item.getTop() - ((ViewGroup.MarginLayoutParams) item.getLayoutParams()).topMargin, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                //layoutManager.scrollToPosition(position);
                layoutManager.smoothScrollToPosition(mRecyclerView,null,position);
                //layoutManager.scrollToPositionWithOffset(position, value);
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(DURATION);
        animator.setStartDelay(800);
        return animator;
    }
}
