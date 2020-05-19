package roman.pidkostelnyi.victoriaarmario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Value("${categories.img.directory}")
    private String categoryImagesDirectoryPath;

    @Value("${subcategories.img.directory}")
    private String subcategoryImagesDirectoryPath;

    @Value("${products.img.directory}")
    private String productsImagesDirectoryPath;

    @Value("${colors.img.directory}")
    private String colorsImagesDirectoryPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/images/category/**")
                .addResourceLocations(getPath(categoryImagesDirectoryPath));
        registry.addResourceHandler("/api/images/subcategory/**")
                .addResourceLocations(getPath(subcategoryImagesDirectoryPath));
        registry.addResourceHandler("/api/images/product/**")
                .addResourceLocations(getPath(productsImagesDirectoryPath));
        registry.addResourceHandler("/api/images/color/**")
                .addResourceLocations(getPath(colorsImagesDirectoryPath));
        registry.addResourceHandler("/api/**")
                .addResourceLocations("classpath:/META-INF/resources/","classpath:/resources/","classpath:/static/","classpath:/public/");
    }

    private String getPath(String directory) {
        return Paths.get(System.getProperty("user.home"), directory).toUri().toString();
    }
}

