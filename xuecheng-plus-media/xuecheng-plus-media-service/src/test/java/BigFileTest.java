import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BigFileTest {
    @Test
    void BigFileChunk() throws IOException {
        File file = new File("D:\\test\\01-今日课程介绍.mp4");
        String chunkPath = "D:\\test\\chunk\\";
//        File file1 = new File(chunkPath+i);
        long chunkSize = 1024 * 1024 * 1;
        //分块数量
        long chunkNum = (long) Math.ceil(file.length() * 1.0 / chunkSize);
        RandomAccessFile r = new RandomAccessFile(file, "r");
        byte[] bytes=new byte[1024];
        for (long i = 0; i < chunkNum; i++) {
            File file1 = new File(chunkPath + i);
            RandomAccessFile rw = new RandomAccessFile(file1, "rw");
            int len =-1;
            while ((len=r.read(bytes))!=-1){
                rw.write(bytes,0,len);
                if (rw.length()>=chunkSize){
                    break;
                }
            }
            rw.close();
        }
        r.close();
    }

    @Test
    void BigFileMerge() throws IOException {
        File file = new File("D:\\test\\chunk");
        File[] files = file.listFiles();
        List<File> files1 = Arrays.asList(files);
        Collections.sort(files1, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o1.getName())-Integer.parseInt(o2.getName());
            }
        });
        File file2 = new File("D:\\test\\02-今日课程介绍.mp4");
        RandomAccessFile rw = new RandomAccessFile(file2, "rw");
        for (File file1 : files1) {
            RandomAccessFile r = new RandomAccessFile(file1, "r");
            byte[] bytes=new byte[1024];
            int len=-1;
            while ((len=r.read(bytes))!=-1){
                rw.write(bytes,0,len);
            }
            r.close();

        }
        rw.close();

    }
}
