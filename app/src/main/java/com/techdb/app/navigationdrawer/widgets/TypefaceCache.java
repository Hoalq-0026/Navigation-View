package com.techdb.app.navigationdrawer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.techdb.app.navigationdrawer.R;

import java.util.Hashtable;

public class TypefaceCache {

    public static final int VARIATION_NORMAL = 0;
    public static final int VARIATION_LIGHT = 1;
    public static final int VARIATION_SPECIAL = 2;
    public static final int VARIATION_THIN = 3;
    public static final int VARIATION_MEDIUM = 4;

    public static final int VARIATION_CONDENSED = 5;
    public static final int VARIATION_BLACK = 6;
    public static final int VARIATION_EXTRA_LIGHT = 7;
    public static final int VARIATION_SEMI = 8;
    public static final int VARIATION_BOLD = 9;

    protected static final Hashtable<String, Typeface> mTypefaceCache = new Hashtable<>();

    public static Typeface getTypeface(Context context) {
        return getTypeface(context, Typeface.NORMAL, VARIATION_NORMAL);
    }

    public static Typeface getTypeface(Context context, int fontStyle, int variation) {
        if (context == null) {
            return null;
        }

        String typefaceName = "Roboto-Regular.ttf";
        if (variation == VARIATION_BLACK) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "Roboto-BlackItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "Roboto-Black.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_BOLD) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "Roboto-BoldItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "Roboto-Bold.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_NORMAL) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "Roboto-Italic.ttf";
                    break;
                }
                default: {
                    typefaceName = "Roboto-Regular.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_LIGHT) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "Roboto-LightItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "Roboto-Light.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_MEDIUM) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "Roboto-MediumItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "Roboto-Medium.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_THIN) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "Roboto-ThinItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "Roboto-Thin.ttf";
                    break;
                }
            }
        } else {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "Roboto-Italic.ttf";
                    break;
                }
                default: {
                    typefaceName = "Roboto-Regular.ttf";
                    break;
                }
            }
        }

        return getTypefaceForTypefaceName(context, typefaceName);
    }

    public static Typeface getTypefaceForTypefaceName(Context context, String typefaceName) {
        if (!mTypefaceCache.containsKey(typefaceName)) {
            Typeface typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/" + typefaceName);
            if (typeface != null) {
                mTypefaceCache.put(typefaceName, typeface);
            }
        }
        return mTypefaceCache.get(typefaceName);
    }

    /**
     * Set the typeface for a TextView (or TextView descendant such as EditText or Button) based on the passed attributes, defaults to
     * normal typeface.
     * @param context
     * @param view
     * @param attributeSet
     */
    public static void setCustomTypeface(Context context, TextView view, AttributeSet attributeSet) {
        if (context == null || view == null) {
            return;
        }

        //Skip at design-time (edit mode)
        if (view.isInEditMode()) {
            return;
        }

        // read custom fontVariation from attributes, default to normal
        int variation = TypefaceCache.VARIATION_NORMAL;
        if (attributeSet != null) {
            TypedArray array = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OpenTextView, 0, 0);
            if (array != null) {
                try {
                    variation = array.getInteger(R.styleable.OpenTextView_fontVariation, TypefaceCache.VARIATION_NORMAL);
                } finally {
                    array.recycle();
                }
            }

        }

        final int fontStyle;
        if (view.getTypeface() != null) {
            boolean isBold = view.getTypeface().isBold();
            boolean isItalic = view.getTypeface().isItalic();
            if (isBold && isItalic) {
                fontStyle = Typeface.BOLD_ITALIC;
            } else if (isBold) {
                fontStyle = Typeface.BOLD;
            } else if (isItalic) {
                fontStyle = Typeface.ITALIC;
            } else {
                fontStyle = Typeface.NORMAL;
            }
        } else {
            fontStyle = Typeface.NORMAL;
        }

        Typeface typeface = getTypeface(context, fontStyle, variation);
        if (typeface != null) {
            view.setTypeface(typeface);
        }
    }
}
