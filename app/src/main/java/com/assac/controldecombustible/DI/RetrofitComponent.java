package com.assac.controldecombustible.DI;

import com.assac.controldecombustible.Storage.Rest;
import com.assac.controldecombustible.View.Activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    void inject(LoginActivity loginActivity);
}
