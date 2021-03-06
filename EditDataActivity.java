package com.mobiledev.topimpamatrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import org.ejml.data.CDenseMatrix64F;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Garrett on 4/18/2016.
 */
public class EditDataActivity extends Activity {
    //Should allow user to edit the data in the grid space clicked, then updates all matrix info


    @Bind(R.id.grid_view)
    GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.keyboard_activity);

        ButterKnife.bind(this);

        CDenseMatrix64F mMatrix = (CDenseMatrix64F) getIntent().getSerializableExtra(MainActivity.SERIALIZABLE_KEY);
        mMatrix.print();

        GridViewCustomAdapter mAdapter = new GridViewCustomAdapter(this, mMatrix);
        mGridView.setAdapter(mAdapter);
        mGridView.setNumColumns(MainActivity.numCol);

//        mMatrixGridItem.setInputType(InputType.TYPE_CLASS_NUMBER);
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput((mMatrixGridItem), InputMethodManager.SHOW_IMPLICIT);
    }

    @OnClick(R.id.new_matrix_button)
    public void newMatrixButtonClicked() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
