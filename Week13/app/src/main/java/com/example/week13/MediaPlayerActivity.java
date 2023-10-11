package com.example.week13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MediaPlayerActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    MediaPlayer mediaPlayer;
    ArrayList<String> listName = new ArrayList<>();
    ArrayList<Uri> listUri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.list_view);

        toolbar.setTitle("Media Player");
        setSupportActionBar(toolbar);

        // Lấy dữ lệu từ raw
        getListRaw();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.list_view_item, R.id.list_item, listName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    mediaPlayer.reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Lấy têm bài hát sau khi click
                String name = listName.get(i);
                // Lấy về đường đẫn
                Uri uri = getRawUri(name);
                // Tạo file play nhạc
                mediaPlayer = MediaPlayer.create(MediaPlayerActivity.this, uri);
                handlePlay(view);
//                mediaPlayer.setWakeMode(MediaPlayerActivity.this, PowerManager.PARTIAL_WAKE_LOCK);
            }
        });
    }

    public void getListRaw() {
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            // Thêm tên file nhạc vào list
            listName.add(fields[i].getName());
            Uri uri = getRawUri(fields[i].getName());
            listUri.add(uri);
        }
    }

    public Uri getRawUri(String fileName) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                File.pathSeparator + File.separator + File.separator +
                getPackageName() + "/raw/" + fileName);
    }

    public void handlePlay(View view) {
        try {
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handlePause(View view) {
        try {
            mediaPlayer.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}