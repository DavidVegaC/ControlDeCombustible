package com.assac.controldecombustible.Listeners;

import com.assac.controldecombustible.Entities.DataFormEntity;

public interface EmbeddedWifiListener {

    void sendBytesEmbedded(byte[] responseDataDevice, int direccion, int numeroBomba);

    void receiveDataForm(DataFormEntity dataFormEntity);

    void scanearQRCode(int indiceBomba);

    void receiveQRCode(String stringQRCode);

    void readNFCPlate(int indiceBomba);

    void receiveNFCPlate(byte[] responseDataDevice);

}
