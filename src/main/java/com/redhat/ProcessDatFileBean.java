package com.redhat;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessDatFileBean {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessDatFileBean.class);

    private List<UploadedImage> files = new ArrayList<>();

    private int uploadsAvailable = 5;

    private boolean autoUpload = false;

    private boolean useFlash = true;

    private String displayMessage;

    private boolean uploadSuccessful = false;

    private List<String> errors = null;

    public int getSize() {

        return getFiles().size();
    }

    public void paint(OutputStream stream, Object object) throws IOException {

        stream.write(getFiles().get((Integer) object).getData());
    }

    public void listener(UploadEvent event) throws Exception {

        UploadItem item = event.getUploadItem();
        LOG.info("{}", item.getFileName());
        LOG.info("{}", item.getContentType());
        LOG.info("{}", item.getFileSize());

        File uploadedFile = item.getFile();
        LOG.info("{}", uploadedFile.getAbsolutePath());

        Path path = Paths.get(uploadedFile.getAbsolutePath());
        byte[] data = Files.readAllBytes(path);

        UploadedImage file = new UploadedImage();
        file.setLength(data.length);
        file.setName(item.getFileName());
        file.setData(data);
        files.add(file);
        uploadsAvailable--;
    }

    public String clearUploadData() {

        files.clear();
        setUploadsAvailable(5);
        return null;
    }

    public long getTimeStamp() {

        return System.currentTimeMillis();
    }

    public List<UploadedImage> getFiles() {

        LOG.info("getFiles() {}", files != null ? files.size() : null);

        return files;
    }

    public void setFiles(List<UploadedImage> files) {

        LOG.info("setFiles() {}", files != null ? files.size() : null);

        this.files = files;
    }

    public int getUploadsAvailable() {

        return uploadsAvailable;
    }

    public void setUploadsAvailable(int uploadsAvailable) {

        this.uploadsAvailable = uploadsAvailable;
    }

    public boolean isAutoUpload() {

        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {

        this.autoUpload = autoUpload;
    }

    public boolean isUseFlash() {

        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {

        this.useFlash = useFlash;
    }

    public String getDisplayMessage() {

        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {

        this.displayMessage = displayMessage;
    }

    public boolean isUploadSuccessful() {

        return uploadSuccessful;
    }

    public void setUploadSuccessful(boolean uploadSuccessful) {

        this.uploadSuccessful = uploadSuccessful;
    }

    public List<String> getErrors() {

        return errors;
    }

    public void setErrors(ArrayList<String> errors) {

        this.errors = errors;
    }

}