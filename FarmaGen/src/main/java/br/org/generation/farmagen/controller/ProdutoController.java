package br.org.generation.farmagen.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.farmagen.model.Produto;
import br.org.generation.farmagen.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoRepository.findByNomeContainingIgnoreCase(nome));
    }

    @GetMapping("/nome/{nome}/elabs/{lab}")
    public ResponseEntity<List<Produto>> getByNomeELab(@PathVariable String nome
            , @PathVariable String lab) {
        return ResponseEntity.ok(produtoRepository.findByNomeAndLaboratorio(nome, lab));
    }

    @GetMapping("/nome/{nome}/oulabs/{lab}")
    public ResponseEntity<List<Produto>> getByNomeOuLab(@PathVariable String nome
            , @PathVariable String lab) {
        return ResponseEntity.ok(produtoRepository.findByNomeOrLaboratorio(nome, lab));
    }

    @GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
    public ResponseEntity<List<Produto>> getByPrecoEntre(@PathVariable BigDecimal inicial, @PathVariable BigDecimal fim){
        return ResponseEntity.ok(produtoRepository.buscaProdutosEntre(inicial,fim));
    }

    @PostMapping
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
        return produtoRepository.findById(produto.getId())
                .map(resposta -> ResponseEntity.ok(produtoRepository.save(produto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable long id){
        return produtoRepository.findById(id)
                .map(resposta -> {
                    produtoRepository.deleteById(id);
                 return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}