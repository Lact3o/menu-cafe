package com.cafe.menu_cafe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe.menu_cafe.dto.ResenaForm;
import com.cafe.menu_cafe.model.Resena;
import com.cafe.menu_cafe.repository.ProductoRepository;
import com.cafe.menu_cafe.repository.ResenaRepository;
import com.cafe.menu_cafe.repository.UsuarioRepository;

@Service
public class ResenaService {
	
	private final ResenaRepository resenaRepository;
	private final ProductoRepository productoRepository;
	private final UsuarioRepository usuarioRepository;
	
	public ResenaService(ResenaRepository resenaRepository,
						 ProductoRepository productoRepository,
						 UsuarioRepository usuarioRepository) {
		this.resenaRepository = resenaRepository;
		this.productoRepository = productoRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Resena> listar(Long productoId) {
		return resenaRepository.findByProductoIdOrderByFechaDesc(productoId);
	}
	
	public Double promedio(Long productoId) {
		return resenaRepository.promedio(productoId);
	}
	
	public void crear(Long productoId, String emailUsuario, ResenaForm form) {
		Resena r = new Resena();
		r.setProducto(productoRepository.findById(productoId).orElseThrow());
	    r.setUsuario(usuarioRepository.findByEmail(emailUsuario).orElseThrow());
	    r.setPuntuacion(form.getPuntuacion());
	    r.setComentario(form.getComentario());
	    resenaRepository.save(r);
	}

}
