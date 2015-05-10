package aboot.magang.anri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import aboot.magang.anri.adapter.JSONParser;
import aboot.magang.anri.adapter.SessionManager;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Activity{
	String idper;
	private static final String TAG_ID = "id";
	//private static final String URL_DET = "http://192.168.88.22/anri/get_detailpermintaan.php";
	//private static final String URL_UPDATE = "http://192.168.88.22/anri/update_permintaan.php";
	private static final String URL_DET = "http://cutikepegawaian.hol.es/get_detailpermintaan.php";
	private static final String URL_UPDATE = "http://cutikepegawaian.hol.es/update_permintaan.php";
	private static String TAG_SUKSES = "success";
	private static String TAG_PER = "permintaan";
	private static String TAG_PIN = "pin";
	private static String TAG_NAMA = "nama";
	private static String TAG_LALU = "tahunlalu";
	private static String TAG_INI = "tahunini";
	private static String TAG_TOTAL = "total";
	private static String TAG_TGLPER = "tgl_permintaan";
	private static String TAG_MULAI = "tgl_awal";
	private static String TAG_AKHIR = "tgl_akhir";
	private static String TAG_JML = "jml_hari";
	private static String TAG_JNS = "nama_cuti";
	private static String TAG_IDCUT = "id_cuti";
	private static String TAG_KET = "ket";
	JSONParser jParser = new JSONParser();
	TextView txtid, txtnama, txtper, txtmulai, txtakhir, txtjmlh, txtjns, txtket;
	RelativeLayout hilangk;
	TextView txtidcutil, txtlalu, txtini, txttotal;
	TextView txthlalu, txthini, txthtotal;
	Button asep;
	String pintet;
	ProgressDialog pDialog ;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.ic_ab_back_holo_dark_am);
		Intent inol = getIntent();
		idper = inol.getStringExtra(TAG_ID);
		new panggilDetailPermintaan().execute();
		asep = (Button)findViewById(R.id.btnaccept);
		hilangk = (RelativeLayout)findViewById(R.id.btnx);
		SessionManager session = new SessionManager(getApplicationContext());
		 HashMap<String,String>user = session.getUserDetails();
	        pintet = user.get(SessionManager.KEY_PIN);
	        hilangk.setVisibility(View.GONE);
		asep.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new UpdateStatus().execute();
			}
		});
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
	class panggilDetailPermintaan extends AsyncTask<String, String, String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DetailFragment.this);
			pDialog.setMessage("Meminta Data....");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", idper));
			JSONObject json = jParser.makeHttpRequest(URL_DET, "GET", params);
			Log.e("Response: ", "> " + json);
			if (json !=null) {
			try {
			int sukses = json.getInt(TAG_SUKSES);
			if (sukses == 1) {
				JSONArray detailObj = json.getJSONArray(TAG_PER);
				JSONObject detail = detailObj.getJSONObject(0);
				txtid = (TextView)findViewById(R.id.pindet);
				txtnama = (TextView)findViewById(R.id.namadet);
				txtper = (TextView)findViewById(R.id.tglperdet);
				txtmulai = (TextView)findViewById(R.id.mulaidet);
				txtakhir = (TextView)findViewById(R.id.akhirdet);
				txtjmlh = (TextView)findViewById(R.id.jmlhdet);
				txtlalu = (TextView)findViewById(R.id.txtlalu);
				txtini = (TextView)findViewById(R.id.txtini);
				txttotal = (TextView)findViewById(R.id.txttotal);
				txtjns = (TextView)findViewById(R.id.namajenisdet);
				txtket = (TextView)findViewById(R.id.ketdet);
				txtidcutil = (TextView)findViewById(R.id.jmlh);
				txthlalu = (TextView)findViewById(R.id.txthlalu);
				txthini = (TextView)findViewById(R.id.txthini);
				txthtotal = (TextView)findViewById(R.id.txthtot);
				final String pindet = detail.getString(TAG_PIN);
				final String namadet = detail.getString(TAG_NAMA);
				final String perdet = detail.getString(TAG_TGLPER);
				final String mulaidet = detail.getString(TAG_MULAI);
				final String akhirdet = detail.getString(TAG_AKHIR);
				final String jmldet = detail.getString(TAG_JML);
				final String thnlalu = detail.getString(TAG_LALU);
				final String thnini = detail.getString(TAG_INI);
				final String totalis = detail.getString(TAG_TOTAL);
				final String jnsdet = detail.getString(TAG_JNS);
				final String cutidi = detail.getString(TAG_IDCUT);
				final String ketdeti = detail.getString(TAG_KET);
				
				runOnUiThread(new Runnable() {
					public void run() {
						txtid.setText(""+pindet);
						txtnama.setText(""+namadet);
						txtper.setText(""+perdet);
						txtmulai.setText(""+mulaidet);
						txtakhir.setText(""+akhirdet);
						txtjmlh.setText(""+jmldet);
						txtjns.setText(""+jnsdet);
						txtidcutil.setText(""+cutidi);
						txtlalu.setText(""+thnlalu);
						txtini.setText(""+thnini);
						txttotal.setText(""+totalis);
						txtidcutil.setVisibility(View.GONE);
						txtket.setText(""+ketdeti);
						if (cutidi.equals("4")) {
							int lalu = Integer.parseInt(""+thnlalu);
							int ini = Integer.parseInt(""+thnini);
							int jml = Integer.parseInt(""+jmldet);
							int temp,laluhasil, inihasil,totalhasil, totalin;
							if (lalu > 0) {
								temp = lalu - jml;
								if (temp > 0) {
									laluhasil = temp;
									inihasil = ini ;
									totalhasil = laluhasil + inihasil;
									txthlalu.setText(Integer.toString(laluhasil));
									txthini.setText(Integer.toString(inihasil));
									txthtotal.setText(Integer.toString(totalhasil));
								}else if (temp <= 0) {
									totalin = temp + ini;
									laluhasil = 0;
									totalhasil = totalin;
									inihasil = totalin;
									txthlalu.setText(Integer.toString(laluhasil));
									txthini.setText(Integer.toString(inihasil));
									txthtotal.setText(Integer.toString(totalhasil));
								}
							}else if (lalu == 0 ) {
								temp = ini - jml;
								inihasil = temp;
								totalhasil = temp;
								laluhasil = 0;
								txthlalu.setText(Integer.toString(laluhasil));
								txthini.setText(Integer.toString(inihasil));
								txthtotal.setText(Integer.toString(totalhasil));
							}else if (lalu < 0) {
								temp = ini + lalu;
								laluhasil = 0;
								inihasil = temp - jml;
								totalhasil = inihasil;
								txthlalu.setText(Integer.toString(laluhasil));
								txthini.setText(Integer.toString(inihasil));
								txthtotal.setText(Integer.toString(totalhasil));		
							}
						}else{
							txthlalu.setText(""+thnlalu);
							txthini.setText(""+thnini);
							txthtotal.setText(""+totalis);
						}
						txthlalu.setVisibility(View.GONE);
						txthini.setVisibility(View.GONE);
						txthtotal.setVisibility(View.GONE);
						txtlalu.setVisibility(View.GONE);
						txtini.setVisibility(View.GONE);
						txttotal.setVisibility(View.GONE);
						if (pintet.equals("12345")) {
							hilangk.setVisibility(View.VISIBLE);
						}
					}
				});
				}
			}catch(JSONException e) {
				e.printStackTrace();
			}
			}else {
				runOnUiThread(new Runnable() {	
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "tidak Dapat terhubung Ke Server. Cek Koneksi Anda", Toast.LENGTH_LONG).show();
					}
				});
			}
			return null;
		}
		protected void onPostExecute(String file_url) {
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}
	class UpdateStatus extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DetailFragment.this);
			pDialog.setMessage("Mengirim Data ...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			String hasildarilalu = txthlalu.getText().toString();
			String hasildaritahunini = txthini.getText().toString();
			String hasildaritotal = txthtotal.getText().toString();
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", idper));
			params.add(new BasicNameValuePair("tahunlalu", hasildarilalu));
			params.add(new BasicNameValuePair("tahunini", hasildaritahunini));
			params.add(new BasicNameValuePair("total", hasildaritotal));
			JSONObject json = jParser.makeHttpRequest(URL_UPDATE, "POST", params);
			Log.d("detailnya user", json.toString());
			try {
			int sukses = json.getInt(TAG_SUKSES);
			if (sukses == 1) {
				Intent i = getIntent();
				setResult(100, i);
				finish();
			}
		}catch(JSONException e) {
			e.printStackTrace();
		}
			return null;
		}
	}
}