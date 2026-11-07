package com.cafe.menu_cafe.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cafe.menu_cafe.model.Resena;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
	
	List<Resena> findByProductoIdOrderByFechaDesc(Long productoId);
	
	@Query("select avg(r.puntuacion) from Resena r where r.producto.id = :productoId")
	Double promedio(@Param("productoId") Long productoId);
}
