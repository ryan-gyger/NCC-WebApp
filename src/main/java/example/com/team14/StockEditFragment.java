package example.com.team14;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StockEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StockEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockEditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static Stock selectedStock = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private EditText mSymbol;
    private EditText mDesc;
    private EditText mPrice;
    private Button mSubmitBtn;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StockEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StockEditFragment newInstance(String param1, String param2) {
        StockEditFragment fragment = new StockEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StockEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_stock_edit, container, false);

        initView();

        return mView;
    }

    private void initView() {
        mSymbol = (EditText) mView.findViewById(R.id.symbolET);
        mDesc = (EditText) mView.findViewById(R.id.descET);
        mPrice = (EditText) mView.findViewById(R.id.priceET);

        if (selectedStock != null) {
            mSymbol.setText(selectedStock.getSymbol());
            mDesc.setText(selectedStock.getDesc());
            mPrice.setText(String.valueOf(selectedStock.getPrice()));
        } else {
            selectedStock = new Stock();
            selectedStock.dateTime = NowUtil.now();
        }

        mSubmitBtn = (Button) mView.findViewById(R.id.submit);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedStock.symbol = mSymbol.getText().toString();
                selectedStock.desc = mDesc.getText().toString();
                selectedStock.price = Double.parseDouble(mPrice.getText().toString());

                selectedStock.save();

                StockDetailedFragment.selectedStock = selectedStock;
                selectedStock = null;
                mListener.changeFragment(MainActivity.STOCKDETAILEDFRAGMENT);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        public void changeFragment(int position);
    }

}
