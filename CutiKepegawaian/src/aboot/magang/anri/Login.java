package aboot.magang.anri;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import aboot.magang.anri.adapter.JSONParser;
import aboot.magang.anri.adapter.SessionManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{
	EditText txtUsername;
	EditText txtPassword;
	ProgressDialog pDialog ;
	Button btnLogin;
	JSONArray pegawai = null;
	SessionManager session;
	JSONParser jParser = new JSONParser();
	private static String url_peg = "http://cutikepegawaian.hol.es/get_pegawai.php";
	//private static String url_peg = "http://192.168.88.22/anri/get_pegawai.php";
	private static String TAG_SUKSES = "success";
	private static String TAG_PEG = "pegawai";
	private static String TAG_PIN = "pin";
	private static String TAG_NAMA = "nama";
	private static String TAG_LALU = "tahunlalu";
	private static String TAG_INI = "tahunini";
	private static String TAG_TOTAL = "total";
	private static String TAG_ROLE = "role";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		session = new SessionManager(getApplicationContext());
		txtUsername = (EditText)findViewById(R.id.txtuser);
		txtPassword = (EditText)findViewById(R.id.txtpass);
		btnLogin = (Button)findViewById(R.id.btnlogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String cuser = txtUsername.getText().toString();
				String cpass = txtPassword.getText().toString();
				if (cuser.matches("") || cpass.matches("")) {
					Toast.makeText(getApplicationContext(), "Field belum terisi", Toast.LENGTH_LONG).show();
				}else {
				new SeesionLogin().execute();
				}
			}
		});
	}
	class SeesionLogin extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Menghubung Ke Server");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... args) {
			String username = txtUsername.getText().toString();
			String password = txtPassword.getText().toString();
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			JSONObject json = jParser.makeHttpRequest(url_peg,"GET" , params);
			Log.e("Response: ", "> " + json);
			if (json !=null) {
			try {
				int sukses = json.getInt(TAG_SUKSES);
				if (sukses == 1) {
					pegawai = json.getJSONArray(TAG_PEG);
					for (int i = 0; i < pegawai.length(); i++) {
						JSONObject c = pegawai.getJSONObject(i);
						String pin = c.getString(TAG_PIN);
						String nama = c.getString(TAG_NAMA);
						String lalu = c.getString(TAG_LALU);
						String ini = c.getString(TAG_INI);
						String total = c.getString(TAG_TOTAL);
						String role = c.getString(TAG_ROLE);
					session.createLoginSession(pin, nama, lalu, ini, total, role);
						Intent in = new Intent(getApplicationContext(), Dashboard.class);
						in.putExtra(TAG_PIN, pin);
						in.putExtra(TAG_ROLE, role);
						startActivity(in);
						finish();
					}
				}else {
					runOnUiThread(new Runnable() {	
						@Override
						public void run() {
							Toast.makeText(getApplicationContext(), "Username / Password salah", Toast.LENGTH_LONG).show();
						}
					});
				}
			}catch(JSONException e) {
				e.printStackTrace();
			}
			}else {
				Log.e("JSON Data", "Tidak Dapat Terhubung ke Server!");
				runOnUiThread(new Runnable() {	
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "tidak Dapat terhubung Ke Server. Cek Koneksi Anda", Toast.LENGTH_LONG).show();
					}
				});
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	    	moveTaskToBack(true);
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
