package merit.capstone.bankApp.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import merit.capstone.bankApp.models.Transaction;
/*
 * Repository interface to be used by spring as it autowires access to the database
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Transaction findById(long id);
    
}