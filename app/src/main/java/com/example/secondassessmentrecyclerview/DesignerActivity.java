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
        DesignerAdapter adapter = new DesignerAdapter(this, designersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}

