package com.demoqa.utils.emulator_device_web;

public class Device {
    private String type;
    private String name;
    private int width;
    private int height;
    private boolean mobile;
    private String firefoxUserAgent;

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFirefoxUserAgent() {
        return firefoxUserAgent;
    }

    public void setFirefoxUserAgent(String firefoxUserAgent) {
        this.firefoxUserAgent = firefoxUserAgent;
    }
}
