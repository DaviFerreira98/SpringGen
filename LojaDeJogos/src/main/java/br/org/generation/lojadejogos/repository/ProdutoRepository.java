package br.org.generation.lojadejogos.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.lojadejogos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List <Produto> findAllByNomeContainingIgnoreCase(String nome);
	public List <Produto> findAllByConsoleContainingIgnoreCase(String console);
	
	//Colocar a ordem dos valores crescente
	public List <Produto> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);
	//Decrescente
	public List <Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
}
