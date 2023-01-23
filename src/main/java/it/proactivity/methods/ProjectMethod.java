package it.proactivity.methods;

import it.proactivity.model.Customer;
import it.proactivity.model.Project;
import it.proactivity.utility.ParsingUtility;
import it.proactivity.utility.SessionUtility;
import it.proactivity.utility.Utility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectMethod {

    public static Boolean createOrUpdateProject(Session session, Long id, String name, String endDate, String reportingId,
                                                Long customerId) throws NoSuchElementException {
        if (session == null) {
            return false;
        }


        if (id == null) {
            //Create project
            if (customerId == null || name == null || endDate == null || reportingId == null) {
                SessionUtility.endSession(session);
                return false;
            }

            LocalDate parsedDate = ParsingUtility.parseStringIntoDate(endDate);
            if (parsedDate == null) {
                SessionUtility.endSession(session);
                return false;
            }

            Project project = createProject(session, name, parsedDate, reportingId, customerId);
            if (project == null) {
                SessionUtility.endSession(session);
                return false;
            }
            session.persist(project);
            SessionUtility.endSession(session);
            return true;
        } else {
            Project project = getProjectById(session, id);
            if (project == null) {
                SessionUtility.endSession(session);
                return false;
            } else {
                setParametersForUpdate(session, project, name, endDate, reportingId, customerId);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromProject(Session session, Long id) throws NoSuchElementException {
        if(session == null) {
            return false;
        }
        if (id == null) {
            SessionUtility.endSession(session);
            return false;
        }

        Project project = getProjectById(session, id);

        if (project == null) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(project);
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static Project createProject(Session session, String name, LocalDate date, String reportingId, Long customerId) {


        Customer customer = getCustomerById(session, customerId);
        if (customer == null) {
            SessionUtility.endSession(session);
            return null;
        }
        Project project = new Project();
        project.setName(name);
        project.setEndDate(date);
        project.setReportingId(reportingId);
        project.setCustomer(customer);
        return project;
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
    private static Project getProjectById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Project> projects  = query.getResultList();
        if(projects == null) {
            return null;
        } else if(projects.isEmpty()) {
            return null;
        } else if(projects.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        } else
            return  projects.get(0);
    }

    private static void setParametersForUpdate(Session session, Project project, String name, String date, String reportingId,
                                                Long customerId) {

        Utility utility = new Utility();
        if (!(utility.isNullOrEmpty(name))) {
            project.setName(name);
        }
        if (!(utility.isNullOrEmpty(date))) {
            LocalDate parsedDate = ParsingUtility.parseStringIntoDate(date);

            project.setEndDate(parsedDate);
        }
        if (!(utility.isNullOrEmpty(reportingId))) {
            project.setReportingId(reportingId);
        }

        Customer customer = getCustomerById(session, customerId);
        if (customer != null) {
            project.setCustomer(customer);
        }
    }
}
