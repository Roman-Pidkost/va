package roman.pidkostelnyi.victoriaarmario.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String description;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private String mainImage;

    @ManyToOne
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    private List<Color> colors = new ArrayList<>();
}
