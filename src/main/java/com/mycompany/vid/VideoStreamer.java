package com.mycompany.vid;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import java.net.*;

public class VideoStreamer {
    public static void main(String[] args) {
        try {
            try (DatagramSocket socket = new DatagramSocket()) {
                InetAddress clientAddress = InetAddress.getByName("localhost"); // Thay bằng IP máy nhận
                int port = 8888;
                
                FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("video.mp4");
                grabber.start();
                
                while (true) {
                    Frame frame = grabber.grab();
                    if (frame == null) break;
                    
                    byte[] frameData = FrameToBytes.frameToBytes(frame);
                    DatagramPacket packet = new DatagramPacket(frameData, frameData.length, clientAddress, port);
                    socket.send(packet);
                    
                    Thread.sleep(33); // Phát ở tốc độ ~30 FPS
                }
                
                grabber.stop();
            } // Thay bằng IP máy nhận
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
