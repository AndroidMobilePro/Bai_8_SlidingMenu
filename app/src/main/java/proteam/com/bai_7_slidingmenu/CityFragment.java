package proteam.com.bai_7_slidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //lay doi so co key la "position" (ben ngoai truyen vo) chinh la item
        //tren listview duoc chon
        int pos = getArguments().getInt("position");
        String[] listCities = getResources().getStringArray(R.array.thanhpho);
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_content);
        tv.setText(listCities[pos]);

        // set Title of actionBar
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(listCities[pos]);

        return view;
    }
}
