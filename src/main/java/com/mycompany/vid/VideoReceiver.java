package com.mycompany.vid;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import java.net.*;

public class VideoReceiver {
    public static void main(String[] args) {
        try {
            try (DatagramSocket socket = new DatagramSocket(8888)) {
                byte[] buffer = new byte[65536]; // Buffer đủ lớn để nhận dữ liệu
                
                CanvasFrame canvas = new CanvasFrame("Video Stream");
                canvas.setDefaultCloseOperation(CanvasFrame.EXIT_ON_CLOSE);
                
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    
                    Frame frame = FrameToBytes.bytesToFrame(packet.getData());
                    canvas.showImage(frame);
                    
                    if (!canvas.isVisible()) break;
                }
            } // Buffer đủ lớn để nhận dữ liệu
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
