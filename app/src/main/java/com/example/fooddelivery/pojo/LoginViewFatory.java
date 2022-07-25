package com.example.fooddelivery.pojo;


import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment.DashBoardViewModel;
import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.OrdersPannel.OrdersViewModel;
import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.UsersPannel.Edit.EditAdminViewModel;
import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.UsersPannel.Profile.AdminProfileViewModel;
import com.example.fooddelivery.Admin.FragmentItems.Burger.BurgerViewModel;
import com.example.fooddelivery.Admin.FragmentItems.Dessert.DessertViewModel;
import com.example.fooddelivery.Admin.FragmentItems.Drink.DrinkViewModel;
import com.example.fooddelivery.Admin.Registration.AdminViewModel;
import com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome.Norfication.NotificationViewModel;
import com.example.fooddelivery.Customer.CustomerRegistration.CustomerViewModel;
import com.example.fooddelivery.Customer.Fragmnet.Burger.CustomerBurgerViewModel;
import com.example.fooddelivery.Customer.Fragmnet.Drinks.DrinksViewModel;
import com.example.fooddelivery.Customer.Fragmnet.Fries.FriesViewModel;
import com.example.fooddelivery.Customer.Fragmnet.Pizza.PizzaViewModel;
import com.example.fooddelivery.Delivery.Couriers.CourierViewModel;
import com.example.fooddelivery.Delivery.ProfileDelivery.EditProfile.EditProfileDeliverViewModel;
import com.example.fooddelivery.Delivery.ProfileDelivery.Orders.OrdersOfDeliveryViewModel;
import com.example.fooddelivery.Delivery.ProfileDelivery.Profile.DeliveryProfileViewModel;
import com.example.fooddelivery.Delivery.Registration.DeliveryViewModel;
import com.example.fooddelivery.Login.LogInRegistration.LoginViewModel;

public class LoginViewFatory implements ViewModelProvider.Factory {
    private Context context;

    public LoginViewFatory(Context context) {
        this.context = context;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(context);
        } else if (modelClass.isAssignableFrom(AdminViewModel.class)) {
            return (T) new AdminViewModel(context);
        } else if (modelClass.isAssignableFrom(CustomerViewModel.class)) {
            return (T) new CustomerViewModel(context);
        } else if (modelClass.isAssignableFrom(EditAdminViewModel.class)) {
            return (T) new EditAdminViewModel(context);
        } else if (modelClass.isAssignableFrom(DeliveryViewModel.class)) {
            return (T) new DeliveryViewModel(context);
        } else if (modelClass.isAssignableFrom(BurgerViewModel.class)) {
            return (T) new BurgerViewModel(context);
        } else if (modelClass.isAssignableFrom(DessertViewModel.class)) {
            return (T) new DessertViewModel(context);
        } else if (modelClass.isAssignableFrom(DrinkViewModel.class)) {
            return (T) new DrinkViewModel(context);
        } else if (modelClass.isAssignableFrom(DashBoardViewModel.class)) {
            return (T) new DashBoardViewModel(context);
        } else if (modelClass.isAssignableFrom(OrdersViewModel.class)) {
            return (T) new OrdersViewModel(context);
        } else if (modelClass.isAssignableFrom(EditProfileDeliverViewModel.class)) {
            return (T) new EditProfileDeliverViewModel(context);
        } else if (modelClass.isAssignableFrom(CustomerBurgerViewModel.class)) {
            return (T) new CustomerBurgerViewModel(context);
        } else if (modelClass.isAssignableFrom(DrinksViewModel.class)) {
            return (T) new DrinksViewModel(context);
        } else if (modelClass.isAssignableFrom(FriesViewModel.class)) {
            return (T) new FriesViewModel(context);
        } else if (modelClass.isAssignableFrom(NotificationViewModel.class)) {
            return (T) new NotificationViewModel(context);
        } else if (modelClass.isAssignableFrom(OrdersOfDeliveryViewModel.class)) {
            return (T) new OrdersOfDeliveryViewModel(context);
        } else if (modelClass.isAssignableFrom(PizzaViewModel.class)) {
            return (T) new PizzaViewModel(context);
        } else if (modelClass.isAssignableFrom(CourierViewModel.class)) {
            return (T) new CourierViewModel(context);
        } else if (modelClass.isAssignableFrom(DeliveryProfileViewModel.class)) {
            return (T) new DeliveryProfileViewModel(context);
        } else if (modelClass.isAssignableFrom(AdminProfileViewModel.class)) {
            return (T) new AdminProfileViewModel(context);
        } else if (modelClass.isAssignableFrom(com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DeliveryPannel.DeliveryViewModel.class)) {
            return (T) new com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DeliveryPannel.DeliveryViewModel(context);
        } else {
            throw new IllegalArgumentException("UN known ");
        }
    }
}