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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HistoriFragment extends Fragment{
	ProgressDialog pDialog ;
	public HistoriFragment() {}
	private static String URL_PER = "http://cutikepegawaian.hol.es/get_minta.php";
	//\private static String URL_PER = "http://192.168.88.22/anri/get_minta.php";
	private static String TAG_SUKSES = "success";
	private static String TAG_PER = "permintaan";
	private static String TAG_NC = "nama_cuti";
	private static String TAG_ID = "id";
	private static String TAG_NAMA = "nama";
	private static String TAG_TGL_PER = "tgl_permintaan";
	private static String TAG_AWAL = "tgl_awal";
	private static String TAG_AKHIR = "tgl_akhir";
	private static String TAG_JN = "jenis_cuti";
	private static String TAG_STAT = "status";
	JSONParser jParser = new JSONParser();
	JSONArray permintaan = null;
	ArrayList<HashMap<String, String>> arraylist;
	ListView listview;
    ListAdapter adapter;
	SessionManager session;
	String pin;
	ListView listhis;
	TextView txtpin, txtnama,txtlalu,txtini,txttotal;
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        session = new SessionManager(activity);
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        View rootView = inflater.inflate(R.layout.history_layout, container, false);
        txtpin = (TextView)rootView.findViewById(R.id.pinsession);
        HashMap<String, String> user = session.getUserDetails();
        String pinn = user.get(SessionManager.KEY_PIN);
        txtpin.setText(pinn);
        txtpin.setVisibility(View.GONE);
        new historiData().execute();
        listhis = (ListView)rootView.findViewById(R.id.listhistori);
        listhis.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String jeen = ((TextView)view.findViewById(R.id.idpermintaan)).getText().toString();
				Intent inol = new Intent(getActivity(), DetailFragment.class);
				inol.putExtra(TAG_ID, jeen);
				startActivityForResult(inol, 100);
			}
		});
        return rootView;
    }
	class historiData extends AsyncTask<Void, Void, Void>{
		protected void onPreExecute() {
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Meminta Data ....");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... args) {
			String setpin = txtpin.getText().toString();
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pin", setpin));
			arraylist = new ArrayList<HashMap<String, String>>();
			JSONObject json = jParser.makeHttpRequest(URL_PER, "GET", params);
			Log.e("Response: ", "> " + json);
			if (json !=null) {
			try {
				int sukses = json.getInt(TAG_SUKSES);
				if (sukses == 1) {
					permintaan = json.getJSONArray(TAG_PER);
					for (int i = 0; i < permintaan.length(); i++) {
						JSONObject c = permintaan.getJSONObject(i);
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(TAG_ID, c.getString(TAG_ID));
						map.put(TAG_NAMA, c.getString(TAG_NAMA));
						map.put(TAG_NC, c.getString(TAG_NC));
						map.put(TAG_TGL_PER, c.getString(TAG_TGL_PER));
						map.put(TAG_AWAL, c.getString(TAG_AWAL));
						map.put(TAG_AKHIR, c.getString(TAG_AKHIR));
						map.put(TAG_JN, c.getString(TAG_JN));
						map.put(TAG_STAT, c.getString(TAG_STAT));
						arraylist.add(map);
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
		protected void onPostExecute(Void args) {
			if (pDialog.isShowing())
				pDialog.dismiss();
			ListView listv = (ListView)getActivity().findViewById(R.id.listhistori);
			ListAdapter adapter = new SimpleAdapter(
					getActivity(), arraylist,
					R.layout.listview, new String[] { TAG_ID,TAG_NC,
					TAG_NAMA, TAG_TGL_PER,TAG_AWAL,TAG_AKHIR,TAG_JN, TAG_STAT},
					new int[] {R.id.idpermintaan, R.id.nce, R.id.tnamaee, R.id.tperee, R.id.tawale,
					R.id.takhire, R.id.jce, R.id.statuse });
			listv.setAdapter(adapter);
		}
		
	}
}