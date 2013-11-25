package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="type_material_attribute")
public class TypeMaterialAttribute {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="name")
	private String name;
	
	/*
	private MaterialAttribute matAttr;
	*/
	public TypeMaterialAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*
	public MaterialAttribute getMatAttr() {
		return matAttr;
	}
	public void setMatAttr(MaterialAttribute matAttr) {
		this.matAttr = matAttr;
	}*/
	
}
