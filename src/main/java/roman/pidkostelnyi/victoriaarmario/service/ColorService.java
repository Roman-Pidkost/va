package roman.pidkostelnyi.victoriaarmario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import roman.pidkostelnyi.victoriaarmario.dto.request.ColorRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.ColorResponse;
import roman.pidkostelnyi.victoriaarmario.entity.Color;
import roman.pidkostelnyi.victoriaarmario.repository.ColorRepository;
import roman.pidkostelnyi.victoriaarmario.tool.FileTool;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private FileTool fileTool;

    @Value("${colors.img.directory}")
    private String imgDirectory;

    public void save(ColorRequest request) throws IOException {
        colorRepository.save(colorRequestToColor(null, request));
    }

    public List<ColorResponse> findAll() {
        return colorRepository.findAll().stream().map(ColorResponse::new).collect(Collectors.toList());
    }

    public List<ColorResponse> findAllByProductId(Long productId) {
        return colorRepository.findAllByProductId(productId).stream().map(ColorResponse::new).collect(Collectors.toList());
    }

    public void update(Long id, ColorRequest request) throws IOException {
        colorRepository.save(colorRequestToColor(findOne(id), request));
    }

    public void delete(Long id) {
        Color color = findOne(id);
        colorRepository.delete(color);
        fileTool.deleteFile(imgDirectory, color.getImage());
    }

    public Color findOne(Long id) {
        return colorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Color with id " + id + " not exists"));
    }

    private Color colorRequestToColor(Color color, ColorRequest request) throws IOException {
        if (color == null) {
            color = new Color();
        }
        color.setName(request.getName());
        if (request.getImage() != null) {
            color.setImage(fileTool.saveFile(request.getImage(), imgDirectory));
        }
        return color;
    }
}
