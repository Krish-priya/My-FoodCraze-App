package com.food.DAO;

import java.util.List;
import com.food.model.WishlistModel;

public interface WishlistInterface {

    void addWishlistItem(WishlistModel wishlist);

    void removeWishlistItem(int wishlistId);

    List<WishlistModel> getWishlistByUser(int userId);

    boolean isAlreadyAdded(int userId, int menuId);
    

    void clearWishlist(int userId);
}