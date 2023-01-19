package it.proactivity.methods;

import it.proactivity.model.Customer;
import it.proactivity.model.Project;
import it.proactivity.utility.ParsingUtility;
import it.proactivity.utility.SessionUtility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectMethod {

    public static Boolean insertOrUpdateProject(Session session, Long id, String name, String endDate, String reportingId,
                                                 Long customer) {
        if (checkInputParameter(session, name, endDate, reportingId, customer)) {
            SessionUtility.endSession(session);
            return false;
        }


        if (id == null) {
            LocalDate parsedDate = ParsingUtility.parseStringIntoDate(endDate);
            if (parsedDate == null) {
                SessionUtility.endSession(session);
                return false;
            }

            Project project = createProject(session, name, parsedDate, reportingId, customer);
            session.persist(project);
            SessionUtility.endSession(session);
            return true;
        } else {

            List<Project> projects = checkProject(session, id);
            if (projects.size() == 0) {
                SessionUtility.endSession(session);
                return false;
            } else {
                Project project = projects.get(0);
                checkParameterForUpdate(session, project, name, endDate, reportingId, customer);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromProject(Session session, Long id) {
        if ((checkInputParameter(session, id))) {
            SessionUtility.endSession(session);
            return false;
        }

        List<Project> projects = checkProject(session, id);

        if (projects.isEmpty()) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(projects.get(0));
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static Project createProject(Session session, String name, LocalDate date, String reportingId, Long customer) {

        List<Customer> customers = checkCustomer(session, customer);
        if (customers.isEmpty()) {
            return null;
        }
        Project project = new Project();
        project.setName(name);
        project.setEndDate(date);
        project.setReportingId(reportingId);
        project.setCustomer(customers.get(0));
        return project;
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
    private static List<Project> checkProject(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Project> projects  = query.getResultList();
        if (projects.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        }

        return projects;
    }

    private static Boolean checkInputParameter(Session session, String name, String endDate, String reportingId,
                                               Long customer) {

        return session == null || name == null || endDate == null || reportingId == null || customer == null;
    }

    private static Boolean checkInputParameter(Session session, Long id) {
        return session == null || id == null || id.equals(0L);
    }


    private static void checkParameterForUpdate(Session session, Project project, String name, String date, String reportingId,
                                                Long customer) {

        if (!(name.isEmpty())) {
            project.setName(name);
        }
        if (!(date.isEmpty())) {
            LocalDate parsedDate = ParsingUtility.parseStringIntoDate(date);
            project.setEndDate(parsedDate);
        }
        if (!(reportingId.isEmpty())) {
            project.setReportingId(reportingId);
        }

        List<Customer> customers = checkCustomer(session, customer);
        if (!(customers.isEmpty())) {
            project.setCustomer(customers.get(0));
        }
    }
}
