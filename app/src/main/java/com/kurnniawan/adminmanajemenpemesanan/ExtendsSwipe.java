package com.kurnniawan.adminmanajemenpemesanan;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by user on 05/12/2017.
 */

public class ExtendsSwipe extends SwipeRefreshLayout {

    private ListView group;

    public ExtendsSwipe(Context context) {
        super(context);
    }

    public ExtendsSwipe(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //ambil list view untuk ngecek bisa di scroll atau engga
    @Override
    public boolean canChildScrollUp() {
        if(group == null){
            group = (ListView) getChildView();
        }
        if(group != null){
            return group.getChildCount() != 0 && group.canScrollVertically(-1);
        }
        else{
            return super.canChildScrollUp();
        }

    }

    //ambil listView aja buka buletan refreshnya
    private View getChildView(){
        int i =0;
        for(i=0; i<getChildCount(); i++){
            if(getChildAt(i) instanceof ListView){
                return  getChildAt(i);
            }
        }
        return null;
    }
}
