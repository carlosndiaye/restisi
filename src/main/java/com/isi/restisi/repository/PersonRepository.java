/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isi.restisi.repository;

import com.isi.restisi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlosndiaye
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}