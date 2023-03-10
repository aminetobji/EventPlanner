package edu.event.utils;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageDownloader {
    private String imageUrl;
    private String destinationFile;
    
    public ImageDownloader(String imageUrl, String destinationFile) {
        this.imageUrl = imageUrl;
        this.destinationFile = destinationFile;
    }
        
    public void downlaod() throws IOException {;
        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream out = new FileOutputStream("media/"+destinationFile);
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
    }
}
