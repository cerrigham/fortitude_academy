package it.proactivity.methods;

import it.proactivity.model.Customer;
import it.proactivity.utility.SessionUtility;
import it.proactivity.utility.Utility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Comparator;
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

    public static Customer findCustomerFromId(Session session, Long id) throws NoSuchElementException{
        if (session == null) {
            return null;
        }
        if (id == null) {
            SessionUtility.endSession(session);
            return null;
        }


        Customer customer = (Customer) Utility.findObjectFromLong(session, id,"Customer");
        if (customer == null) {
            SessionUtility.endSession(session);
            return null;
        }else {
            SessionUtility.endSession(session);
            return customer;
        }
    }

    public static Customer findCustomerFromIdWithCriteria(Session session, Long id) {
        if (session == null) {
            return null;
        }
        if (id == null || id.equals(0l)) {
            SessionUtility.endSession(session);
            return null;
        }

        Object o = Utility.findObjectFromLongWithCriteria(session,id, Customer.class);
        SessionUtility.endSession(session);

        if(o == null) {
            SessionUtility.endSession(session);
            return null;
        }

        Customer customer = (Customer) o;
        return customer;
    }

    public static List<Customer> findCustomersFromName(Session session, String attributeValue) {
        if (session == null) {
            return null;
        }
        if (attributeValue == null || attributeValue.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objectList =  Utility.findObjectFromString(session, "name", attributeValue, "Customer");

        if (objectList == null || objectList.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Customer> customers = new ArrayList<>();

        objectList.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);
        return customers;
    }

    public static  List<Customer> findCustomerFromNameWithCriteria(Session session, String name) {
        if (session == null) {
            return null;
        }
        if (name == null || name.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objects = Utility.findObjectsFromStringWithCriteria(session, "name", name, Customer.class);
        if (objects == null) {
            SessionUtility.endSession(session);
            return  null;
        }
        List<Customer> customers = new ArrayList<>();

        objects.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);

        return customers;
    }

    public static List<Customer> findCustomerFromEmail(Session session, String attributeValue) {
        if (session == null) {
            return null;
        }
        if (attributeValue == null || attributeValue.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objectList =  Utility.findObjectFromString(session, "email", attributeValue,
                "Customer");

        if (objectList == null || objectList.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Customer> customers = new ArrayList<>();

        objectList.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);
        return customers;
    }

    public static List<Customer> findCustomerFromEmailWithCriteria(Session session, String email) {
        if (session == null) {
            return null;
        }
        if (email == null || email.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objects = Utility.findObjectsFromStringWithCriteria(session, "email", email, Customer.class);
        if (objects == null) {
            SessionUtility.endSession(session);
            return  null;
        }
        List<Customer> customers = new ArrayList<>();

        objects.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);

        return customers;
    }

    public static List<Customer> findCustomersFromPhoneNumber(Session session, String attributeValue) {
        if (session == null) {
            return null;
        }
        if (attributeValue == null || attributeValue.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objectList =  Utility.findObjectFromString(session, "phoneNumber", attributeValue,
                "Customer");

        if (objectList == null || objectList.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Customer> customers = new ArrayList<>();

        objectList.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);
        return customers;
    }

    public static List<Customer> findCustomersFromPhoneNumberWithCriteria(Session session, String phoneNumber) {
        if (session == null) {
            return null;
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objects = Utility.findObjectsFromStringWithCriteria(session, "phoneNumber",
                phoneNumber, Customer.class);

        if (objects == null) {
            SessionUtility.endSession(session);
            return  null;
        }
        List<Customer> customers = new ArrayList<>();

        objects.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);

        return customers;
    }

    public static List<Customer> findCustomersFromDetails(Session session, String attributeValue) {
        if (session == null) {
            return null;
        }

        List<Object> objectList =  Utility.findObjectFromString(session, "detail", attributeValue,
                "Customer");

        if (objectList == null || objectList.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Customer> customers = new ArrayList<>();

        objectList.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);
        return customers;
    }

    public static List<Customer> findCustomersFromDetailWithCriteria(Session session, String detail) {
        if (session == null) {
            return null;
        }


        List<Object> objects = Utility.findObjectsFromStringWithCriteria(session, "detail",
                detail, Customer.class);

        if (objects == null) {
            SessionUtility.endSession(session);
            return  null;
        }
        List<Customer> customers = new ArrayList<>();

        objects.stream()
                .forEach(e -> customers.add((Customer) e));

        customers.stream()
                .sorted(Comparator.comparing(Customer::getId));

        SessionUtility.endSession(session);

        return customers;
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
