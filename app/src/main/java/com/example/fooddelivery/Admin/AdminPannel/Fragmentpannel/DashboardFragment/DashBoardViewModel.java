package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class DashBoardViewModel extends ViewModel  {
    Context context ;
    RepoDashBoardPannel repo = new RepoDashBoardPannel();
MutableLiveData<Integer> mutableLiveData  = new MutableLiveData<>();
    public DashBoardViewModel(Context context) {
        this.context = context;
    }

    public void getData (){
        repo.getNumberOfCustomers().subscribeWith(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(@NonNull Integer integer) {
                mutableLiveData.postValue(integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        repo.getNumberOfDeliverys().subscribeWith(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(@NonNull Integer integer) {
                mutableLiveData.postValue(integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
     repo.getNumberOfOrders().subscribeWith(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(@NonNull Integer integer) {
                mutableLiveData.postValue(integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }

    /*
    public int getNumberOFusers(){
        return repo.getNumberOfCustomers().subscribeWith(new Dispo)
    }

     */
}
