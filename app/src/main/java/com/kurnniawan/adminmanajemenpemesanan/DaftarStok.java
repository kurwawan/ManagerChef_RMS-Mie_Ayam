package com.kurnniawan.adminmanajemenpemesanan;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by user on 05/12/2017.
 */
public class DaftarStok {
    boolean status;

    public DaftarStok(){

    }

    public DaftarStok(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
