package com.weiwosuoai.android50palettedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.test3);
        // Create a Palette Object.
        Palette palette = new Palette.Builder(bitmap).generate();
        // Get the corresponding tones by the Palette.
        // 活力黑
        Palette.Swatch vibrantDark =
                palette.getDarkVibrantSwatch();

        // 充满活力的
        Palette.Swatch vibrant =
                palette.getVibrantSwatch();

        // 活力亮
        Palette.Swatch vibrantLight =
                palette.getLightVibrantSwatch();

        // 柔和
        Palette.Swatch muted =
                palette.getMutedSwatch();

        // 柔和黑
        Palette.Swatch mutedDark =
                palette.getDarkMutedSwatch();

        // 柔和亮
        Palette.Swatch mutedLight =
                palette.getLightMutedSwatch();

        // Set the color to the corresponding component.
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(vibrant.getRgb())
        );

        // If the current system version is >= 'LOLLIPOP'.
        if (Util.getSystemVersion() >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(vibrantDark.getRgb());
        }

//        Palette.generateAsync(bitmap,
//                new Palette.PaletteAsyncListener() {
//                    /**
//                     * Called when the {@link Palette} has been generated.
//                     *
//                     * @param palette
//                     */
//                    @Override
//                    public void onGenerated(Palette palette) {
//                        // Get the corresponding tones by the Palette.
//                        // 活力黑
//                        Palette.Swatch vibrantDark =
//                                palette.getDarkVibrantSwatch();
//
//                        // 充满活力的
//                        Palette.Swatch vibrant =
//                                palette.getVibrantSwatch();
//
//                        // 活力亮
//                        Palette.Swatch vibrantLight =
//                                palette.getLightVibrantSwatch();
//
//                        // 柔和
//                        Palette.Swatch muted =
//                                palette.getMutedSwatch();
//
//                        // 柔和黑
//                        Palette.Swatch mutedDark =
//                                palette.getDarkMutedSwatch();
//
//                        // 柔和亮
//                        Palette.Swatch mutedLight =
//                                palette.getLightMutedSwatch();
//
//                        // Set the color to the corresponding component.
//                        getSupportActionBar().setBackgroundDrawable(
//                                new ColorDrawable(muted.getRgb())
//                        );
//
//                        // If the current system version is >= 'LOLLIPOP'.
//                        if (Util.getSystemVersion() >= Build.VERSION_CODES.LOLLIPOP) {
//                            Window window = getWindow();
//                            window.setStatusBarColor(mutedDark.getRgb());
//                        }
//
//                    }
//                });

    }
}
