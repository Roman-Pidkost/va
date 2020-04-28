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
    @Column(columnDefinition = "text")
    private String description;
    private String catalogNumber;
    private String mainImage;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    @ManyToOne
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    private List<Color> colors = new ArrayList<>();
}
