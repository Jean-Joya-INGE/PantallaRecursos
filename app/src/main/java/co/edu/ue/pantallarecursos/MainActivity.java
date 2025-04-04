package co.edu.ue.pantallarecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variables según la imagen proporcionada
    private Context context;
    private Activity activity;
    private TextView tvVersionAndroid;

    // Bluetooth
    private static final int codigo_respuesta_bt = 1;
    private static final int codigo_habilitatBT = 100;
    private BluetoothAdapter BTadaptador;
    private boolean bandera = false;

    // Batería
    private ProgressBar pbLevelBaterry;
    private TextView tvNivel;
    private IntentFilter batteryFilter;

    // Archivo
    private EditText nombreArchivo;
    private Object archivo; // Cambiado a Object ya que Archivo no está definido
    // Conexión
    private TextView tvConexion;
    private ConnectivityManager conexion;

    // Linterna
    private CameraManager cameraManager;
    private String cameraId;
    private boolean flashOn = false;
    private boolean offFlash;

    //File
    private EditText nameFile;
    private Object onFlash;

    //private CLFile clfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObjects();
        onFlash.setOnClickListener(this::onLigth);
        offFlash.setOnClickListener(this::offLigth);
        batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        // Referencias a los elementos de la interfaz
        this.tvVersionAndroid = findViewById(R.id.tvVersionAndroid);
        this.tvNivel = findViewById(R.id.tvNivel);
        this.nombreArchivo = findViewById(R.id.etNombreArchivo);
        // Inicialización sin funcionalidad
        this.BTadaptador = BluetoothAdapter.getDefaultAdapter();
        this.batteryFilter = new IntentFilter();
    }

    @SuppressLint("WrongViewCast")
    private void initObjects() {
        this.context = getApplicationContext();
        this.activity = this;
        this.tvVersionAndroid= findViewById(R.id.tvVersionAndroid);
        this.pbLevelBaterry = findViewById(R.id.tvBateria);
        this.tvNivel = findViewById(R.id.tvNivel);
        this.nombreArchivo = findViewById(R.id.etNombreArchivo);
        this.nameFile = findViewById(R.id.etNombreArchivo);
        this.onFlash=findViewById(R.id.btnActivar);
        this.offFlash=findViewById(R.id.btnDesactivar);


    }


}
// Inicialización de variables
//context = getApplicationContext();
//activity = this;