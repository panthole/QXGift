package com.panthole.qxgift.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by panlingyue on 16/08/13
 */
public class ExpandableHolder<V extends View, EV extends ExpandableView<V>> extends RecyclerView.ViewHolder {

    private EV view;

    public EV getView() {
        return view;
    }

    public ExpandableHolder(EV itemView) {
        super(itemView.getContainer());
        this.view = itemView;
    }
}
