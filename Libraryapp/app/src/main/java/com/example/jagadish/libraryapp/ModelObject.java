package com.example.jagadish.libraryapp;

/**
 * Created by Jagadish on 1/03/2017.
 */
public enum ModelObject {

        RED(R.string.red, R.layout.view_red),
        BLUE(R.string.blue, R.layout.view_blue);

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

