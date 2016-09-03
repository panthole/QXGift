package com.panthole.qxgift.core;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.panthole.qxgift.R;

/**
 * Created by panlingyue on 16/08/13
 */
public class GiftItemView extends FrameLayout implements ExpandableView<GiftItemView> {

    private GiftEntity mData;

    private View mExpandedView;

    private View mCollapsedView;
    private ImageView mExpandGiftImage;
    private TextView mExpandGiftName;
    private TextView mExpandGiftPrice;
    private Button mExpandGiftSend;

    private TextView price = null;
    private ImageView gift_num_imageview = null;
    private TextView gift_num_textview = null;
    private ImageView imageview = null;
    private RelativeLayout llgifview = null;
    private ImageView gift_type_label = null;
    private TextView gift_name = null;
    private RelativeLayout giftPriceContainer = null;

    private OnItemSendClick onItemSendClick;

    private int postion;

    public GiftItemView(Context context) {
        super(context);
    }

    public GiftItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("Expand", "onFinishInflate for " + this);
        mExpandedView = findViewById(R.id.expanded_container);
        mCollapsedView = findViewById(R.id.fragment_live_room_gift_lrgifview);

        price = (TextView) findViewById(R.id.fragment_live_room_gift_giftprice);
        gift_num_imageview =
                (ImageView) findViewById(R.id.fragment_live_room_gift_num_imageview);
        gift_num_textview = (TextView) findViewById(R.id.fragment_live_room_gift_num_textview);
        imageview = (ImageView) findViewById(R.id.fragment_live_room_gift_imageview);
        llgifview = (RelativeLayout) findViewById(R.id.fragment_live_room_gift_lrgifview);
        gift_type_label = (ImageView) findViewById(R.id.fragment_live_room_gift_type_label);
        gift_name = (TextView) findViewById(R.id.fragment_live_room_gift_name);
        giftPriceContainer = (RelativeLayout) findViewById(R.id.gift_price_container);

        mExpandGiftImage = (ImageView) findViewById(R.id.fragment_live_room_gift_imageview_expand);
        mExpandGiftName= (TextView) findViewById(R.id.fragment_live_room_gift_name_expand);
        mExpandGiftPrice = (TextView) findViewById(R.id.fragment_live_room_gift_giftprice_expand);
        mExpandGiftSend = (Button) findViewById(R.id.fragment_live_room_gift_btsend_expand);
    }

    public void initView(GiftEntity item) {
        if ("-1".equals(item.getProductId())) {
            llgifview.setVisibility(View.INVISIBLE);
            return;
        }
        gift_num_textview.setVisibility(View.GONE);
        price.setText(item.getPrice());
        gift_name.setText(item.getName());
        if (item.getGiftType().equals("1")) {
            //贵族礼物
            gift_type_label.setVisibility(View.VISIBLE);

            //Picasso.with(getContext())
            //        .load(R.drawable.gift_dialog_noble_label)
            //        .fit()
            //        .into(gift_type_label);
        } else if (item.getGiftType().equals("2")) {
            //守护礼物
            gift_type_label.setVisibility(View.VISIBLE);
            //Picasso.with(getContext())
            //        .load(R.drawable.gift_dialog_guard_label)
            //        .fit()
            //        .into(gift_type_label);
        } else {
            gift_type_label.setVisibility(View.GONE);
        }
        //add 隐藏label
        gift_type_label.setVisibility(View.GONE);

        //if (item.getWeekStar()) {
        //    gift_num_imageview.setVisibility(View.VISIBLE);
        //    Picasso.with(getContext()).load(R.drawable.week_star).fit().into(gift_num_imageview);
        //} else if (item.getIsLuck()) {
        //    gift_num_imageview.setVisibility(View.VISIBLE);
        //    Picasso.with(getContext()).load(R.drawable.lucky_gift).fit().into(gift_num_imageview);
        //} else {
        //    gift_num_imageview.setVisibility(View.GONE);
        //}
        ////add 隐藏
        //gift_num_imageview.setVisibility(View.GONE);
        //
        //try {
        //    Picasso.with(getContext())
        //            .load(item.getImageUrl())
        //            .error(R.drawable.bag_image_default)
        //            .placeholder(R.drawable.bag_image_default)
        //            .fit()
        //            .into(imageview);
        //} catch (Exception e) {
        //    imageview.setVisibility(View.INVISIBLE);
        //}

        int currentGiftId;
        if (item.getProductId().equals("")) {
            currentGiftId = 0;
        } else {
            currentGiftId = Integer.parseInt(item.getProductId());
        }
        //xkj mark
//        if (currentGiftId == miSelectedGiftItemId) {
//            ViewUtils.setBackground(llgifview,
//                    getContext().getResources().getDrawable(R.drawable.bg_gift_grid_selected));
//        } else {
//            llgifview.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
//        }

        //add 免费礼物价格

    }

    private void bindExpanded(final GiftEntity item, GiftItemView view) {
        //view.mExpandedContributionsText.setText(buildExpandedText(data));
        mExpandGiftPrice.setText(item.getPrice());
        mExpandGiftName.setText(item.getName());
        //try {
        //    Picasso.with(getContext())
        //            .load(item.getImageUrl())
        //            .error(R.drawable.bag_image_default)
        //            .placeholder(R.drawable.bag_image_default)
        //            .fit()
        //            .into(view.mExpandGiftImage);
        //} catch (Exception e) {
        //    view.mExpandGiftImage.setVisibility(View.INVISIBLE);
        //}

        mExpandGiftSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemSendClick!=null)
                {
                    onItemSendClick.onSendGiftButtonClick(item,postion);
                }
            }
        });
    }

    public void bind(GiftEntity data, boolean isExpanded, int height) {
        this.mData = data;
        if (isExpanded) {
            bindExpanded(data, this);
        } else {
            bindCollapsed(data);
        }
        getLayoutParams().height = height;
    }

    private void bindCollapsed(GiftEntity item) {
        initView(item);
    }

    @Override
    public View getContainer() {
        return this;
    }

    @Override
    public View getExpandedStateView() {
        return mExpandedView;
    }

    @Override
    public View getCollapsedStateView() {
        return mCollapsedView;
    }

    @Override
    public void bindViews(boolean expanded) {
        if (expanded) {
            bindExpanded(mData, this);
        } else {
            bindCollapsed(mData);
        }
    }

    @Override
    public void bindExpandedState(GiftItemView another) {
        another.mExpandedView.setVisibility(VISIBLE);
        another.mCollapsedView.setVisibility(GONE);
        bindExpanded(mData, another);
        another.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    public void setOnItemSendClick(OnItemSendClick onItemSendClick, int postion) {
        this.onItemSendClick = onItemSendClick;
        this.postion = postion;
    }

    public interface OnItemSendClick {

        void onSendGiftButtonClick(GiftEntity item, int postion);
    }
}
