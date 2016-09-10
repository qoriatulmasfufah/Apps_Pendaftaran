package id.sch.smktelkom_mlg.tugas01.xirpl1027.apps_pendaftaran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etNama;
    EditText etTTL;
    Button bOK;
    TextView tvHasil;

    RadioButton rbLL, rbP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNama = (EditText) findViewById(R.id.editTextNama);
        etTTL = (EditText) findViewById(R.id.editTextTTL);
        bOK = (Button) findViewById(R.id.buttonOK);

        rbLL = (RadioButton) findViewById(R.id.radioButtonLL);
        rbP = (RadioButton) findViewById(R.id.radioButtonP);

        tvHasil = (TextView) findViewById(R.id.textViewHasil);


        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();

            }
        });

        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();
            }
        });
    }

    private void doClick() {
        String hasil = null;
        if (rbLL.isChecked()) {
            hasil = rbLL.getText().toString();
        } else if (rbP.isChecked()) {
            hasil = rbP.getText().toString();
        }
        if (hasil == null) {
            tvHasil.setText("Belum memilih Jenis Kelamin");
        } else {
            tvHasil.setText(("Jenis Kelamin Anda : " + hasil));
        }
    }

    private void doProcess() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTTL.getText().toString());
            tvHasil.setText(nama + " lahir tahun " + tahun);
        }

    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String tahun = etTTL.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }
        if (tahun.isEmpty()) {
            etTTL.setError("Tahun Kelahiran belum diisi");
            valid = false;
        } else if (tahun.length() != 4) {
            etTTL.setError("Format Tahun Kelahiran bukan xxxx");
            valid = false;
        } else {
            etTTL.setError(null);
        }
        return valid;
    }
}
