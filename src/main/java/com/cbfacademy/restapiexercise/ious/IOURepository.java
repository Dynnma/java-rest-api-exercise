package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

//import com.cbfacademy.restapiexercise.core.Repository;

/*The IOURepository interface defines the operations for managing IOUs in the system.
 * It provides methods for retrieving, saving, updating, and deleting IOU records */
public interface IOURepository extends ListCrudRepository<IOU, UUID> {

    List<IOU> searchByBorrower(String name); /* searches and returns a list of IOUs that match the borrower's name */

    List<IOU> searchByLender(String name); /* searches and returns a list of IOUs that match the lender's name */
}
