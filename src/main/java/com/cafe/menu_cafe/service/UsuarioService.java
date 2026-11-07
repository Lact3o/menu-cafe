package com.cafe.menu_cafe.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafe.menu_cafe.dto.RegistroForm;
import com.cafe.menu_cafe.model.Usuario;
import com.cafe.menu_cafe.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public boolean existe(String email) {
		return usuarioRepository.findByEmail(email).isPresent();
	}
	
	public void registrar(RegistroForm form) {
		Usuario u = new Usuario();
		u.setNombre(form.getNombre());
		u.setEmail(form.getEmail());
		u.setPassword(passwordEncoder.encode(form.getPassword()));
		usuarioRepository.save(u);
	}
	
	/** Spring Security llama a este método para buscar la cuenta al iniciar sesión. */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Usuario u = usuarioRepository.findByEmail(email)
	        .orElseThrow(() -> new UsernameNotFoundException("No existe la cuenta"));
	    return User.withUsername(u.getEmail()).password(u.getPassword()).roles("USER").build();
	}
}
