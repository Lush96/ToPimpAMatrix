package com.mobiledev.topimpamatrix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.ejml.data.CDenseMatrix64F;
import org.ejml.ops.RandomMatrices;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by larspmayrand on 4/14/16.
 */
public class MainActivity extends AppCompatActivity {

    public  final static String SERIALIZABLE_KEY = "key";
    //    public static final int numCol = 3;
//    public static final int numRow = 3;
    public static int numCol;
    public static int numRow;
    private static final String TAG = "MainActivity";

    @Bind(R.id.row_spinner)
    Spinner mRowSpinner;

    @Bind(R.id.column_spinner)
    Spinner mColumnSpinner;

    @Bind(R.id.start_detailActivity_button)
    Button mCreateMatrixButton;

    private ArrayAdapter<Integer> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);


        /* Start DetailActivity for randomly generated complex matrix. */
//        double[][] array = new double[][]
//                {{Math.random() * 30 - 15, Math.random() * 30 - 15, Math.random() * 30 - 15, 0},
//                        {Math.random() * 30 - 15, Math.random() * 30 - 15, 0, Math.random() * 30 - 15}};
//        CDenseMatrix64F matrix = new CDenseMatrix64F(array);
        ButterKnife.bind(this);

        Integer[] items = new Integer[]{1,2,3,4,5};
        mAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_layout, items);
        mRowSpinner.setAdapter(mAdapter);
        mColumnSpinner.setAdapter(mAdapter);
    }

    @OnItemSelected(R.id.row_spinner)
    public void onRowSpinnerSelection(int pos) {
        numRow = mAdapter.getItem(pos);
    }

    @OnItemSelected(R.id.column_spinner)
    public void onColumnSpinnerSelection(int pos) {
        numCol = mAdapter.getItem(pos);
    }

    @OnClick(R.id.start_detailActivity_button)
    public void onMatrixButtonClicked() {
        if (numCol != numRow)
        {
            Toast.makeText(this, R.string.nonsquare_matrix_error, Toast.LENGTH_SHORT).show();
        }
        else {
            CDenseMatrix64F matrix = MatrixHelper.makeComplex(RandomMatrices.createOrthogonal(numRow, numCol, new Random()));
            Intent intent = new Intent(this, DetailActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable(SERIALIZABLE_KEY, matrix);
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    }


}
