package roman.pidkostelnyi.victoriaarmario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelnyi.victoriaarmario.dto.request.ColorRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.ColorResponse;
import roman.pidkostelnyi.victoriaarmario.service.ColorService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.COLOR_URL;

@CrossOrigin
@RestController
@RequestMapping(COLOR_URL)
public class ColorController {

    @Autowired
    private ColorService colorService;

    @PostMapping
    public void create(@Valid @RequestBody ColorRequest request) throws IOException {
        colorService.save(request);
    }

    @GetMapping
    public List<ColorResponse> findAll() {
        return colorService.findAll();
    }

    @GetMapping("/byProduct")
    public List<ColorResponse> findAllByProductId(Long productId) {
        return colorService.findAllByProductId(productId);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody ColorRequest request) throws IOException {
        colorService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        colorService.delete(id);
    }
}
