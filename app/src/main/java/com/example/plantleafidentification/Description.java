package com.example.plantleafidentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Description extends AppCompatActivity {
    TextView med_use,edible_use,other_use,leaf_name;

    private static final String TAG = "Benefits Display Screen";
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        leaf_name = (TextView)findViewById(R.id.leaf_name_desc);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");

        med_use = (TextView)findViewById(R.id.med_use);
        edible_use = (TextView)findViewById(R.id.edible_use);
        other_use = (TextView)findViewById(R.id.other_use);


//        String name = "Lemon";
//        String desc = "Lemons are an excellent preventative medicine and have a wide range of uses in the domestic medicine chest.The skin of the ripe fruit is carminative and stomachic. The stem bark is bitter, stomachic and tonic .";
//        String sol = "The fruit is rich in vitamin C which helps the body to fight off infections and also to prevent or treat scurvy for sailors.The juice of the fruit is used for polishing bronze and other metals that have been neglected . ";
        //String sol = "Fruit - raw or cooked . A very acid taste . Mainly used as a drink and as a flavouring . It is also used in salad dressings etc where it acts as an antioxidant as well as imparting an acid flavour . The juice is used to help set jam . The fruit can be up to 15cm long and 7cm wide .";

        plantDetails(str);
    }

    private void plantDetails(String plantName){
        Log.d(TAG, "Setting up plant name in DescriptionScreen");

        leaf_name.setText(plantName);
        db = FirebaseFirestore.getInstance();

        if(!plantName.isEmpty()){
            db.collection("Herbplex").whereEqualTo("leaf_name", plantName).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for(DocumentSnapshot document : task.getResult()){
                                    med_use.setText((CharSequence) document.get("leaf_medicinal_use"));
                                    edible_use.setText((CharSequence) document.get("leaf_edible_use"));
                                    other_use.setText((CharSequence) document.get("leaf_other_use"));
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"No such document", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

}