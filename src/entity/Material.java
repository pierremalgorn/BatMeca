package entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Column;

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
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_user",nullable=true)
	private User user;*/
	/*@OneToMany
	public List<SubMaterial> subMat;
	*/
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
	
/*	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	*/
	
}
