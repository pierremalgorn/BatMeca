package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="test_attribute")
public class TestAttribute {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_test",nullable=true)
	private Test test;
	@Column(name="value")
	private String value;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_type_test_attribute",nullable=true)
	private TypeTestAttribute typeTestAttr;
	
	@ManyToOne
	@JoinColumn(name="id_material",nullable=true)
	private Material material;
	
	public TestAttribute(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	public String getName() {
		return value;
	}

	public void setName(String name) {
		this.value = name;
	}

	public TypeTestAttribute getTypeTestAttr() {
		return typeTestAttr;
	}

	public void setTypeTestAttr(TypeTestAttribute typeTestAttr) {
		this.typeTestAttr = typeTestAttr;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
