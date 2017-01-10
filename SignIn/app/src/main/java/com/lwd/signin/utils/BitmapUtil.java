package com.lwd.signin.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

public class BitmapUtil
{
    public static Bitmap createReflectedBitmap(Bitmap srcBitmap)
    {
        if (null == srcBitmap)
        {
            return null;
        }
        
        // The gap between the reflection bitmap and original bitmap. 
        final int REFLECTION_GAP = 4;
        
        int srcWidth  = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        int reflectionWidth  = srcBitmap.getWidth();
        int reflectionHeight = srcBitmap.getHeight() / 2;
        
        if (0 == srcWidth || srcHeight == 0)
        {
            return null;
        }
        
        // The matrix
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        
        try
        {
            // The reflection bitmap, width is same with original's, height is half of original's.
            Bitmap reflectionBitmap = Bitmap.createBitmap(
                    srcBitmap,
                    0, 
                    srcHeight / 2,
                    srcWidth, 
                    srcHeight / 2,
                    matrix,
                    false);
            
            if (null == reflectionBitmap)
            {
                return null;
            }
            
            // Create the bitmap which contains original and reflection bitmap.
            Bitmap bitmapWithReflection = Bitmap.createBitmap(
                    reflectionWidth,
                    (srcHeight + (reflectionHeight + REFLECTION_GAP)/2), 
                    Config.ARGB_8888);
            
            if (null == bitmapWithReflection)
            {
                return null;
            }
            
            // Prepare the canvas to draw stuff.
            Canvas canvas = new Canvas(bitmapWithReflection);
            
            // Draw the original bitmap.
            canvas.drawBitmap(srcBitmap, 0, 0, null);
            
            // Draw the reflection bitmap.
            canvas.drawBitmap(reflectionBitmap, 0, srcHeight + REFLECTION_GAP, null);
            
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            LinearGradient shader = new LinearGradient(
                    0, 
                    srcHeight, 
                    0, 
                    bitmapWithReflection.getHeight() + REFLECTION_GAP, 
                    0x70FFFFFF, 
                    0x00FFFFFF,
                    TileMode.MIRROR);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
            
            // Draw the linear shader.
            canvas.drawRect(
                    0, 
                    srcHeight, 
                    srcWidth, 
                    bitmapWithReflection.getHeight() + REFLECTION_GAP, 
                    paint);
            
            return bitmapWithReflection;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Load bitmap file from sd card.
     * 
     * @param strPath The bitmap file path.
     * 
     * @return The Bitmap object, the returned value may be null.
     */
    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        if (null == drawable)
        {
            return null;
        }
        
        int width  = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        
        return drawableToBitmap(drawable, width, height);
    }
    
    /**
     * Load bitmap file from sd card.
     * 
     * @param strPath The bitmap file path.
     * 
     * @return The Bitmap object, the returned value may be null.
     */
    public static Bitmap drawableToBitmap(Drawable drawable, int width, int height)
    {
        if (null == drawable || width <= 0 || height <= 0)
        {
            return null;
        }

        Config config = (drawable.getOpacity() != PixelFormat.OPAQUE) ? Config.ARGB_8888 : Config.RGB_565;
        
        Bitmap bitmap = null;
        
        try
        {
            bitmap = Bitmap.createBitmap(width, height, config);
            if (null != bitmap)
            {
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, width, height);
                drawable.draw(canvas);
            }
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return bitmap;
    }
}
