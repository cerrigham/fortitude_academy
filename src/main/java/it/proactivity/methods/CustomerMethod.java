package it.proactivity.methods;

import it.proactivity.model.Customer;
import it.proactivity.utility.SessionUtility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerMethod {
    public static Boolean insertOrUpdateCustomer(Session session, Long id, String name, String email, String phoneNumber,
                                                 String detail) {
        if (checkInputParameter(session, name, email, phoneNumber, detail)) {
            SessionUtility.endSession(session);
            return false;
        }

        if (id == null) {
            Customer customer = createCustomer(name, email, phoneNumber, detail);
            session.persist(customer);
            SessionUtility.endSession(session);

            return true;
        } else {

            List<Customer> customers = checkCustomer(session, id);
            if (customers.size() == 0) {
                SessionUtility.endSession(session);
                return false;
            } else {
                Customer customer = customers.get(0);
                checkParameterForUpdate(customer, name, email, phoneNumber, detail);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromCustomer(Session session, Long id) {
        if ((checkInputParameter(session, id))) {
            SessionUtility.endSession(session);
            return false;
        }

        List<Customer> customers = checkCustomer(session, id);

        if (customers.isEmpty()) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(customers.get(0));
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static Customer createCustomer(String name, String email, String phoneNumber, String detail) {

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setDetail(detail);
        return customer;
    }

    private static List<Customer> checkCustomer(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Customer> customerList = query.getResultList();
        if (customerList.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        }

        return customerList;
    }

    private static Boolean checkInputParameter(Session session, String name, String email, String phoneNumber, String detail) {

        return session == null || name == null || email == null || phoneNumber == null || detail == null;
    }

    private static Boolean checkInputParameter(Session session, Long id) {
        return session == null || id == null || id.equals(0L);
    }

    private static void checkParameterForUpdate(Customer customer, String name, String email, String phoneNumber,
                                                String detail) {

        if (!(name.isEmpty())) {
            customer.setName(name);
        }
        if (!(email.isEmpty())) {
            customer.setEmail(email);
        }
        if (!(phoneNumber.isEmpty())) {
            customer.setPhoneNumber(phoneNumber);
        }
        if (!(phoneNumber.isEmpty())) {
            customer.setDetail(detail);
        }
    }
}
