package br.fbteste.resource;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.fbteste.domain.Usuario;
import br.fbteste.service.FirebaseService;

@RestController
public class UsuarioResouce {

	@Autowired
	private FirebaseService fb;

	@PostMapping("/novo")
	public ResponseEntity<String> salvar(@RequestBody Usuario u) {
		try {
			return ResponseEntity.ok().body(fb.salvar(u));
		} catch (InterruptedException | ExecutionException e) {
			return ResponseEntity.ok().body(HttpStatus.BAD_REQUEST.toString());
		}
	}

	@GetMapping("/listar")
	public Collection<Usuario> listar() {
		try {
			return fb.listar();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
