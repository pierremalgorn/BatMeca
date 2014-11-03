package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "material_attribute")
public class MaterialAttribute {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "value")
	private String value;

	@ManyToOne
	@JoinColumn(name = "id_type_material_attribute", nullable = true)
	private TypeMaterialAttribute typeMatAttr;

	@ManyToOne
	@JoinColumn(name = "id_material", nullable = true)
	private Material material;

	public MaterialAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TypeMaterialAttribute getTypeMatAttr() {
		return typeMatAttr;
	}

	public void setTypeMatAttr(TypeMaterialAttribute typeMatAttr) {
		this.typeMatAttr = typeMatAttr;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
