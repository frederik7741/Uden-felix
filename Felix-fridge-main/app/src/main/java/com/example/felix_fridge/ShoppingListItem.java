package com.example.felix_fridge;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoppingListItem implements Parcelable {
    private String productName;
    private int quantity;

    public ShoppingListItem(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected ShoppingListItem(Parcel in) {
        productName = in.readString();
        quantity = in.readInt();
    }

    public static final Parcelable.Creator<ShoppingListItem> CREATOR = new Parcelable.Creator<ShoppingListItem>() {
        @Override
        public ShoppingListItem createFromParcel(Parcel in) {
            return new ShoppingListItem(in);
        }

        @Override
        public ShoppingListItem[] newArray(int size) {
            return new ShoppingListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeInt(quantity);
    }

    public int getClickCount() {
        return quantity;
    }
}
