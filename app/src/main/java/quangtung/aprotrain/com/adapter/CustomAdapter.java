package quangtung.aprotrain.com.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import quangtung.aprotrain.com.R;

public class CustomAdapter extends CursorAdapter {
    public CustomAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_items, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView noidungTextView = view.findViewById(R.id.re_title);
        FrameLayout importantView = view.findViewById(R.id.re_color);

        String content = cursor.getString(cursor.getColumnIndex("noidung"));
        int quantrong = cursor.getInt(cursor.getColumnIndex("quantrong"));

        noidungTextView.setText(content);

        if(quantrong == 0) {
            importantView.setBackgroundResource(R.color.white);
        } else {
            importantView.setBackgroundResource(R.color.red);
        }
    }
}
