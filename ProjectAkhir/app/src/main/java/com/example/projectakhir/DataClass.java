package com.example.projectakhir;

public class DataClass {
    private String dataTitle;
    private int dataDesc;
    private String dataTgl;
    private int dataImage;

    public DataClass(String dataTitle, int dataDesc, String dataTgl, int dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataTgl = dataTgl;
        this.dataImage = dataImage;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public String getDataTgl() {
        return dataTgl;
    }

    public int getDataImage() {
        return dataImage;
    }
}