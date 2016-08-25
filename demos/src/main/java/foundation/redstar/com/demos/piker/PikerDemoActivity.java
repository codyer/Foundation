package foundation.redstar.com.demos.piker;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.redstar.foundation.ui.widget.pickerview.OptionsPickerView;
import com.redstar.foundation.ui.widget.pickerview.TimePickerView;
import com.redstar.foundation.ui.widget.pickerview.model.IPickerViewData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import foundation.redstar.com.demos.R;
import foundation.redstar.com.demos.piker.bean.PickerViewData;
import foundation.redstar.com.demos.piker.bean.ProvinceBean;


public class PikerDemoActivity extends Activity {

    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<IPickerViewData>>> options3Items = new ArrayList<>();
    private TextView tvTime1,tvTime2,tvTime3, tvOptions;
    TimePickerView pvTime1,pvTime2,pvTime3;
    OptionsPickerView pvOptions;
//    View vMasker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piker_widget_demo);
//        vMasker=findViewById(R.id.vMasker);
        tvTime1=(TextView) findViewById(R.id.tvTime1);
        tvTime2=(TextView) findViewById(R.id.tvTime2);
        tvTime3=(TextView) findViewById(R.id.tvTime3);
        tvOptions=(TextView) findViewById(R.id.tvOptions);
        //时间选择器
        pvTime1 = new TimePickerView(this, TimePickerView.Type.MONTH_DAY_HOUR_MIN);
        pvTime2 = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime3 = new TimePickerView(this, TimePickerView.Type.MONTH);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime1.setTime(new Date());
        pvTime1.setCyclic(false);
        pvTime1.setCancelable(true);
        pvTime2.setTime(new Date());
        pvTime2.setCyclic(false);
        pvTime2.setCancelable(true);
        pvTime3.setTime(new Date());
        pvTime3.setCyclic(false);
        pvTime3.setCancelable(true);
        //时间选择后回调
        pvTime1.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvTime1.setText(getTime(date));
            }
        });
        //弹出时间选择器
        tvTime1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pvTime1.show();
            }
        });
        //时间选择后回调
        pvTime2.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvTime2.setText(getTime(date));
            }
        });
        //弹出时间选择器
        tvTime2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pvTime2.show();
            }
        });
        //时间选择后回调
        pvTime3.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                Calendar calendar = Calendar.getInstance();
                if (date == null)
                    calendar.setTimeInMillis(System.currentTimeMillis());
                else
                    calendar.setTime(date);

                tvTime3.setText(1+calendar.get(Calendar.MONTH)+"月");
            }
        });
        //弹出时间选择器
        tvTime3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pvTime3.show();
            }
        });
        //选项选择器
        pvOptions = new OptionsPickerView(this);



        //选项1
        options1Items.add(new ProvinceBean(0,"广东","广东省，以岭南东道、广南东路得名","其他数据"));
        options1Items.add(new ProvinceBean(1,"湖南","湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南","芒果TV"));
        options1Items.add(new ProvinceBean(3,"广西","嗯～～",""));

        //选项2
        ArrayList<String> options2Items_01=new ArrayList<>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("阳江");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02=new ArrayList<>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        ArrayList<String> options2Items_03=new ArrayList<>();
        options2Items_03.add("桂林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        //选项3
        ArrayList<ArrayList<IPickerViewData>> options3Items_01 = new ArrayList<>();
        ArrayList<ArrayList<IPickerViewData>> options3Items_02 = new ArrayList<>();
        ArrayList<ArrayList<IPickerViewData>> options3Items_03 = new ArrayList<>();
        ArrayList<IPickerViewData> options3Items_01_01=new ArrayList<>();
        options3Items_01_01.add(new PickerViewData("天河"));
        options3Items_01_01.add(new PickerViewData("黄埔"));
        options3Items_01_01.add(new PickerViewData("海珠"));
        options3Items_01_01.add(new PickerViewData("越秀"));
        options3Items_01.add(options3Items_01_01);
        ArrayList<IPickerViewData> options3Items_01_02=new ArrayList<>();
        options3Items_01_02.add(new PickerViewData("南海"));
        options3Items_01_02.add(new PickerViewData("高明"));
        options3Items_01_02.add(new PickerViewData("禅城"));
        options3Items_01_02.add(new PickerViewData("桂城"));
        options3Items_01.add(options3Items_01_02);
        ArrayList<IPickerViewData> options3Items_01_03=new ArrayList<>();
        options3Items_01_03.add(new PickerViewData("其他"));
        options3Items_01_03.add(new PickerViewData("常平"));
        options3Items_01_03.add(new PickerViewData("虎门"));
        options3Items_01.add(options3Items_01_03);
        ArrayList<IPickerViewData> options3Items_01_04=new ArrayList<>();
        options3Items_01_04.add(new PickerViewData("其他"));
        options3Items_01_04.add(new PickerViewData("其他"));
        options3Items_01_04.add(new PickerViewData("其他"));
        options3Items_01.add(options3Items_01_04);
        ArrayList<IPickerViewData> options3Items_01_05=new ArrayList<>();

        options3Items_01_05.add(new PickerViewData("其他1"));
        options3Items_01_05.add(new PickerViewData("其他2"));
        options3Items_01.add(options3Items_01_05);

        ArrayList<IPickerViewData> options3Items_02_01=new ArrayList<>();

        options3Items_02_01.add(new PickerViewData("长沙1"));
        options3Items_02_01.add(new PickerViewData("长沙2"));
        options3Items_02_01.add(new PickerViewData("长沙3"));
        options3Items_02_01.add(new PickerViewData("长沙4"));
        options3Items_02_01.add(new PickerViewData("长沙5"));




        options3Items_02.add(options3Items_02_01);
        ArrayList<IPickerViewData> options3Items_02_02=new ArrayList<>();

        options3Items_02_02.add(new PickerViewData("岳阳"));
        options3Items_02_02.add(new PickerViewData("岳阳1"));
        options3Items_02_02.add(new PickerViewData("岳阳2"));
        options3Items_02_02.add(new PickerViewData("岳阳3"));
        options3Items_02_02.add(new PickerViewData("岳阳4"));
        options3Items_02_02.add(new PickerViewData("岳阳5"));

        options3Items_02.add(options3Items_02_02);
        ArrayList<IPickerViewData> options3Items_03_01=new ArrayList<>();
        options3Items_03_01.add(new PickerViewData("好山水"));
        options3Items_03.add(options3Items_03_01);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
                tvOptions.setText(tx);
//                vMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(pvOptions.isShowing()||pvTime1.isShowing()||pvTime2.isShowing()||pvTime3.isShowing()){
                pvOptions.dismiss();
                pvTime1.dismiss();
                pvTime2.dismiss();
                pvTime3.dismiss();
                return true;
            }
            if(pvTime1.isShowing()||pvTime2.isShowing()||pvTime3.isShowing()){
                pvTime1.dismiss();
                pvTime2.dismiss();
                pvTime3.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
