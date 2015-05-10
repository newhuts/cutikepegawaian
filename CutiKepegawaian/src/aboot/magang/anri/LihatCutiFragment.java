package aboot.magang.anri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import aboot.magang.anri.adapter.JSONParser;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LihatCutiFragment extends Fragment{
	ProgressDialog pDialog ;
	JSONParser jParser = new JSONParser();
	JSONArray terima = null;
	ArrayList<HashMap<String, String>>terimalist;
	private static String URL_TERIMA = "http://cutikepegawaian.hol.es/terima_permintaan.php";
	//private static String URL_TERIMA = "http://192.168.88.22/anri/terima_permintaan.php";
	private static String TAG_SUKSES = "success";
	private static String TAG_PER = "permintaan";
	private static String TAG_ID = "id";
	private static String TAG_PIN = "pin";
	private static String TAG_NC = "nama_cuti";
	private static String TAG_NAMA = "nama";
	private static String TAG_TGL_PER = "tgl_permintaan";
	private static String TAG_AWAL = "tgl_awal";
	private static String TAG_AKHIR = "tgl_akhir";
	private static String TAG_JN = "jenis_cuti";
	private static String TAG_KET = "ket";
	private static String TAG_STAT = "status";
	String role;
	ListView listhis;
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lihatcuti_layout, container, false);
        new LoadPermintaan().execute();
        listhis = (ListView)rootView.findViewById(R.id.listhistori);
        listhis.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String jeen = ((TextView)view.findViewById(R.id.idpermintaan)).getText().toString();
				Intent inol = new Intent(getActivity(), DetailFragment.class);
				inol.putExtra(TAG_ID, jeen);
				startActivityForResult(inol, 100);
			}
		});
        return rootView;
    }
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 100) {
			Intent intent = getActivity().getIntent();
			getActivity().finish();
			startActivity(intent);
		}
	}
	
	class LoadPermintaan extends AsyncTask<String, String, String>{
		protected void onPreExecute() {
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Meminta Data ....");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			JSONObject json = jParser.makeHttpRequest(URL_TERIMA, "GET", params);
			terimalist = new ArrayList<HashMap<String, String>>();
			Log.e("Response: ", "> " + json);
			if (json !=null) {
			try {
				int success = json.getInt(TAG_SUKSES);
				if (success == 1) {
					terima = json.getJSONArray(TAG_PER);
					for (int i = 0; i < terima.length(); i++) {
						JSONObject c = terima.getJSONObject(i);
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(TAG_ID, c.getString(TAG_ID));
						map.put(TAG_PIN, c.getString(TAG_PIN));
						map.put(TAG_NAMA, c.getString(TAG_NAMA));
						map.put(TAG_NC, c.getString(TAG_NC));
						map.put(TAG_TGL_PER, c.getString(TAG_TGL_PER));
						map.put(TAG_AWAL, c.getString(TAG_AWAL));
						map.put(TAG_AKHIR, c.getString(TAG_AKHIR));
						map.put(TAG_JN, c.getString(TAG_JN));
						map.put(TAG_KET, c.getString(TAG_KET));
						map.put(TAG_STAT, c.getString(TAG_STAT));
						terimalist.add(map);					
					}
				}
			}catch(JSONException e) {
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
		protected void onPostExecute(String args) {
			if (pDialog.isShowing())
				pDialog.dismiss();
			ListView listv = (ListView)getActivity().findViewById(R.id.listhistori);
			ListAdapter adapter = new SimpleAdapter(
					getActivity(), terimalist,
					R.layout.listview, new String[] { TAG_ID, TAG_NC,
							TAG_NAMA, TAG_TGL_PER,TAG_AWAL,TAG_AKHIR,TAG_JN, TAG_STAT},
					new int[] {R.id.idpermintaan, R.id.nce, R.id.tnamaee, R.id.tperee, R.id.tawale,
						R.id.takhire, R.id.jce, R.id.statuse });
			listv.setAdapter(adapter);	
		}
	}
}