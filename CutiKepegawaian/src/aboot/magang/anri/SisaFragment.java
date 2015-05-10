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
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SisaFragment extends Fragment{
	String pin;
	ProgressDialog pDialog ;
	JSONParser jParser = new JSONParser();
	private static String TAG_SUKSES = "success";
	private static String TAG_PEG = "pegawai";
	private static String TAG_NAMA = "nama";
	private static String TAG_LALU = "tahunlalu";
	private static String TAG_INI = "tahunini";
	private static String TAG_TOTAL = "total";
	private static String URL_USER = "http://cutikepegawaian.hol.es/get_userdetail.php";
	//private static String URL_USER = "http://192.168.88.22/anri/get_userdetail.php";
	SessionManager session;
	TextView txtpin, txtnama,txtlalu,txtini,txttotal;
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        session = new SessionManager(activity);
    }
	public SisaFragment() {}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {	
        View rootView = inflater.inflate(R.layout.sisacuti_layout, container, false);
        txtpin = (TextView)rootView.findViewById(R.id.pin);
        txtnama = (TextView)rootView.findViewById(R.id.nama);
        txtlalu = (TextView)rootView.findViewById(R.id.thnlalu);
        txtini = (TextView)rootView.findViewById(R.id.thnini);
        txttotal = (TextView)rootView.findViewById(R.id.thntotaal);
        HashMap<String,String>user = session.getUserDetails();
        pin = user.get(SessionManager.KEY_PIN);
        txtpin.setText(pin);
        new userDetail().execute();
        return rootView;
    }
	class userDetail extends AsyncTask<String, String, String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Meminta Data ....");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pin", pin));
			JSONObject json = jParser.makeHttpRequest(URL_USER, "GET", params);
			Log.e("Response: ", "> " + json);
			if (json !=null) {
				try {
					int sukses = json.getInt(TAG_SUKSES);
					if (sukses == 1) {
						JSONArray detailObj = json.getJSONArray(TAG_PEG);
						JSONObject detail = detailObj.getJSONObject(0);
						final String nama = detail.getString(TAG_NAMA);
						final String lalu = detail.getString(TAG_LALU);
						final String ini = detail.getString(TAG_INI);
						final String total = detail.getString(TAG_TOTAL);
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								txtpin.setText(pin);
								txtnama.setText(nama);
								txtlalu.setText(lalu);
								txtini.setText(ini);
								txttotal.setText(total);
							}
						});
					}
					}catch (JSONException e) {
						e.printStackTrace();
					}
			}else {
				Log.e("JSON Data", "Tidak Dapat Terhubung ke Server!");
				getActivity().runOnUiThread(new Runnable() {	
					@Override
					public void run() {
						Toast.makeText(getActivity(), "tidak Dapat terhubung Ke Server. Cek Koneksi Anda", Toast.LENGTH_LONG).show();
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
}