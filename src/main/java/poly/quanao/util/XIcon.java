package poly.quanao.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class XIcon {

    public static ImageIcon getIcon(String path) {
        // Trường hợp là tên file ngắn, ví dụ "logo.png"
        if (!path.contains("/") && !path.contains("\\")) {
            return XIcon.getIcon("/poly/quanao/icons/" + path);
        }

        // Trường hợp là đường dẫn resource bắt đầu bằng "/"
        if (path.startsWith("/")) {
            URL url = XIcon.class.getResource(path);
            if (url == null) {
                System.err.println("⚠ Không tìm thấy ảnh: " + path);
                return new ImageIcon(); // hoặc trả về icon mặc định
            }
            return new ImageIcon(url);
        }

        // Trường hợp là đường dẫn tuyệt đối (File system)
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("⚠ File ảnh không tồn tại: " + file.getAbsolutePath());
        }
        return new ImageIcon(path);
    }

    public static ImageIcon getIcon(String path, int width, int height) {
        Image image = getIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static void setIcon(JLabel label, String path) {
        label.setIcon(XIcon.getIcon(path, label.getWidth(), label.getHeight()));
    }

    public static void setIcon(JLabel label, File file) {
        XIcon.setIcon(label, file.getAbsolutePath());
    }

    public static File copyTo(File fromFile, String folder) {
        String fileExt = fromFile.getName().substring(fromFile.getName().lastIndexOf("."));
        File toFile = new File(folder, XStr.getKey() + fileExt);
        toFile.getParentFile().mkdirs();
        try {
            Files.copy(fromFile.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return toFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static File copyTo(File fromFile) {
        return copyTo(fromFile, "files");
    }
}
