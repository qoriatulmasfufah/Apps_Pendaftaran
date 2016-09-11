package id.sch.smktelkom_mlg.tugas01.xirpl1027.apps_pendaftaran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama;
    EditText etTTL;
    Button bOK;
    TextView tvHasil;
    RadioGroup rgJK;
    TextView tvJenisKelamin;
    CheckBox cbBC, cbTL, cbLA;
    TextView tvPHB, tvHobby;
    int nHobby;
    Spinner spKota;
    TextView tvKot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTTL = (EditText) findViewById(R.id.editTextTTL);
        rgJK = (RadioGroup) findViewById(R.id.radioGroupJK);
        bOK = (Button) findViewById(R.id.buttonOK);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvJenisKelamin = (TextView) findViewById(R.id.textViewJenisKelamin);
        cbBC = (CheckBox) findViewById(R.id.checkBoxBC);
        cbTL = (CheckBox) findViewById(R.id.checkBoxTL);
        cbLA = (CheckBox) findViewById(R.id.checkBoxLA);
        cbBC.setOnCheckedChangeListener(this);
        cbTL.setOnCheckedChangeListener(this);
        cbLA.setOnCheckedChangeListener(this);

        tvHobby = (TextView) findViewById(R.id.textViewHobby);
        tvPHB = (TextView) findViewById(R.id.textViewPHB);

        spKota = (Spinner) findViewById(R.id.spinnerKota);
        tvKot = (TextView) findViewById(R.id.textViewKot);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();
                doKelamin();
                doClick();
                doSpinner();

            }
        });
    }

    private void doSpinner() {
        tvKot.setText("Kota Asal " + spKota.getSelectedItem().toString());
    }

    private void doClick() {
        String hobby = "Hobby Anda:\n";
        int startlen = hobby.length();
        if (cbBC.isChecked()) hobby += cbBC.getText() + "\n";
        if (cbTL.isChecked()) hobby += cbTL.getText() + "\n";
        if (cbLA.isChecked()) hobby += cbLA.getText() + "\n";

        if (hobby.length() == startlen) hobby += "Anda Tidak Memilih";
        tvHobby.setText(hobby);
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
            etTTL.setError("Format Tahun Kelahiran xxxx");
            valid = false;
        } else {
            etTTL.setError(null);
        }
        return valid;
    }

    private void doKelamin() {
        String kelamin = null;
        if (rgJK.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgJK.getCheckedRadioButtonId());
            kelamin = rb.getText().toString();
        }

        if (kelamin == null) {
            tvJenisKelamin.setText("Pilih Jenis Kelamin Anda");
        } else {
            tvJenisKelamin.setText("Jenis Kelamin Anda : " + kelamin);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nHobby += 1;
        else nHobby -= 1;

        tvHobby.setText("Hobby (" + nHobby + ") terpilih");
    }
}
