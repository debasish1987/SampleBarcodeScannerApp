package com.deb.barcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Debasish
 * Main Activity to launch scanning screen
 */
public class MainActivity extends Activity {

	EditText barcodeET;
	Button scanBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		barcodeET = (EditText) findViewById(R.id.barcodeET);
		scanBtn = (Button) findViewById(R.id.scanBtn);

		scanBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isCameraAvailable()) {
					Intent intent = new Intent(MainActivity.this,
							ScannerActivity.class);
					intent.putExtra("TYPE", "0");
					startActivityForResult(intent, 1);

				} else {
					Toast.makeText(MainActivity.this,
							R.string.Rear_Facing_Camera_Unavailable,
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case 1:

			if (data != null) {
				String result = data.getExtras().getString("result");
				barcodeET.setText(result);
			} else {

			}
			break;

		default:
			break;
		}
	}

	private boolean isCameraAvailable() {
		PackageManager pm = getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

}
