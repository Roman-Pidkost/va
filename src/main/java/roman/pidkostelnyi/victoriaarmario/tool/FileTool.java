package roman.pidkostelnyi.victoriaarmario.tool;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.*;

@Service
public class FileTool {

    public String saveFile(String image, String directory) throws IOException {
        Path pathToDirectory = getPathToDirectory(directory);

        String[] data = image.split(Constants.COMMA);
        String metaInfo = data[0];
        String base64File = data[1];

        String fileName = createFileName(getFileExtensionFromMetaInfo(metaInfo));

        Files.write(
                Paths.get(pathToDirectory.toString(), fileName),
                Base64.getDecoder().decode(base64File.getBytes())
        );
        return fileName;
    }

    public boolean deleteFile(String directory, String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return true;
        }
        return Paths.get(System.getProperty(USER_HOME), directory, fileName).toFile().delete();
    }

    private String createFileName(String fileExtension) {
        String fileName = UUID.randomUUID().toString();
        return String.format(FULL_IMAGE_NAME_FORMAT, fileName, fileExtension);
    }

    private String getFileExtensionFromMetaInfo(String metaInfo) {
        return metaInfo.split(SLASH)[1].split(SEMICOLON)[0];
    }

    private Path getPathToDirectory(String directory) throws IOException {
        Path path = Paths.get(System.getProperty(USER_HOME), directory);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        return path;
    }
}
