package com.mycompany.vid;

import java.io.*;
import org.bytedeco.javacv.Frame;

public class FrameToBytes {
    public static byte[] frameToBytes(Frame frame) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(frame);
        oos.flush();
        return baos.toByteArray();
    }

    public static Frame bytesToFrame(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (Frame) ois.readObject();
    }
}
