package com.example.secondassessmentrecyclerview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondassessmentrecyclerview.adapter.DesignerAdapter;
import com.example.secondassessmentrecyclerview.model.Designer;

import java.util.ArrayList;
import java.util.List;

public class DesignerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Designer> designerArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);
        recyclerView = findViewById(R.id.designer_container);

        Intent intent = getIntent();
        final List<Designer> designersList = (List<Designer>) intent.getSerializableExtra("designerlist");
//
//        String[] designerNames = new String[designersList.size()];
//        String[] designerDob = new String[designersList.size()];
//        String[] designerGender = new String[designersList.size()];
//        String[] designerCountry = new String[designersList.size()];
//        String[] designerEmail = new String[designersList.size()];
//        String[] designerPhone = new String[designersList.size()];
//        String[] designerAddress = new String[designersList.size()];
//        String[] designerimage = new String[designersList.size()];
//
//        int i = 0;
//        for (Designer designer : designersList) {
//            designerNames[i] = designer.getName();
//            designerDob[i] = designer.getDob();
//            designerGender[i] = designer.getGender();
//            designerCountry[i] = designer.getCountry();
//            designerEmail[i] = designer.getEmail();
//            designerPhone[i] = designer.getPhone();
//            designerAddress[i] = designer.getAddress();
//            designerimage[i] = designer.getImage();
//            designerArrayList.add(new Designer(designerNames[i], designerDob[i], designerGender[i], designerCountry[i], designerEmail[i], designerPhone[i], designerAddress[i], designerimage[i]));
//            i++;
//        }

        DesignerAdapter adapter = new DesignerAdapter(this, designersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}

