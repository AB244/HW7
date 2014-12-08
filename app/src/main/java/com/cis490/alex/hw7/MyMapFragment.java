package com.cis490.alex.hw7;

import android.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 12/8/2014.
 */
public class MyMapFragment extends com.google.android.gms.maps.MapFragment{

    private GoogleMap mMap;

    @Override
    public void onStart(){
        super.onStart();
        initMap();
    }

    private void initMap()
    {
        mMap = ((MyMapFragment) getFragmentManager().findFragmentById(R.id.MapsContainer)).getMap();

        ArrayList<Schools> schoolsList = new ArrayList<Schools>();

        Schools UofL = new Schools();
        UofL.setName("University of Louisville");
        UofL.setAbrev("UL");
        UofL.setLatitude(38.215071);
        UofL.setLongitude(-85.759841);
        schoolsList.add(UofL);

        Schools UofK = new Schools();
        UofK.setName("University of Kentucky");
        UofK.setAbrev("UK");
        UofK.setLatitude(38.030710);
        UofK.setLongitude(-84.503820);
        schoolsList.add(UofK);

        Schools Spald = new Schools();
        Spald.setName("Spalding University");
        Spald.setAbrev("SU");
        Spald.setLatitude(38.241477);
        Spald.setLongitude(-85.757313);
        schoolsList.add(Spald);

        Schools IUS = new Schools();
        IUS.setName("Indiana University Southeast");
        IUS.setAbrev("IUS");
        IUS.setLatitude(38.346031);
        IUS.setLongitude(-85.817009);
        schoolsList.add(IUS);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (Schools school : schoolsList) {
            LatLng latLong = new LatLng(school.getLatitude(), school.getLongitude());

            MarkerOptions marker = new MarkerOptions();
            marker.title(school.getName());
            marker.position(latLong);
            marker.snippet(school.getAbrev());
            mMap.addMarker(marker);
            builder.include(marker.getPosition());
        }

        //Prevent the Map cannot be zero error
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),
                this.getResources().getDisplayMetrics().widthPixels,
                this.getResources().getDisplayMetrics().heightPixels,
                20));
    }
}
