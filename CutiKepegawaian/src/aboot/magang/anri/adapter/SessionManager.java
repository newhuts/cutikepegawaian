package aboot.magang.anri.adapter;

import java.util.HashMap;
import aboot.magang.anri.Login;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressLint("CommitPrefEdits")
public class SessionManager {
	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String PREF_NAME = "AndroidHivePref";
	private static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_PIN = "pin";
	public static final String KEY_NAMA = "nama";
	public static final String KEY_LALU = "tahunlalu";
	public static final String KEY_INI = "tahunini";
	public static final String KEY_TOTAL = "total";
	public static final String KEY_ROLE = "role";
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	public void createLoginSession(String pin, String nama, String lalu, String ini, String total, String role){
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_PIN, pin);
		editor.putString(KEY_NAMA, nama);
		editor.putString(KEY_LALU, lalu);
		editor.putString(KEY_INI, ini);
		editor.putString(KEY_TOTAL, total);
		editor.putString(KEY_ROLE, role);
		editor.commit();
	}	
	public void checkLogin(){
		if(!this.isLoggedIn()){
			Intent i = new Intent(_context, Login.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			_context.startActivity(i);
		}	
	}
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		user.put(KEY_PIN, pref.getString(KEY_PIN, null));
		user.put(KEY_NAMA, pref.getString(KEY_NAMA, null));
		user.put(KEY_LALU, pref.getString(KEY_LALU, null));
		user.put(KEY_INI, pref.getString(KEY_INI, null));
		user.put(KEY_TOTAL, pref.getString(KEY_TOTAL, null));
		return user;
	}
	public void logoutUser(){
		editor.clear();
		editor.commit();
		Intent i = new Intent(_context, Login.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		_context.startActivity(i);
	}
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}
}
