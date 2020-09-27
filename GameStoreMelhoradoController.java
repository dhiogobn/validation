package br.edu.unifacisa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifacisa.model.GameStore;
import br.edu.unifacisa.model.Response;
import br.edu.unifacisa.repository.GameStoreRepository;

@RestController
@RequestMapping("/v2/gameStore")
public class GameStoreMelhoradoController {
private GameStoreRepository repository;
	
	public GameStoreMelhoradoController() {
		repository = new GameStoreRepository();
	}
	
	@PostMapping
	public ResponseEntity<Response<GameStore>> cadastrar(@Valid @RequestBody GameStore jogo, BindingResult result) {
		Response<GameStore> response = new Response<GameStore>();
		try {
			if(result.hasErrors()) {
				for (ObjectError erro : result.getAllErrors()) {
					String key = String.valueOf(response.getErros().size()+1);
					response.getErros().put(key, erro.getDefaultMessage());
					
				}
				return ResponseEntity.ok(response);
			
			}
			response.setStatus(HttpStatus.OK.value());
			repository.cadastrar(jogo);
			response.setDados(jogo);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "falha ao cadastrar um novo jogo");
			return ResponseEntity.ok(response);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<Response<List<GameStore>>> listar() {
		Response<List<GameStore>> response = new Response<List<GameStore>>();
		try {
			response.setStatus(HttpStatus.OK.value());
			response.setDados(repository.listar());
			return ResponseEntity.ok(response);
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "falha na consulta");
			return ResponseEntity.ok(response);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<List<GameStore>>> excluirGame(@PathVariable int id) {
		Response<List<GameStore>> response = new Response<List<GameStore>>();
		try {
			response.setStatus(HttpStatus.OK.value());
			repository.remover(id);
			response.setDados(repository.listar());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "falha ao deletar jogo");
			return ResponseEntity.ok(response);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<GameStore>> editar(@Valid @RequestBody GameStore jogo,@PathVariable int id, BindingResult result)throws Exception{
		Response<GameStore> response = new Response<GameStore>();
		try {
			if(result.hasErrors()) {
				for (ObjectError erro : result.getAllErrors()) {
					String key = String.valueOf(response.getErros().size()+1);
					response.getErros().put(key, erro.getDefaultMessage());
				}
				return ResponseEntity.ok(response);
			}
			response.setStatus(HttpStatus.OK.value());
			repository.editar(jogo, id);
			response.setDados(jogo);
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "falha ao editar jogo");
			return ResponseEntity.ok(response);
		}
		
	}
	@PutMapping("/vendas/{id}")
	public ResponseEntity<Response<List<GameStore>>> realizarVenda(@PathVariable int id)throws Exception{
		Response<List<GameStore>> response = new Response<List<GameStore>>();
		try {
			
			response.setStatus(HttpStatus.OK.value());
			repository.realizarVenda(id);
			response.setDados(repository.listar());
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "falha ao realizar a venda do jogo");
			return ResponseEntity.ok(response);
		}
	}

}
