/**
 * Created by AshikR1
 * Date: 1/14/2024.
 */

package com.example.saishruti.Utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {
    public static int dpToPxl(int dpVal, Resources res){
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpVal, res.getDisplayMetrics()
        );
    }
}
