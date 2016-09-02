package foundation.chinaredstar.com.demos.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chinaredstar.foundation.common.utils.HttpUtil;
import com.chinaredstar.foundation.common.utils.LogUtil;
import com.chinaredstar.foundation.common.utils.ToastUtil;
import com.chinaredstar.foundation.common.utils.http.HttpConnectException;
import com.chinaredstar.foundation.interaction.bean.Result;

import java.util.HashMap;
import java.util.Map;

import foundation.chinaredstar.com.demos.R;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        findViewById(R.id.testGetHttp).setOnClickListener(this);
        findViewById(R.id.testPostHttp).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int method = HttpUtil.Method.GET;
        Map<String, String> params = new HashMap<>();
        params.put("openId", "1baa2e5c-af03-44d3-ac8b-76a835d1ba1f");
        params.put("commentType", "booking_designer");
        String url = "http://10.11.25.130:8080/b/comments/commentList";

        switch (view.getId()) {
            case R.id.testGetHttp:
                method = HttpUtil.Method.GET;
                url = "http://10.11.25.130:8080/b/comments/commentDetail/201608190200100029";
                params.clear();
//                params.put("openId", "1baa2e5c-af03-44d3-ac8b-76a835d1ba1f");
//                params.put("commentType", "booking_designer");
                break;
            case R.id.testPostHttp:
                method = HttpUtil.Method.POST;
                url = "http://10.11.25.130:8080/b/comments/replyComment";
                params.clear();
                params.put("openId", "1baa2e5c-af03-44d3-ac8b-76a835d1ba1f");
                params.put("commentId", "55");
                params.put("replyContent", "replyContentAAAAAAAAA");
                break;
        }
        HttpUtil.getData("TAG", method, url,params, String.class, new HttpUtil.Callback<String>() {
            @Override
            public void onSuccess(String data) {
                LogUtil.d("onSuccess = " + data);
                ToastUtil.showToast("onSuccess = " + data);
            }

            @Override
            public void onFailure(Result<Object> result) {
                LogUtil.d("onFailure = " + result.toString());
                ToastUtil.showToast("onFailure = " + result.toString());
            }

            @Override
            public void onError(HttpConnectException error) {
                LogUtil.d("onError = " + error.toString());
                ToastUtil.showToast("onError = " + error.toString());
            }
        });
    }
}
