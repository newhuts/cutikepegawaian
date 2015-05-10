package aboot.magang.anri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import aboot.magang.anri.adapter.JSONParser;
import aboot.magang.anri.adapter.SessionManager;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BuatCutiFragment extends Fragment {
	EditText edit;
	EditText editt;
	EditText jmlh;
	EditText kett;
	Button kirim;
	TextView selek;
	TextView state;
	Button hitungs;
	Spinner spinn;
	TextView vieww;
	TextView txtpino;
	String year, month, day;
	SessionManager session;
	String hasilnya;
	String hasil;
	ProgressDialog pDialog ;
	JSONParser jsonParser = new JSONParser();
	JSONArray hitun = null;
	ArrayList<HashMap<String, String>> arraylist;
	private static String TAG_SUKSES = "success";
	private static String URL_BUAT = "http://cutikepegawaian.hol.es/buat_permintaan.php";
	private static String URL_HITUNG = "http://cutikepegawaian.hol.es/get_libur.php";
	//private static String URL_BUAT = "http://192.168.88.22/anri/buat_permintaan.php";
	//private static String URL_HITUNG = "http://192.168.88.22/anri/get_libur.php";
	private static String TAG_ID = "id";
	private static String TAG_PER = "permintaan";
	String pin;
	TextView hak;
	Calendar awalcal= Calendar.getInstance();
	Calendar akhircal = Calendar.getInstance();
	public BuatCutiFragment() {}
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        session = new SessionManager(activity);
	    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.buatcuti_layout, container, false);
		txtpino = (TextView)rootView.findViewById(R.id.pinteres);
		Spinner spinner = (Spinner)rootView.findViewById(R.id.spinner1);
		edit = (EditText)rootView.findViewById(R.id.tglmulai);
		editt = (EditText)rootView.findViewById(R.id.tglselesai);
		selek = (TextView)rootView.findViewById(R.id.pinsesion);
		jmlh = (EditText)rootView.findViewById(R.id.jmlhari);
		hitungs = (Button)rootView.findViewById(R.id.hitunggs);
		kett = (EditText)rootView.findViewById(R.id.kete);
		state = (TextView)rootView.findViewById(R.id.state);
		HashMap<String, String> user = session.getUserDetails();
		 String pinn = user.get(SessionManager.KEY_PIN);
		 txtpino.setText(pinn);
		 txtpino.setVisibility(View.GONE);
		state.setText("Pending");
		state.setVisibility(View.GONE);
		kirim = (Button)rootView.findViewById(R.id.kirim);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				 Log.i("cyberwalker", "item selected" + position);
				 selek.setText(""+position);
				 selek.setVisibility(View.GONE);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity(), "Mohon pilih jenis cuti", Toast.LENGTH_LONG).show();
			}
		});
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
					R.array.cuti_array, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
			
		final DatePickerDialog.OnDateSetListener awal = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
				awalcal.set(Calendar.YEAR, year);
				awalcal.set(Calendar.MONTH, monthOfYear);
				awalcal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateAwal();
			}
			private void updateAwal() {
				String formatawal = "yyyy-MM-dd"; //In which you need put here
				SimpleDateFormat sdfawal = new SimpleDateFormat(formatawal, Locale.US);
				edit.setText(sdfawal.format(awalcal.getTime()));
			}				
		};
        edit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 new DatePickerDialog(getActivity(), awal, 
                		 awalcal.get(Calendar.YEAR), awalcal.get(Calendar.MONTH),
                         awalcal.get(Calendar.DAY_OF_MONTH)).show();
             }
         });
        
        final DatePickerDialog.OnDateSetListener akhir = new DatePickerDialog.OnDateSetListener() {
    		@Override
    		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
    			akhircal.set(Calendar.YEAR, year);
                akhircal.set(Calendar.MONTH, monthOfYear);
                akhircal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateAkhir();
    		}
    		private void updateAkhir() {
    			String formatakhir = "yyyy-MM-dd"; //In which you need put here
    	        SimpleDateFormat sdfakhir = new SimpleDateFormat(formatakhir, Locale.US);
    	         editt.setText(sdfakhir.format(akhircal.getTime()));
    		}	
         };
        editt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), akhir, akhircal.get(Calendar.YEAR), 
                		akhircal.get(Calendar.MONTH),
                        akhircal.get(Calendar.DAY_OF_MONTH)).show();
                new Hitungtanggal().execute();   
            }
        });
        jmlh.setEnabled(false);
        hitungs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Hitungtanggal().execute();
			}
		});
        kirim.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String setspin = selek.getText().toString();
				if (setspin.equals("0")) {
					Toast.makeText(getActivity(), "pilih jenis cuti", Toast.LENGTH_LONG).show();
				}else {
					//Toast.makeText(getActivity(), "selamat", Toast.LENGTH_LONG).show();
					new CreatePermintaan().execute();
				}
			}
		});
        return rootView;
    }
	class Hitungtanggal extends AsyncTask<String, Void, String>{
		protected void onPreExecute() {
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Menghitung Data ....");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			String hariawali = edit.getText().toString();
			String hariakhiri = editt.getText().toString();
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("tgl_awal", hariawali));
			params.add(new BasicNameValuePair("tgl_akhir", hariakhiri));
			arraylist = new ArrayList<HashMap<String, String>>();
			JSONObject json = jsonParser.makeHttpRequest(URL_HITUNG, "GET", params);
			Log.e("Response: ", "> " + json);
			if (json !=null) {
			try {
				int sukses = json.getInt(TAG_SUKSES);
				if (sukses == 1) {
					hitun = json.getJSONArray(TAG_PER);
					for (int i = 0; i < hitun.length(); i++) {
						JSONObject c = hitun.getJSONObject(i);
						final int hasilnyah = c.getInt(TAG_ID);
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								jmlh.setText(""+hasilnyah);
							}
						});
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
		}
	}
	
	class CreatePermintaan extends AsyncTask<Void, Void, Void>{
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Mengirim Data ....");
			pDialog.setCancelable(false);
			pDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					pDialog.dismiss();
				}
			});
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... args) {
			String pino = txtpino.getText().toString();
			String seleksi = selek.getText().toString();
			String hariawal = edit.getText().toString();
			String hariakhir = editt.getText().toString();
			String jmlhari = jmlh.getText().toString();
			String ketee = kett.getText().toString();
			String statu = state.getText().toString();
			List<NameValuePair>params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pin", pino));
			params.add(new BasicNameValuePair("id_cuti", seleksi));
			params.add(new BasicNameValuePair("tgl_awal", hariawal));
			params.add(new BasicNameValuePair("tgl_akhir", hariakhir));
			params.add(new BasicNameValuePair("jml_hari", jmlhari));
			params.add(new BasicNameValuePair("ket", ketee));
			params.add(new BasicNameValuePair("status", statu));
			JSONObject json = jsonParser.makeHttpRequest(URL_BUAT, "POST", params);
			Log.e("Response: ", "> " + json);
			if (json !=null) {
			try {
				int sukses = json.getInt(TAG_SUKSES);
				if (sukses == 1) {
					Intent in = new Intent(getActivity(), Dashboard.class);
					startActivity(in);
				}else {
					Toast.makeText(getActivity(), "ada kesalahan", Toast.LENGTH_LONG).show();
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
		protected void onPostExecute(String file_url) {
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}
}