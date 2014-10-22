package entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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


@Entity
@Table (name="test")
@NamedQueries({
	@NamedQuery(name = "findAllTest", query = "Select t From Test t"),
	@NamedQuery(name="findTest",query = "Select t From Test t Where t.id = :id" )
	

})
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="date")
	private Date date;
	@ManyToOne
	@JoinColumn(name="id_material",nullable=true)
	private Material material;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="test",fetch=FetchType.EAGER)
	private Set<TestAttribute> testAttributs;
	@ManyToOne
	@JoinColumn(name="id_creator",nullable=true)
	private User user;
	
	public Test(){
		
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Set<TestAttribute> getTestAttributs() {
		return testAttributs;
	}

	public void setTestAttributs(Set<TestAttribute> testAttributs) {
		this.testAttributs = testAttributs;
	}


	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
		if (!material.getTests().contains(this)) {
            material.getTests().add(this);
        }
		
	}

	public void addTestAttribute(TestAttribute testAttr){
		this.testAttributs.add(testAttr);
		if (testAttr.getTest() != null) {
			testAttr.setTest(this);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
