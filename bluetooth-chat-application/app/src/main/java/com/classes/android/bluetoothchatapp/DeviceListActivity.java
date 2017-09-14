package com.classes.android.bluetoothchatapp;

import android.bluetooth.BluetoothAdapter;


        import java.util.Set;

        import android.app.Activity;
        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.Window;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;

public class DeviceListActivity extends Activity {

    private TextView tvDeviceListPairedDeviceTitle, tvDeviceListNewDeviceTitle;
    private ListView lvDeviceListPairedDevice, lvDeviceListNewDevice;
    private Button btnDeviceListScan;

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> pairedDevicesArrayAdapter;
    private ArrayAdapter<String> newDevicesArrayAdapter;

    public static String DEVICE_ADDRESS = "deviceAddress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.device_list);

        setResult(Activity.RESULT_CANCELED);

        getWidgetReferences();
        bindEventHandler();
        initializeValues();
    }

    private void getWidgetReferences() {
        tvDeviceListPairedDeviceTitle = (TextView) findViewById(R.id.tvDeviceListPairedDeviceTitle);
        tvDeviceListNewDeviceTitle = (TextView) findViewById(R.id.tvDeviceListNewDeviceTitle);

        lvDeviceListPairedDevice = (ListView) findViewById(R.id.lvDeviceListPairedDevice);
        lvDeviceListNewDevice = (ListView) findViewById(R.id.lvDeviceListNewDevice);

        btnDeviceListScan = (Button) findViewById(R.id.btnDeviceListScan);

    }

    private void bindEventHandler() {
        lvDeviceListPairedDevice.setOnItemClickListener(mDeviceClickListener);
        lvDeviceListNewDevice.setOnItemClickListener(mDeviceClickListener);

        btnDeviceListScan.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startDiscovery();
                btnDeviceListScan.setVisibility(View.GONE);
            }
        });
    }

    private void initializeValues() {
        pairedDevicesArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.device_name);
        newDevicesArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.device_name);

        lvDeviceListPairedDevice.setAdapter(pairedDevicesArrayAdapter);
        lvDeviceListNewDevice.setAdapter(newDevicesArrayAdapter);

        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(discoveryFinishReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(discoveryFinishReceiver, filter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter
                .getBondedDevices();

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            tvDeviceListPairedDeviceTitle.setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                pairedDevicesArrayAdapter.add(device.getName() + "\n"
                        + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.none_paired)
                    .toString();
            pairedDevicesArrayAdapter.add(noDevices);
        }
    }

    private void startDiscovery() {
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scanning);

        tvDeviceListNewDeviceTitle.setVisibility(View.VISIBLE);

        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }

        bluetoothAdapter.startDiscovery();
    }

    private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            bluetoothAdapter.cancelDiscovery();

            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent intent = new Intent();
            intent.putExtra(DEVICE_ADDRESS, address);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

    private final BroadcastReceiver discoveryFinishReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    newDevicesArrayAdapter.add(device.getName() + "\n"
                            + device.getAddress());
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                if (newDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(
                            R.string.none_found).toString();
                    newDevicesArrayAdapter.add(noDevices);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
        this.unregisterReceiver(discoveryFinishReceiver);
    }

}
