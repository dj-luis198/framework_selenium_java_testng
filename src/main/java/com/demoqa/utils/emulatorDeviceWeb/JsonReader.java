package com.demoqa.utils.emulatorDeviceWeb;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {
    private List<Device> devices;

    public JsonReader(String filePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type type = new TypeToken<DeviceContainer>(){}.getType();
            DeviceContainer deviceContainer = gson.fromJson(reader, type);
            devices = deviceContainer.getDevices();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Device getDevice(String deviceName) {
        for (Device device : devices) {
            if (device.getName().equals(deviceName)) {
                return device;
            }
        }
        return null;
    }
}