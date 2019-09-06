package quangtung.aprotrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import quangtung.aprotrain.com.adapter.CustomAdapter;
import quangtung.aprotrain.com.db.DBHelper;
import quangtung.aprotrain.com.entity.RemiderModify;
import quangtung.aprotrain.com.modal.Remider;

public class MainActivity extends AppCompatActivity {
    ListView remiderListView;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper.getInstance(this);

//        Remider remider = new Remider("Buy cream 1", true);
//        RemiderModify.insert(remider);
//
//        remider = new Remider("Buy cream 2", true);
//        RemiderModify.insert(remider);

        remiderListView = findViewById(R.id.remiders_listview);
        Cursor cursor = RemiderModify.findAll();
        customAdapter = new CustomAdapter(this, cursor);

        remiderListView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

        registerForContextMenu(remiderListView);

        remiderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = customAdapter.getCursor();
                cursor.moveToPosition(position);

                String noidung = cursor.getString(cursor.getColumnIndex("noidung"));
                int quantrong = cursor.getInt(cursor.getColumnIndex("quantrong"));

                Intent intent = new Intent(MainActivity.this, RemiderActivity.class);
                intent.putExtra("noidung", noidung);
                intent.putExtra("quantrong", quantrong);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
    }

    Remider currentRemider = null;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new_remider:
                currentRemider = null;
                showNoteDialog();
                break;
            case R.id.menu_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Cursor cursor = customAdapter.getCursor();
        cursor.moveToPosition(info.position);

        int id = cursor.getInt(cursor.getColumnIndex("_id"));

        switch (item.getItemId()) {
            case R.id.menu_delete_remider:
                RemiderModify.delete(id);
                updateListView();
                break;
            case R.id.menu_edit_remider:
                String noidung = cursor.getString(cursor.getColumnIndex("noidung"));
                int quantrong = cursor.getInt(cursor.getColumnIndex("quantrong"));

                currentRemider = new Remider(noidung, quantrong == 1);
                currentRemider.setId(id);

                showNoteDialog();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void showNoteDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.remider_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view);
        final Dialog dialog = builder.create();

        final EditText noidungEditText = view.findViewById(R.id.nd_noidung);
        final CheckBox quantrongCb = view.findViewById(R.id.nd_quantrong);
        Button cancelBtn = view.findViewById(R.id.nd_cancel);
        Button saveBtn = view.findViewById(R.id.nd_save);
        TextView titleTextView = view.findViewById(R.id.nd_title);

        if(currentRemider != null) {
            noidungEditText.setText(currentRemider.getNoidung());
            quantrongCb.setChecked(currentRemider.isQuantrong());

            titleTextView.setText("Edit Remider");
            titleTextView.setBackgroundResource(R.color.colorAccent);
        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidung = noidungEditText.getText().toString();
                boolean quantrong = quantrongCb.isChecked();

                if(currentRemider == null) {
                    Remider remider = new Remider(noidung, quantrong);
                    RemiderModify.insert(remider);
                } else {
                    currentRemider.setNoidung(noidung);
                    currentRemider.setQuantrong(quantrong);
                    RemiderModify.update(currentRemider);

                    currentRemider = null;
                }

                updateListView();

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void updateListView() {
        Cursor cursor = RemiderModify.findAll();
        customAdapter.changeCursor(cursor);
        customAdapter.notifyDataSetChanged();
    }
}
