package com.david.Overlappingimageview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String GITHUB_URL = "https://github.com/davidHarush/Overlappingimageview/blob/master/README.md";

    private FloatingActionButton mFab;
    private OverlappingImageView mImg2, mImg3, mImg4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mImg2 = ((OverlappingImageView) findViewById(R.id.imageView1))
                .setMaskShape(android.R.drawable.picture_frame)
                .setDrawOnFront(false);

        mImg3 = ((OverlappingImageView) findViewById(R.id.imageView2))
                .setMaskShape(R.drawable.mask_shape);

        mImg4 = ((OverlappingImageView) findViewById(R.id.imageView3))
                .setMaskShape(R.drawable.mask_shape)
                .setDrawOnFront(false);


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImg2.toggleMask();
                mImg3.toggleMask();
                mImg4.toggleMask();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_info) {
            showInfoDialog();
            return true;
        }

        if (id == R.id.action_github) {
            openGitHub();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showInfoDialog() {

        AlertDialog dialog = new AlertDialog.Builder(this).
                setTitle("INFO").
                setMessage(Html.fromHtml("this is example for a simple ImageView with adding " +
                        "Functionality to Draw Drawable on the front\\background of the imageView.<br/>" +
                        "<a href=" + GITHUB_URL + "> for more Info go to GitHub </a>")).
                setPositiveButton("ok", null).
                show();
        ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void openGitHub() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_URL));
        startActivity(browserIntent);
    }

}
