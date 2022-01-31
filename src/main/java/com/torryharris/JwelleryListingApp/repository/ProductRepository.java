package com.torryharris.JwelleryListingApp.repository;

import com.torryharris.JwelleryListingApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long>{
    List<Product> findAllByCategory_Id(int id);

    @Query(value = "select * from product where name like ?",nativeQuery = true)
    public List<Product> search(String keyword);

    @Transactional // stocks-1 48-->47
    @Modifying
    @Query(value = "update product  p set p.stock = p.stock-1 where p.id = ?",nativeQuery = true)
    int StockDecrement(int id);

    @Transactional  //stock+1
    @Modifying
    @Query(value = "update product  p set p.stock = p.stock+1 where p.id = ?",nativeQuery = true)
    int StockIncrement(int id);
}
