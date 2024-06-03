package com.uts.utsandoid_pemandro3_10121021_refaldidzaudan

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//03 Juni 2024 - 10121021 - Refaldi Dzaudan P - PemAndro3
class MainActivity2 : AppCompatActivity() {
    private var customDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val intent = intent
        val intent2 = Intent(this, MainActivity::class.java)

        val tes = intent.getStringExtra(MainActivity.EXTRA_TES)
        val tglkon = intent.getStringExtra(MainActivity.EXTRA_TGLKON)
        val nik = intent.getStringExtra(MainActivity.EXTRA_NIK)
        val nama = intent.getStringExtra(MainActivity.EXTRA_NAMA)
        val jk = intent.getStringExtra(MainActivity.EXTRA_KELAMIN)
        val tgl = intent.getStringExtra(MainActivity.EXTRA_TGL)
        val hub = intent.getStringExtra(MainActivity.EXTRA_HUBUNGAN)

        val tvTes = findViewById<TextView>(R.id.j_tes)
        val tvTglKon = findViewById<TextView>(R.id.j_tglkon)
        val tvNik = findViewById<TextView>(R.id.j_nik)
        val tvNama = findViewById<TextView>(R.id.j_nama)
        val tvTgl = findViewById<TextView>(R.id.j_tgl)
        val tvHub = findViewById<TextView>(R.id.j_hub)
        val tvJk = findViewById<TextView>(R.id.j_jk)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan)
        val btnUbah = findViewById<Button>(R.id.btn_ubah)

        tvTes.text = tes
        tvTglKon.text = tglkon
        tvNik.text = nik
        tvNama.text = nama
        tvTgl.text = tgl
        tvJk.text = jk
        tvHub.text = hub

        initCustomDialog()

        btnSimpan.setOnClickListener { customDialog?.show() }

        btnUbah.setOnClickListener {
            intent2.apply {
                putExtra(MainActivity.EXTRA_TES, tes)
                putExtra(MainActivity.EXTRA_TGLKON, tglkon)
                putExtra(MainActivity.EXTRA_STATUS, "ubah")
                putExtra(MainActivity.EXTRA_NIK, nik)
                putExtra(MainActivity.EXTRA_NAMA, nama)
                putExtra(MainActivity.EXTRA_TGL, tgl)
                putExtra(MainActivity.EXTRA_KELAMIN, jk)
                putExtra(MainActivity.EXTRA_HUBUNGAN, hub)
            }
            startActivity(intent2)
        }
    }

    private fun initCustomDialog() {
        customDialog = Dialog(this@MainActivity2).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.activity_success)
            setCancelable(true)
        }

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
            finish() // Menutup aktivitas (kembali ke halaman utama)
        }

        val btnOk = customDialog?.findViewById<Button>(R.id.btn_ok)
        btnOk?.setOnClickListener {
            customDialog?.dismiss()
            clearData() // Membersihkan inputan
        }
    }

    // Fungsi untuk membersihkan inputan
    private fun clearData() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    companion object {
        const val EXTRA_NAMA = "nama"
        const val EXTRA_NIK = "nik"
        const val EXTRA_TGL = "tgl"
        const val EXTRA_KELAMIN = "kelamin"
        const val EXTRA_HUBUNGAN = "hubungan"
        const val EXTRA_STATUS = "status"
        const val EXTRA_TES = "tes"
        const val EXTRA_TGLKON = "tglkon"
    }
}
