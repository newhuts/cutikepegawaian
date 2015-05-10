package aboot.magang.anri;

import aboot.magang.anri.adapter.SessionManager;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Logout extends Fragment{
	SessionManager session;
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        session = new SessionManager(activity);
    }
	public Logout() {}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lihatcuti_layout, container, false);
        session.logoutUser();
        return rootView;
    }
}