package pos.java.maven.hibernate.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({//NAMEDQUERIES SÃO PARA ADICIONAR QUANDO SE REPETE OS MESMOS COMANDOS
	
	@NamedQuery(name = "UsuarioPessoa.todos", query = "Select u from UsuarioPessoa u"),
	@NamedQuery(name = "UsuarioPessoa.buscarPorNome", query = "select u from UsuarioPessoa u where u.nome = :nome")
})
public class UsuarioPessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	private String sobrenome;
	private String email;
	private String login;
	private String senha;
	private int idade;
	
	@OneToMany(mappedBy = "usuarioPessoa")
	private List<TelefoneUser> telefoneUser;
	
	public UsuarioPessoa() {
		
	}
	
	public void setTelefoneUser(List<TelefoneUser> telefoneUser) {
		this.telefoneUser = telefoneUser;
	}
	
	public List<TelefoneUser> getTelefoneUser() {
		return telefoneUser;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, idade, login, nome, senha, sobrenome, telefoneUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPessoa other = (UsuarioPessoa) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && idade == other.idade
				&& Objects.equals(login, other.login) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha) && Objects.equals(sobrenome, other.sobrenome)
				&& Objects.equals(telefoneUser, other.telefoneUser);
	}
	
	
	
}

