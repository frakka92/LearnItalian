package com.example.android.learnitalian;

public class Word {

    private String mItalianTranslation;
    private String mDefaultTranslation;

    /**
     * @param mItalianTranslation italian word to be translated
     * @param mDefaultTranslation translation
     */

    public Word(String mItalianTranslation, String mDefaultTranslation) {
        this.mItalianTranslation = mItalianTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public String getmItalianWord() {
        return mItalianTranslation;
    }

    public void setmItalianWord(String mItalianWord) {
        this.mItalianTranslation = mItalianWord;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setmDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }
}
