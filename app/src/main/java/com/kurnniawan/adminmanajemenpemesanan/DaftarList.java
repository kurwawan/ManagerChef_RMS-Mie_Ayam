package com.kurnniawan.adminmanajemenpemesanan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 18/11/2017.
 */

public class DaftarList extends ArrayAdapter<InputConstructor> {

    private Activity context;
    private List<InputConstructor> inputConstructorList;

    public DaftarList(Activity context, List<InputConstructor> inputConstructorList) {
        super(context, R.layout.list_pesanan, inputConstructorList);
        this.context = context;
        this.inputConstructorList = inputConstructorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View listItemView, @NonNull ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_pesanan, parent, false);
        }

        TextView npel = listItemView.findViewById(R.id.tvNamaPelayan);
        TextView time = listItemView.findViewById(R.id.tvWaktu);
        TextView km = listItemView.findViewById(R.id.tvKodeMeja);
        TextView npem = listItemView.findViewById(R.id.tvNamaPemesan);
        TextView pesanan = listItemView.findViewById(R.id.tvIsiPesanan);
        TextView ket = listItemView.findViewById(R.id.tvKeterangan);

        InputConstructor inputConstructor = inputConstructorList.get(position);

        StringBuilder builder = new StringBuilder();
        Map<String,Object> daftarPesanan = inputConstructor.getDaftarPesanan();
        if (daftarPesanan != null) {
            for (String key : daftarPesanan.keySet()) {
                builder.append(key).append(" (").append(daftarPesanan.get(key)).append(")\n");            }
        } else {
            builder.append("KOSONG");
        }

        km.setText(inputConstructor.getIdMeja());
        npel.setText(inputConstructor.getNamaPelayan());
        npem.setText(inputConstructor.getNamaPemesan());
        ket.setText(inputConstructor.getKeteranganPemesan());
        pesanan.setText(builder.toString());

        long Time = inputConstructor.getmTimestamp();
        Date date = new Date(Time);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        time.setText(sdf.format(date));

        return listItemView;
    }
}
