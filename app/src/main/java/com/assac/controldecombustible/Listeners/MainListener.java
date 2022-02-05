package com.assac.controldecombustible.Listeners;

public interface MainListener {
    void enableForegroundDispatchSystem();
    void disableForegroundDispatchSystem();

    void showProgressDialog(String message);
    void dismissProgressDialog();

    void CloseSession();
}
