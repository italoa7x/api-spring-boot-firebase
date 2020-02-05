package br.fbteste.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import br.fbteste.domain.Usuario;

@Service
public class FirebaseService {
	@Autowired
	private FirebaseInit fb;


	public String salvar(Usuario u) throws InterruptedException, ExecutionException {
		Firestore fb = FirestoreClient.getFirestore();
		DocumentReference docRef = fb.collection("users").document();
		Map<String, String> dadosUsuario = new HashMap<>();
		dadosUsuario.put("nome", u.getNome());
		dadosUsuario.put("email", u.getEmail());

		ApiFuture<WriteResult> collection = docRef.set(dadosUsuario);
		return collection.get().getUpdateTime().toString();

	}

	public List<Usuario> listar() throws InterruptedException, ExecutionException {
		Firestore fb = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = fb.collection("users").get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		List<Usuario> usuarios = documents.stream()
				.map(obj -> new Usuario(obj.getId(), obj.getString("nome"), obj.getString("email")))
				.collect(Collectors.toList());
		return usuarios;
	}
}
