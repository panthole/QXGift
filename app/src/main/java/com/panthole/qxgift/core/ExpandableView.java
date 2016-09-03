package com.panthole.qxgift.core;

import android.view.View;

/**
 * Created by panthole on 16-08-13
 */
public interface ExpandableView<T extends View> {
    View getContainer();

    View getExpandedStateView();

    View getCollapsedStateView();

    void bindViews(boolean expanded);

    void bindExpandedState(T another);
}
