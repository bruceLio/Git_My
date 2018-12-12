package file.util;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public enum FileType {

    // 未知
    UNKNOWN,
    // 压缩文件
    ZIP, RAR, _7Z, TAR, GZ, TAR_GZ, BZ2, TAR_BZ2,
    // 位图文件
    BMP, PNG, JPG, JPEG,
    // 矢量图文件
    SVG,
    // 影音文件
    AVI, MP4, MP3, AAR, OGG, WAV, WAVE;

    /**
     * 获取文件真实类型
     */
    private static final String CHINESE_CHARSET = "GBK";

    private static FileType getFileType(File file) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] head = new byte[4];
            if (-1 == inputStream.read(head)) {
                return FileType.UNKNOWN;
            }
            int headHex = 0;
            for (byte b : head) {
                headHex <<= 8;
                headHex |= b;
            }
            switch (headHex) {
                case 0x504B0304:
                    return FileType.ZIP;
                case 0x776f7264:
                    return FileType.TAR;
                case -0x51:
                    return FileType._7Z;
                case 0x425a6839:
                    return FileType.BZ2;
                case -0x74f7f8:
                    return FileType.GZ;
                case 0x52617221:
                    return FileType.RAR;
                default:
                    return FileType.UNKNOWN;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return FileType.UNKNOWN;
    }

    /**
     * 解压缩7z文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    private static void decompress7Z(File file, String targetPath, boolean delete) {
        SevenZFile sevenZFile = null;
        OutputStream outputStream = null;
        try {
            sevenZFile = new SevenZFile(file);
            // 创建输出目录
            createDirectory(targetPath, null);
            SevenZArchiveEntry entry;

            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else {
                    outputStream = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
                    int len = 0;
                    byte[] b = new byte[2048];
                    while ((len = sevenZFile.read(b)) != -1) {
                        outputStream.write(b, 0, len);
                    }
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sevenZFile != null) {
                    sevenZFile.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 构建目录
     *
     * @param outputDir 输出目录
     * @param subDir    子目录
     */
    private static void createDirectory(String outputDir, String subDir) {
        File file = new File(outputDir);
        if (!(subDir == null || subDir.trim().equals(""))) {//子目录不为空
            file = new File(outputDir + File.separator + subDir);
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }

    public static void decompress(File file, String targetPath, boolean delete) {
        FileType fileType = getFileType(file);
        switch (fileType) {
            case _7Z:
                decompress7Z(file, targetPath, delete);
                break;
            case RAR:
                decompressRAR(file, targetPath, delete);
            default:
                break;
        }
    }

    /**
     * 解压缩RAR文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    private static void decompressRAR(File file, String targetPath, boolean delete) {
        Archive archive = null;
        OutputStream outputStream = null;
        try {
            archive = new Archive(file);
            FileHeader fileHeader;
            // 创建输出目录
            createDirectory(targetPath, null);
            while ((fileHeader = archive.nextFileHeader()) != null) {
                if (fileHeader.isDirectory()) {
                    createDirectory(targetPath, fileHeader.getFileNameString().trim()); // 创建子目录
                } else {
                    outputStream = new FileOutputStream(new File(targetPath + File.separator + fileHeader.getFileNameString().trim()));
                    archive.extractFile(fileHeader, outputStream);
                }
            }
        } catch (RarException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (archive != null) {
                    archive.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFileName(String fileName) {
        int i = fileName.lastIndexOf(".");
        if(i>0){
            return fileName.substring(0, i);
        }
        return fileName;
    }
}