package com.demoqa.utils.emulator_device_web;

import com.demoqa.utils.exceptions.FileException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {
    private final List<Device> devices;

    public JsonReader(String filePath) throws FileException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type type = new TypeToken<DeviceContainer>(){}.getType();
            DeviceContainer deviceContainer = gson.fromJson(reader, type);
            devices = deviceContainer.getDevices();
        } catch (FileNotFoundException e) {
            throw new FileException("File not found: " + filePath, e);
        } catch (IOException e) {
            throw new FileException("Error reading file: " + filePath, e);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("Syntax error in JSON file: " + filePath, e);
        } catch (Exception e) {
            throw new FileException("Unexpected error: " + filePath, e);
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