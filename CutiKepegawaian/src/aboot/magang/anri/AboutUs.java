package aboot.magang.anri;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class AboutUs extends Activity{
	TextView txtket;
	Button btnabout;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		txtket = (TextView)findViewById(R.id.des);
		String deskripsi = "Aplikasi ini sengaja dibuat untuk menyelesaikan dan melengkapi laporan KKP (Kuliah Kerja Praktek ) di Arsip Nasional Republik Indonesia";
		txtket.setText(""+deskripsi);
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
            Intent intent = new Intent(this, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
