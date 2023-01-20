package it.proactivity.methods;

import it.proactivity.model.Customer;
import it.proactivity.utility.SessionUtility;
import it.proactivity.utility.Utility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerMethod {

    public static Boolean createOrUpdateCustomer(Session session, Long id, String name, String email, String phoneNumber,
                                                 String detail) throws NoSuchElementException {
        if(session == null)
            return false;

        if (id == null) {
            // create Customer
            Utility utility = new Utility();

            if(utility.isNullOrEmpty(name) || utility.isNullOrEmpty(email) || utility.isNullOrEmpty(phoneNumber)) {
                SessionUtility.endSession(session);
                return false;
            }

            Customer customer = createCustomer(name, email, phoneNumber, detail);
            session.persist(customer);
            SessionUtility.endSession(session);

            return true;
        } else {
            // update Customer
            Customer customer = getCustomerById(session, id);
            if (customer == null) {
                SessionUtility.endSession(session);
                return false;
            } else {
                checkParameterForUpdate(customer, name, email, phoneNumber, detail);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromCustomer(Session session, Long id) throws NoSuchElementException {
        if(session == null) {
            return false;
        }
        if (id == null) {
            SessionUtility.endSession(session);
            return false;
        }
        Customer customer = getCustomerById(session, id);

        if (customer == null) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(customer);
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static Customer createCustomer(String name, String email, String phoneNumber, String detail) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);

        Utility utility = new Utility();
        if(!utility.isNullOrEmpty(detail)) {
            customer.setDetail(detail);
        }

        return customer;
    }

    private static Customer getCustomerById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Customer> customerList = query.getResultList();

        if(customerList == null) {
            return null;
        } else if(customerList.isEmpty()) {
            return null;
        } else if(customerList.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        } else
            return  customerList.get(0);
    }

    private static void checkParameterForUpdate(Customer customer, String name, String email, String phoneNumber,
                                                String detail) {
        Utility utility = new Utility();
        if (!utility.isNullOrEmpty(name)) {
            customer.setName(name);
        }
        if (!utility.isNullOrEmpty(email)) {
            customer.setEmail(email);
        }
        if (!utility.isNullOrEmpty(phoneNumber)) {
            customer.setPhoneNumber(phoneNumber);
        }
        if (detail != null) {
            customer.setDetail(detail);
        }
    }
}
