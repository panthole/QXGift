package com.panthole.qxgift.core;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.panthole.qxgift.R;
import java.util.List;

/**
 * Created by panlingyue on 16/08/13
 */
public class GiftAdapter
    extends ExpandableAdapter<GiftItemView, GiftItemView, GiftItemHolder> {

  private List<GiftEntity> mData;

  private Context mContext;

  public GiftAdapter(Context context) {
    this.mContext = context;
  }

  public void setData(List<GiftEntity> mData) {
    this.mData = mData;
    notifyDataSetChanged();
  }

  @Override protected boolean isExpandable(int viewType) {
    return true;
  }

  @Override
  protected GiftItemHolder onCreateExpandableViewHolder(ViewGroup parent, int viewType) {
    GiftItemView itemView =
        (GiftItemView) LayoutInflater.from(mContext).inflate(R.layout.item_recv_gift, parent, false);
    return new GiftItemHolder(itemView);
  }

  @Override
  protected RecyclerView.ViewHolder onCreateUnexpandableViewHolder(ViewGroup parent, int viewType) {
    throw new RuntimeException("Unreachable cause we don't have nonexpandableitems");
  }

  @Override
  protected void onBindExpandableViewHolder(GiftItemHolder holder, boolean isExpanded, int height,
                                            int position,GiftItemView.OnItemSendClick onItemSendClick) {
    holder.getView().bind(mData.get(position), isExpanded, height);
    holder.getView().setOnItemSendClick(onItemSendClick,position);
  }

  @Override
  protected void onBindUnexpandableViewHolder(RecyclerView.ViewHolder holder, int position) {
    throw new RuntimeException("Unreachable cause we don't have nonexpandableitems");
  }

  @Override public int getItemCount() {
    return mData.size();
  }

}

