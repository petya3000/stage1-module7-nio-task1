package com.epam.mjc.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {
        ByteBuffer buffer = null;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r");
             FileChannel channel = raf.getChannel()) {

            buffer = ByteBuffer.allocate(400);
            StringBuilder stringBuilder = new StringBuilder();
            while ((channel.read(buffer)) > 0) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    stringBuilder.append((char) buffer.get());
                }
            }

            String str = stringBuilder.toString();
            int start = str.indexOf(':');
            int end = str.indexOf('\n');
            String name = str.substring(start + 2, end - 1);
            str = str.substring(end + 1);

            start = str.indexOf(':');
            end = str.indexOf('\n');
            String age = str.substring(start + 2, end - 1);
            Integer intAge = age.isEmpty() ? null : Integer.parseInt(age);
            str = str.substring(end + 1);

            start = str.indexOf(':');
            end = str.indexOf('\n');
            String email = str.substring(start + 2, end - 1);
            str = str.substring(end + 1);

            start = str.indexOf(':');
            end = str.indexOf('\n');
            String phone = str.substring(start + 2, end - 1);
            Long longPhone;
            if (phone.isEmpty() || phone.equals(" "))
                longPhone = 0L;
            else
                longPhone = Long.parseLong(phone);

            return new Profile(name, intAge, email, longPhone);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                buffer.clear();
            }
            catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
    }
}
