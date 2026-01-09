package com.example.demoles12112025;

public class voertuig {
    private String merk;
    private String type;
    private String bouwjaar;

    public String getBouwjaar() {
        return bouwjaar;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "voertuig{" +
                "merk='" + merk + '|' +
                ", type='" + type + '|' +
                ", bouwjaar='" + bouwjaar + '|' +
                '}';
    }



    public voertuig(String merk, String type, String bouwjaar) {
        this.merk = merk;
        this.merk = type;
        this.type = type;
        this.bouwjaar = bouwjaar;
    }

    public String getMerk() {
        return this.merk;


    }
}