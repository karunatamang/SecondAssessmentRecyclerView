package com.example.secondassessmentrecyclerview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondassessmentrecyclerview.model.Designer;

public class UserDetailActivity extends AppCompatActivity {

    TextView designerName, designerDob, designerGender, designerCountry, designerEmail, designerPhone, designerAddress;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        designerName = findViewById(R.id.designer_name);
        designerDob = findViewById(R.id.designer_dob);
        designerGender = findViewById(R.id.designer_gender);
        designerCountry = findViewById(R.id.designer_country);
        designerEmail = findViewById(R.id.designer_email);
        designerPhone = findViewById(R.id.designer_phone);
        designerAddress = findViewById(R.id.designer_address);
        imageView = findViewById(R.id.img_designer);

        Designer designer = (Designer) getIntent().getSerializableExtra("DesignerDetails");

        int imageID = Integer.valueOf(designer.getImage());

        designerName.setText(designer.getName());
        designerDob.setText(designer.getDob());
        designerGender.setText(designer.getGender());
        designerCountry.setText(designer.getCountry());
        designerEmail.setText(designer.getEmail());
        designerPhone.setText(designer.getPhone());
        designerAddress.setText(designer.getAddress());
        imageView.setImageResource(imageID);

    }
}
