package foundation.redstar.com.demos.piker.bean;


import com.redstar.foundation.ui.widget.pickerview.model.IPickerViewData;

/**
 * Created by Administrator on 2016/7/13.
 *
 */
public class PickerViewData implements IPickerViewData {
    private String content;

    public PickerViewData(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getPickerViewText() {
        return content;
    }
}
