package pos.java.maven.hibernate;

import java.util.List;

import org.junit.Test;

import pos.java.maven.hibernate.dao.DaoGeneric;
import pos.java.maven.hibernate.model.TelefoneUser;
import pos.java.maven.hibernate.model.UsuarioPessoa;




public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		HibernateUtil.getEntityManager();
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Jhonata");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Luis");
		pessoa.setEmail("jhonata@email.com.br");
		
		daoGeneric.salvar(pessoa);
		
	}
	
	
	//MÉTODO DE TESTE PARA BUSCAR UM DADO NO BANCO PELO ID 
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test //MÉTODO TEST PARA ATUALIZAR UM DADO NO BANCO DE DADOS
	public void testeUpdate() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(49);
		pessoa.setNome("Nome atualizado Hibernate");
		
		pessoa = daoGeneric.UpdateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	//MÉTODO TEST PARA DELETAR UM ITEM NO BANCO DE DADOS PELO ID
	@Test
	public void testDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		daoGeneric.deletarPoId(pessoa);
	}
	
	
	@Test
	public void testeConsultar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for(UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			System.out.println("============================");
		}
	}
	
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa").getResultList();
		
		for(UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	//LISTA  UM MAXIMO DE ITENS NA LISTA EX: 10
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa order by nome")
				.setMaxResults(10).getResultList();//TRAZ O MÁXIMO DE 10 ITENS NA LISTA
		
		for(UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = :nome")
				.setParameter("nome", "Egidio").getResultList();
		
		for(UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	//MÉTODO PARA RETORNAR UMA SOMA DE TODAS AS IDADES
	public void testeQuerySomaIdade() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager()
				.createQuery("select sum(u.idade) from UsuarioPessoa u")
				.getSingleResult();
		
		System.out.println("Soma de todas as idades do banco é : " + somaIdade);
	}
	
	@Test
	public void testeNameQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		for(UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	
	}
	
	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createNamedQuery("UsuarioPessoa.buscarPorNome")
				.setParameter("nome", "Paulo")
				.getResultList();
		
		for(UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	
	}
	
	@Test
	public void testeGravarTelefone() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa usuarioPessoa = (UsuarioPessoa) daoGeneric.pesquisar(24L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("Celular");
		telefoneUser.setNumero("41998756825");
		telefoneUser.setUsuarioPessoa(usuarioPessoa);
		
		daoGeneric.salvar(telefoneUser);
		
		
	}
	
	
	
	@Test
	public void testeConsultaTelefones() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(24L, UsuarioPessoa.class);
		
		for(TelefoneUser fone : pessoa.getTelefoneUser()) {
			
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("=========");
		}
	}
	
	
	
	
	
	
	
}
