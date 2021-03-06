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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StockBuyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StockBuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockBuyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static Stock selectedStock;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private Button mSubmit;
    private Trade mTrade;
    private EditText mSymbol;
    private EditText mQuantity;
    private EditText mPrice;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StockBuyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StockBuyFragment newInstance(String param1, String param2) {
        StockBuyFragment fragment = new StockBuyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StockBuyFragment() {
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
        mView = inflater.inflate(R.layout.fragment_stock_buy, container, false);

        initView();

        return mView;
    }

    private void initView() {
        mQuantity = (EditText) mView.findViewById(R.id.quantityET);
        mPrice = (EditText) mView.findViewById(R.id.priceET);
        mSymbol = (EditText) mView.findViewById(R.id.symbolET);

        mSymbol.setText(selectedStock.getSymbol());

        mSubmit = (Button) mView.findViewById(R.id.submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTrade == null) {
                    mTrade = new Trade();
                }

                mTrade.quantity = Integer.parseInt(mQuantity.getText().toString());
                mTrade.price = Double.parseDouble(mPrice.getText().toString());
                mTrade.stock = selectedStock;
                mTrade.dateTime = NowUtil.now();

                mTrade.save();

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
