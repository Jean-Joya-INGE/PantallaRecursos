package co.edu.ue.pantallarecursos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variables según la imagen proporcionada
    private Context context;
    private Activity activity;
    private TextView tvVersionAndroid;
    private int versionSDK;
    // Batería
    private ProgressBar pbLevelBaterry;
    private TextView tvLevelBaterry;
    private IntentFilter batteryFilter;
    // Conexión
    private TextView tvConexion;
    private ConnectivityManager conexion;
    // Linterna
    private CameraManager cameraManager;
    private String cameraId;
    private Button onFlash;
    private Button offFlash;
    //File
    private EditText nameFile;
    //private CLFile clfile;
    // Bluetooth
    private Button onBlue;
    private Button offBlue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initObjects();
        onFlash.setOnClickListener(this::OnLigth);
        offFlash.setOnClickListener(this::OffLigth);
        batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, batteryFilter);

    }
    // Bateria
    BroadcastReceiver broadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int levelBattery = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            pbLevelBaterry.setProgress(levelBattery);
            tvLevelBaterry.setText("Nivel de bateria: "+levelBattery+"%");
        }
    };
    private  void checkConnection(){
        try{
            conexion= (ConnectivityManager) (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conexion != null){
            NetworkInfo networkInfo = conexion.getActiveNetworkInfo();
            boolean stateNet = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if(stateNet){
                tvConexion.setText("State On");
            }else{
                tvConexion.setText("State OFF Or No Info");
            }
        }
        }catch(Exception e){
            Log.i("CONEXION:",e.getMessage());
        }
    }

    // Linterna
    private void OnLigth(View view){
        try{
            cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            cameraId= cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,true);
        }catch(CameraAccessException e){
            Log.i("Linterna: error al encender",e.getMessage());
        }
    }
    private void OffLigth(View view){
        try{
            cameraManager.setTorchMode(cameraId,false);
        }catch(Exception e){
            Log.i("Linterna:",e.getMessage());
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Version Android
        String versionSO= Build.VERSION.RELEASE;
        versionSDK= Build.VERSION.SDK_INT;
        tvVersionAndroid.setText("versionSO: "+versionSO+"/ SDK: "+versionSDK);
        checkConnection();
    }

    private void initObjects() {
        this.context = getApplicationContext();
        this.activity = this;
        this.tvVersionAndroid= findViewById(R.id.tvVersionAndroid);
        this.pbLevelBaterry = findViewById(R.id.pbLevelBaterry);
        this.tvLevelBaterry = findViewById(R.id.tvNivel);
        this.tvConexion = findViewById(R.id.tvConection2);
        this.nameFile = findViewById(R.id.etNombreArchivo);
        this.onFlash = findViewById(R.id.OnFlash);
        this.offFlash = findViewById(R.id.OffFlash);
        this.onBlue = findViewById(R.id.btnActivar);
        this.offBlue = findViewById(R.id.btnDesactivar);
    }


}
