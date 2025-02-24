package technikal.task.fishmarket.persistence.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "fish")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fish {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@Column(name = "name")
	String name;
	@Column(name = "price")
	double price;
	@Column(name = "catch_date")
	Date catchDate;
	@ElementCollection
	@CollectionTable(name = "fish_images", joinColumns = @JoinColumn(name = "fish_id"))
	@Column(name = "image_file_name")
	List<String> images;

	@Column(name = "count")
	Integer count;
}
