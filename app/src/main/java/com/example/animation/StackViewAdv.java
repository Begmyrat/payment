package com.example.animation;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.StackView;

public class StackViewAdv extends StackView
{
    public StackViewAdv(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public StackViewAdv(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setDisplayedChild(int whichChild)
    {
        this.getOnItemSelectedListener().onItemSelected(this, null, whichChild, -1);

        super.setDisplayedChild(whichChild);
    }
}