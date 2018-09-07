package com.hongri.model.mvc.controller;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.hongri.model.R;
import com.hongri.model.mvc.model.MVCHttpRequestModel;
import com.hongri.model.mvc.model.MVCRequestCallback;

/**
 * @author hongri
 * @date 2018/9/4
 *
 * Controller层
 */
public class MVCActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRequest;
    private ImageView iv;
    private MVCHttpRequestModel mvcHttpRequestModel;
    private static final String requestUrl
        = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536307707406&di"
        + "=edafdb6ac08325cbabe1bf4fdb930cec&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn"
        + ".com%2F58pic%2F18%2F23%2F07%2F54F58PIC2yq_1024.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * xml文件属于理论上的View层
         */
        setContentView(R.layout.activity_mvcpattern);

        mvcHttpRequestModel = new MVCHttpRequestModel();

        btnRequest = findViewById(R.id.btnRequest);
        iv = findViewById(R.id.iv);
        btnRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRequest:

                /**
                 * Controller层将网络请求业务逻辑交由Model层处理
                 */
                mvcHttpRequestModel.onHttpRequest(requestUrl, new MVCRequestCallback() {

                    @Override
                    public void onSuccess(Object successData) {
                        if (successData != null) {
                            iv.setImageBitmap((Bitmap)successData);
                        }
                    }

                    @Override
                    public void onFailure(Object failureData) {
                        Toast.makeText(MVCActivity.this, failureData.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}
