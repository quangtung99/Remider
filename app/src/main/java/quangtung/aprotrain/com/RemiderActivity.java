package quangtung.aprotrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RemiderActivity extends AppCompatActivity {
    TextView noidungTv, quantrongTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remider);

        String noidung = getIntent().getStringExtra("noidung");
        int quantrong = getIntent().getIntExtra("quantrong", 0);

        noidungTv = findViewById(R.id.an_noidung);
        quantrongTv = findViewById(R.id.an_quantrong);

        noidungTv.setText(noidung);
        if(quantrong == 0) {
            quantrongTv.setText("Mục không quan trọng");
        } else {
            quantrongTv.setText("Mục quan trọng");
        }
    }
}
