//: sfg6lab.repository.ContactManyToManyRepository.java

package sfg6lab.repository;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import sfg6lab.domain.model.Contact;


public interface ContactRepository extends
        ListCrudRepository<Contact, Long>,
        ListPagingAndSortingRepository<Contact, Long> {

}
