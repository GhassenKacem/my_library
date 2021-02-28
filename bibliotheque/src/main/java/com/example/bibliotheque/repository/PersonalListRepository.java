package com.example.bibliotheque.repository;

 import com.example.bibliotheque.models.PersonalList;
  import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;


@Repository
public interface PersonalListRepository extends JpaRepository<PersonalList, Long> {

}
