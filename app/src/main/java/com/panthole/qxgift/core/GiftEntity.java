package com.panthole.qxgift.core;
import java.util.HashMap;

public class GiftEntity {

    String name;//商品名称
    String price;//商品价格
    String promotionPrice;//促销价格
    String productId = "";//商品ID
    String imageUrl;//商品图标地址
    String status;//商品状态，-1 下架 0:默认 1：上架
    String paymentMethod;//礼物支付方式，1奇豆，2钻石，3奇豆+钻石
    String isWeekStar;
    String isLuck;
    String description;

    int isNew;
    HashMap<String, String> effect;//特效数量，达到n个后显示连送  {"0":"10"}
    private String giftType;//商品类型，0：常规，1：贵族商品，2：守护商品

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public HashMap<String, String> getEffect() {
        return effect;
    }

    public void setEffect(HashMap<String, String> effect) {
        this.effect = effect;
    }
}
