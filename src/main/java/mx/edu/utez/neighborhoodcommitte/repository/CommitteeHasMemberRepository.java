package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeHasMember;

public interface CommitteeHasMemberRepository extends JpaRepository<CommitteeHasMember, Long>{
    
}
