package com.assac.controldecombustible.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.assac.controldecombustible.DI.BaseApplication;
import com.assac.controldecombustible.Listeners.LoginListener;
import com.assac.controldecombustible.R;
import com.assac.controldecombustible.Storage.Rest;
import com.assac.controldecombustible.Util.CustomAnimation;
import com.assac.controldecombustible.Util.CustomProgressDialog;
import com.assac.controldecombustible.Util.Internet.ValidateConn;
import com.assac.controldecombustible.Util.NavigationFragment;
import com.assac.controldecombustible.View.Fragment.LoginFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private LoginFragment loginFragment =new LoginFragment();
    CustomProgressDialog customProgressDialog = new CustomProgressDialog(LoginActivity.this);

    @Inject
    Retrofit retrofit;

    Thread threadDownloadMasters, threadRegisterAccessToServer;
    private Handler handlerDownloadMasters = new Handler();
    private Rest rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpDagger();
        rest = new Rest(this,retrofit);
        goToLogin();
    }

    private void setUpDagger(){
        ((BaseApplication)getApplication()).getRetrofitComponent().inject(this);
    }

    private void goToLogin(){
        NavigationFragment.addFragment(null, loginFragment, "loginFragment", this,
                R.id.Login_linear_container, false, CustomAnimation.LEFT_RIGHT);
    }

    @Override
    public void goToMain() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loadService() {

        ValidateConn validateConn = new ValidateConn(rest,this,"1", "1");
        validateConn.mstartConn();

    }


}