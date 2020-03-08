package me.akoala.onlinelog.util;

import lombok.SneakyThrows;
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

    @SneakyThrows
    public static void checkFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            String error = "FileNotFound: " + path;
//            log.error(error);
            throw new FileNotFoundException(error);
        }
    }

    /**
     * @param path 路径
     * @param num  行数
     * @return
     */
    public static List<String> tail(String path, int num) {
        try {
            checkFile(path);
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path, "r");
            long length = raf.length();
            if (length == 0) {
                return Collections.EMPTY_LIST;
            }

            // 期望指针位置
            long expectPoint;

            long lineEnd = -1;
            long oldLineEnd = -1;
            long lineStart = -1;

            // 指向文件倒数第二位字节偏移，用于获取最后一位字节数据
            expectPoint = length - 1;
            raf.seek(expectPoint);

            // 保存每行数据到容器
            List<String> result = new ArrayList<>();
            int readLineNum = num;
            for (int i = 0; i < num; i++) {
                while (expectPoint >= 0 && readLineNum > 0) {

                    // 指针会移动
                    byte readByte = raf.readByte();

                    if (readByte == '\n') {

                        if (lineEnd != -1) {
                            oldLineEnd = lineEnd;
                        }
                        expectPoint -= 1;
                        raf.seek(expectPoint);

                        // 几位换行符，linux 是1位
                        int flag = 1;
                        // 紧跟的前一位是 \r
                        if (raf.readByte() == '\r') {
                            // windows 是两位
                            flag = 2;
                            lineEnd = expectPoint;
                            raf.seek(expectPoint);
                            if (oldLineEnd != -1) {
                                lineStart = expectPoint + 4;
                            }
                        } else {
                            lineEnd = expectPoint + 1;
                            if (oldLineEnd != -1) {
                                lineStart = expectPoint + 3;
                            }
                        }

                        if (oldLineEnd != -1) {

                            raf.seek(lineStart);

                            byte[] buf = new byte[(int) (oldLineEnd - lineStart)];
                            raf.read(buf);
                            String str = new String(buf);
                            result.add(str);

                            raf.seek(lineStart - flag);

                            oldLineEnd = -1;
                            lineStart = -1;

                            readLineNum--;
                        }

                    }
                    // 指向左一个
                    expectPoint--;
                    if (expectPoint < 0) {
                        break;
                    }
                    raf.seek(expectPoint);


                }
//                //
//
//
//
////                log.info("ln {}: {}", i, str);
//
//
//
//                start = nextEnd;
//                end = nextEnd;
//                raf.seek(start);
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
