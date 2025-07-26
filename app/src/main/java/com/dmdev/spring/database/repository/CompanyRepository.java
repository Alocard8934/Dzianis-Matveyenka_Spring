
package com.dmdev.spring.database.repository;

import com.dmdev.spring.bpp.Auditing;
import com.dmdev.spring.bpp.InjectBean;
import com.dmdev.spring.bpp.Transaction;
import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import java.util.Optional;


@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company>{
    
@InjectBean    
private ConnectionPool connectionPool;

@PostConstruct
private void init(){
    System.out.println("init company repository");
}
    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("Find by Id" + id);
         return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
         System.out.println("delete method...");
    }


    
   
}
