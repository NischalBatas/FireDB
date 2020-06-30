package com.example.firedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText singer_name;
    private ListView lv;
    private Spinner spin_genre;
    private DatabaseReference dbRef;
    private List<Singer> listSinger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singer_name=findViewById(R.id.edit_singer_id);
        spin_genre=findViewById(R.id.spin_genre_id );
        lv =findViewById(R.id.lv_id);


        listSinger =new ArrayList<>();
        dbRef= FirebaseDatabase.getInstance().getReference("Singer");

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSinger.clear();
                for (DataSnapshot singerSnapshot : dataSnapshot.getChildren()){
                    Singer singerObj = singerSnapshot.getValue(Singer.class);
                    listSinger.add(singerObj);
                }
                SingerAdapter adapter=new SingerAdapter(MainActivity.this, listSinger);
                lv.setAdapter(adapter);

                Toast.makeText(MainActivity.this, "Firebase DB Access", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "FireBase db nt access", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void add_Singer(View view) {
        //yo function call huncha add singer button click garda
        //firebase ma singre data push garne
        String singerName= singer_name.getText().toString();
        String singerGenre =spin_genre.getSelectedItem().toString();

        if (TextUtils.isEmpty(singerName)){
            Toast.makeText(this, "Singer name is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            //firebase ma singer data push garne code lekhne
            //primary key banau nay
            String id =dbRef.push().getKey();
            Singer singerobj =new Singer(id, singerName,singerGenre);
            dbRef.child(id).setValue(singerobj);
            Toast.makeText(this, "Singer addd ro Fire Database", Toast.LENGTH_SHORT).show();
        }

    }
}
