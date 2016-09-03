package com.panthole.qxgift.core;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * Created by panlingyue on 16/08/13
 */
public abstract class ExpandableRecyclerView<V extends View, EV extends ExpandableView<V>,
        EVH extends ExpandableHolder<V, EV>> extends FrameLayout implements ExpandableAdapter.onToggleListener {

    protected abstract V createMeasurableView(Context context, ViewGroup parent);

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private V mMeasurableView;

    public ExpandableRecyclerView(Context context, AttributeSet set) {
        super(context, set);
        ScrollView scrollView = new ScrollView(context);
        mMeasurableView = createMeasurableView(context, scrollView);
        ViewGroup.LayoutParams measurableParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollView.addView(mMeasurableView, measurableParams);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(scrollView, params);
        scrollView.setVisibility(INVISIBLE);
        LayoutParams recyclerViewParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView = new RecyclerView(context);
        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        addView(mRecyclerView, recyclerViewParams);
    }

    public void setAdapter(ExpandableAdapter<V, EV, EVH> adapter) {
        adapter.mMeasurableView = mMeasurableView;
        adapter.mOnToggleListener = this;
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onToggle(int position, View itemVIew) {
        Animator recyclerViewAnimator = AnimatorHelper.createRecyclerViewAnimator(mLayoutManager, itemVIew, mRecyclerView, position);
        recyclerViewAnimator.start();
    }
}
