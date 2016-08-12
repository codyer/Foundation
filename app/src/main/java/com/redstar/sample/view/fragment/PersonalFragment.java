package com.redstar.sample.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.redstar.sample.R;
import com.redstar.sample.databinding.FragmentPersonalBinding;
import com.redstar.sample.view.activity.personal.PersonalInformationActivity;

public class PersonalFragment extends Fragment {

    private FragmentPersonalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * 绑定view
         */
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setSalesDataChecked(true);
        binding.salesOrMarketDataTabButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == binding.salesDataRadioButton.getId()) {
                    binding.setSalesDataChecked(true);
                } else {
                    binding.setSalesDataChecked(false);
                }
            }
        });

        binding.monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int month = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonalInformationActivity.class);
                startActivity(intent);
            }
        });
    }
}
