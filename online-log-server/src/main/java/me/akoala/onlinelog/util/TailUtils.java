package me.akoala.onlinelog.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 文件读取工具
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/11/22 15:47
 */
@Slf4j
public class TailUtils {

    public static void checkFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("FileNotFoundException");
        }
    }

    public static List<String> tail(String path, int num) {
        checkFile(path);

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path, "r");
            long length = raf.length();
            if (length == 0) {
                return null;
            }

            // 当前指针位置
            long end = length;
            long start = length - 1;
            long nextEnd;

            // 指向最后
            raf.seek(start);

            // 保持结果
            List<String> result = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                while (true) {
                    // 指针 不会移动
                    byte readByte = raf.readByte();
                    if (readByte == '\n') {

                        raf.seek(start - 1);
                        // 紧跟的前一位是 \r
                        if (raf.readByte() == '\r') {
                            nextEnd = start - 1;
                        } else {
                            nextEnd = start;
                        }
                        raf.seek(++start);

                        break;
                    }

                    // 指向左一个
                    raf.seek(--start);
                }
                //
                byte[] buf = new byte[(int) (end - start)];
                raf.read(buf);
                String str = new String(buf);

//                log.info("ln {}: {}", i, str);

                result.add(str);

                start = nextEnd;
                end = nextEnd;
                raf.seek(start);
            }
            Collections.reverse(result);
            return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(raf);
        }

        return null;
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
