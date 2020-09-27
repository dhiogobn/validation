package br.edu.unifacisa.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifacisa.model.GameStore;
import br.edu.unifacisa.repository.GameStoreRepository;

@RestController
@RequestMapping("/v1/gameStore")
public class GameStoreController {
	private GameStoreRepository repository;
	
	public GameStoreController() {
		repository = new GameStoreRepository();
	}
	
	@PostMapping
	public ResponseEntity<String> cadastrar(@RequestBody GameStore jogo) {
		try {
			repository.cadastrar(jogo);
			return new ResponseEntity<String>("paciente cadastrado com sucesso", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<GameStore>> listar() {
		return ResponseEntity.ok(repository.listar());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirGame(@PathVariable int id) {
		
		try {
			repository.remover(id);
			return new ResponseEntity<String>("paciente removido", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GameStore> editar(@RequestBody GameStore jogo,@PathVariable int id)throws Exception{
		try {
			return new ResponseEntity<GameStore>(repository.editar(jogo, id), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<GameStore>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PutMapping("/vendas/{id}")
	public ResponseEntity<GameStore> realizarVenda(@RequestBody GameStore jogo,@PathVariable int id)throws Exception{
		try {
			return new ResponseEntity<GameStore>(repository.realizarVenda( id), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<GameStore>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
