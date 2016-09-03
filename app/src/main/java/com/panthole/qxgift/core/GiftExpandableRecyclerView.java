package com.panthole.qxgift.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.panthole.qxgift.R;

/**
 * Created by panlingyue on 16/08/13
 */
public class GiftExpandableRecyclerView extends ExpandableRecyclerView<GiftItemView,
        GiftItemView, GiftItemHolder>{


    @Override
    protected GiftItemView createMeasurableView(Context context, ViewGroup parent) {
        return (GiftItemView) LayoutInflater.from(context).inflate(R.layout.item_recv_gift, parent, false);
    }

    public GiftExpandableRecyclerView(Context context, AttributeSet set) {
        super(context, set);
    }

}
