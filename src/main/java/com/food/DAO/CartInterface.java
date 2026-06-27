package com.food.DAO;

import java.util.List;
import com.food.model.CartModel;

public interface CartInterface {

    void addCartItem(CartModel cart);

    List<CartModel> getAllCartItems();

    void updateCartItem(int menuId, int quantity);

    void deleteCartItem(int menuId);

    void clearCart();
    void increaseQuantity(int menuId);

    void decreaseQuantity(int menuId);

}