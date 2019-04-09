package com.example.android.learnitalian;

public class Word {

    private String mItalianTranslation;
    private String mDefaultTranslation;
    private int mIcon = NO_IMAGE;
    private int mAudio;

    private static final int NO_IMAGE = -1;

    /**
     * @param mItalianTranslation italian word to be translated
     * @param mDefaultTranslation translation
     */

    /**
     * @param mItalianTranslation italian word to be translated
     * @param mDefaultTranslation translation
     * @param mAudio              icon that represents the word
     */
    public Word(String mItalianTranslation, String mDefaultTranslation, int mAudio) {
        this.setmItalianTranslation(mItalianTranslation);
        this.setmDefaultTranslation(mDefaultTranslation);
        this.setmAudio(mAudio);
    }

    /**
     * @param mItalianTranslation italian word to be translated
     * @param mDefaultTranslation translation
     * @param mIcon               icon that represents the word
     * @param mAudio              audio of the word
     */
    public Word(String mItalianTranslation, String mDefaultTranslation, int mIcon, int mAudio) {
        this.setmItalianTranslation(mItalianTranslation);
        this.setmDefaultTranslation(mDefaultTranslation);
        this.setmIcon(mIcon);
        this.setmAudio(mAudio);
    }

    public String getmItalianTranslation() {
        return mItalianTranslation;
    }

    public void setmItalianTranslation(String mItalianTranslation) {
        this.mItalianTranslation = mItalianTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setmDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public boolean hasImage() {
        return this.getmIcon() != Word.NO_IMAGE;
    }

    public int getmAudio() {
        return mAudio;
    }

    public void setmAudio(int mAudio) {
        this.mAudio = mAudio;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mItalianTranslation='" + mItalianTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mIcon=" + mIcon +
                ", mAudio=" + mAudio +
                '}';
    }
}
