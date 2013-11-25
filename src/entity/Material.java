package entity;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table (name="material")
@NamedQueries({
	@NamedQuery(name = "findAllMaterial", query = "Select m From Material m "),
	
})
public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column ( name="name" )
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="material",fetch=FetchType.EAGER)
	private Set<Test> tests;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="material",fetch=FetchType.EAGER)
	private Set<MaterialAttribute> matAttrs;

	@ManyToOne
	@JoinColumn(name="id_parent",nullable=true)
	private Material materialParent;
	
	public Material() {
		super();

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
	
	
	public Set<Test> getTests() {
		return tests;
	}
	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}
	
	public void addTest(Test test){
		this.tests.add(test);
		if(test.getMaterial() != this){
			test.setMaterial(this);
		}
	}
	public Set<MaterialAttribute> getMatAttrs() {
		return matAttrs;
	}
	public void setMatAttrs(Set<MaterialAttribute> matAttrs) {
		this.matAttrs = matAttrs;
	}
	public Material getMaterialParent() {
		return materialParent;
	}
	public void setMaterialParent(Material materialParent) {
		this.materialParent = materialParent;
	}
	
/*	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	*/
	
}
