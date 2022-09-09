package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Usuario;


@Repository
public interface RegisterRepository extends JpaRepository<Usuario, Integer> {

}
