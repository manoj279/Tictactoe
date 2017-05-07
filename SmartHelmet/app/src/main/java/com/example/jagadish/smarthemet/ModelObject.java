package com.example.jagadish.smarthemet;

public enum ModelObject {

    Connectivity(R.string.connectivity, R.layout.connectivity),
    Music(R.string.music, R.layout.music);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}

