package entity;

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
import javax.persistence.Table;

@Entity
@Table (name="user")
@NamedQueries({
	@NamedQuery(name = "findAllUsers", query = "Select u From User u "),})

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column ( name="name" )
	private String name;
	@Column ( name="first_name" )
	private String firstName;
	@Column ( name="password" )
	private String password;
	@Column ( name="email" )
	private String email;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="type",nullable=true)
	private TypeUser type;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_user",nullable=true)
	private User user;*/
	/*@OneToMany
	public List<SubMaterial> subMat;
	*/
	public User() {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TypeUser getType() {
		return type;
	}

	public void setType(TypeUser type) {
		this.type = type;
	}


}
