package com.techdb.app.navigationdrawer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.techdb.app.navigationdrawer.R;

public class OpenTextView extends TextView {

    protected boolean mFixWidowWordEnabled;

    public OpenTextView(Context context) {
        super(context);
        TypefaceCache.setCustomTypeface(context, this, null);
    }

    public OpenTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypefaceCache.setCustomTypeface(context, this, attrs);
        readCustomAttrs(context, attrs);
    }

    public OpenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypefaceCache.setCustomTypeface(context, this, attrs);
        readCustomAttrs(context, attrs);
    }

    public void setFixWidowWordEnabled(boolean enabled) {
        this.mFixWidowWordEnabled = enabled;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!mFixWidowWordEnabled) {
            super.setText(text, type);
            return;
        }

        Spannable out;
        int lastSpace = String.valueOf(text).lastIndexOf(' ');
        if (lastSpace != -1 && lastSpace < text.length() - 1) {
            // Replace last space character by a non breaking space.
            CharSequence tmpText = replaceCharacter(text, lastSpace, "\u00A0");
            out = new SpannableString(tmpText);
            // Restore spans if text is an instance of Spanned
            if (text instanceof Spanned) {
                TextUtils.copySpansFrom((Spanned) text, 0, text.length(), null, out, 0);
            }
        } else {
            out = new SpannableString(text);
        }
        super.setText(out, type);
    }

    private void readCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OpenTextView, 0, 0);
        if (array != null) {
            mFixWidowWordEnabled = array.getBoolean(R.styleable.OpenTextView_fixWidowWords, false);

            if (mFixWidowWordEnabled) {
                setText(getText());
            }
        }
    }

    private CharSequence replaceCharacter(CharSequence source, int charIndex, CharSequence replacement) {
        if (charIndex != -1 && charIndex < source.length() - 1) {
            return TextUtils.concat(source.subSequence(0, charIndex), replacement, source.subSequence(charIndex + 1, source.length()));
        }
        return source;
    }
}
