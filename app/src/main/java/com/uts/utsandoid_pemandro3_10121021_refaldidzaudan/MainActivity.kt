package com.uts.utsandoid_pemandro3_10121021_refaldidzaudan

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.uts.utsandoid_pemandro3_10121021_refaldidzaudan.R
import java.util.Calendar

//03 Juni 2024 - 10121021 - Refaldi Dzaudan P - PemAndro3
class MainActivity : AppCompatActivity() {
    private var date: EditText? = null
    private var date2: EditText? = null
    private var datePickerDialog: DatePickerDialog? = null
    private var status: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        status = intent.getStringExtra(EXTRA_STATUS)

        val radioSexGroup = findViewById<RadioGroup>(R.id.opsiJK)
        val radioHub = findViewById<RadioGroup>(R.id.opsi)
        val edtNik = findViewById<EditText>(R.id.edt_nik)
        val edtNama = findViewById<EditText>(R.id.edt_nama)
        val btnLanjut = findViewById<Button>(R.id.btn_lanjut)

        date = findViewById(R.id.edt_tglkon)
        date2 = findViewById(R.id.edt_tgl)

        if (!status.isNullOrEmpty()) {
            val tes = intent.getStringExtra(EXTRA_TES)
            val tglkon = intent.getStringExtra(EXTRA_TGLKON)
            val nik = intent.getStringExtra(EXTRA_NIK)
            val nama = intent.getStringExtra(EXTRA_NAMA)
            val tgl = intent.getStringExtra(EXTRA_TGL)
            val jk = intent.getStringExtra(EXTRA_KELAMIN)
            val hub = intent.getStringExtra(EXTRA_HUBUNGAN)

            edtNik.setText(nik)
            edtNama.setText(nama)
            date?.setText(tglkon)
            date2?.setText(tgl)

            when (tes) {
                "Rapid Tes" -> radioSexGroup.check(R.id.rapid)
                "PCR" -> radioSexGroup.check(R.id.pcr)
            }

            when (jk) {
                "Perempuan" -> radioSexGroup.check(R.id.jk_p)
                "Laki - laki" -> radioSexGroup.check(R.id.jk_l)
            }

            when (hub) {
                "Orang Tua" -> radioHub.check(R.id.hub_ortu)
                "Suami / Istri" -> radioHub.check(R.id.hub_suis)
                "Anak" -> radioHub.check(R.id.hub_anak)
                "Kerabat Lainnya" -> radioHub.check(R.id.hub_lainnya)
            }
        }

        date?.setOnClickListener { showDatePickerDialog(date!!) }
        date2?.setOnClickListener { showDatePickerDialog(date2!!) }

        val intent = Intent(this, MainActivity2::class.java)
        btnLanjut.setOnClickListener {
            val nik = edtNik.text.toString()
            val nama = edtNama.text.toString()
            val tglkon = date?.text.toString()
            val tgl = date2?.text.toString()
            val selectedId = radioSexGroup.checkedRadioButtonId
            val radioSexButton = findViewById<RadioButton>(selectedId)

            val selectedId2 = radioHub.checkedRadioButtonId
            val radioHubButton = findViewById<RadioButton>(selectedId2)

            intent.apply {
                putExtra(EXTRA_NIK, nik)
                putExtra(EXTRA_NAMA, nama)
                putExtra(EXTRA_TGL, tgl)
                putExtra(EXTRA_TGLKON, tglkon)
                putExtra(EXTRA_KELAMIN, radioSexButton.text)
                putExtra(EXTRA_HUBUNGAN, radioHubButton.text)
            }
            startActivity(intent)
        }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]

        datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val bulan = when (monthOfYear + 1) {
                    1 -> "Januari"
                    2 -> "Februari"
                    3 -> "Maret"
                    4 -> "April"
                    5 -> "Mei"
                    6 -> "Juni"
                    7 -> "Juli"
                    8 -> "Agustus"
                    9 -> "September"
                    10 -> "Oktober"
                    11 -> "November"
                    12 -> "Desember"
                    else -> ""
                }
                editText.setText("$dayOfMonth $bulan $year")
            }, mYear, mMonth, mDay
        )
        datePickerDialog?.show()
    }

    companion object {
        const val EXTRA_TES = "tes"
        const val EXTRA_TGLKON = "tglkon"
        const val EXTRA_NAMA = "nama"
        const val EXTRA_NIK = "nik"
        const val EXTRA_TGL = "tgl"
        const val EXTRA_KELAMIN = "kelamin"
        const val EXTRA_HUBUNGAN = "hubungan"
        const val EXTRA_STATUS = "status"
    }
}
