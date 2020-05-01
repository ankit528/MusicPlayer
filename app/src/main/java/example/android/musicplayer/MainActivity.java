package example.android.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static example.android.musicplayer.R.layout;
import static example.android.musicplayer.R.raw;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        final ImageView play, stop;
        mediaPlayer = MediaPlayer.create(MainActivity.this, raw.glimpses_of_kesari_ringtone);

        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.pause);
                } else {
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.play);
                }
            }
        });

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play.setImageResource(R.drawable.play);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "Song Finished..!!!", Toast.LENGTH_SHORT).show();
                play.setImageResource(R.drawable.play);
                mediaPlayer.release();
            }
        });
    }
}
