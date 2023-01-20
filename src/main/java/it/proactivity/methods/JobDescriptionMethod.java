package it.proactivity.methods;


import it.proactivity.model.JobDescription;
import it.proactivity.model.Project;
import it.proactivity.model.Technology;
import it.proactivity.utility.SessionUtility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

public class JobDescriptionMethod {

    public static Boolean insertOrUpdateJobDescription(Session session, Long id, Long technology, Long project) {
        if (checkInputParameter(session, technology, project)) {
            SessionUtility.endSession(session);
            return false;
        }

        if (id == null) {
            JobDescription jobDescription = createJobDescription(session, technology, project);
            session.persist(jobDescription);
            SessionUtility.endSession(session);

            return true;
        } else {

            List<JobDescription> jobDescriptions = checkJobDescription(session, id);
            if (jobDescriptions.size() == 0) {
                SessionUtility.endSession(session);
                return false;
            } else {
                JobDescription jobDescription = jobDescriptions.get(0);
                checkParameterForUpdate(session, jobDescription, technology, project);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromJobDescription(Session session, Long id) {
        if ((checkInputParameter(session, id))) {
            SessionUtility.endSession(session);
            return false;
        }

        List<JobDescription> jobDescriptions = checkJobDescription(session, id);

        if (jobDescriptions.isEmpty()) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(jobDescriptions.get(0));
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static JobDescription createJobDescription(Session session, Long technology, Long project) {

        List<Technology> technologies = checkTechnology(session, technology);
        if (technologies.isEmpty()) {
            return null;
        }

        List<Project> projects = checkProject(session, project);
        if (projects.isEmpty()) {
            return null;
        }

        JobDescription jobDescription = new JobDescription();
        jobDescription.setTechnology(technologies.get(0));
        jobDescription.setProject(projects.get(0));

        return jobDescription;
    }

    private static List<JobDescription> checkJobDescription(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<JobDescription> cq = cb.createQuery(JobDescription.class);
        Root<JobDescription> root = cq.from(JobDescription.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<JobDescription> jobDescriptions = query.getResultList();
        if (jobDescriptions.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        }

        return jobDescriptions;
    }

    private static List<Technology> checkTechnology(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Technology> cq = cb.createQuery(Technology.class);
        Root<Technology> root = cq.from(Technology.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Technology> technologies = query.getResultList();
        if (technologies.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        }

        return technologies;
    }

    private static List<Project> checkProject(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Project> projects = query.getResultList();
        if (projects.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        }

        return projects;
    }

    private static Boolean checkInputParameter(Session session, Long technology, Long project) {

        return session == null || technology == null || project == null;
    }

    private static Boolean checkInputParameter(Session session, Long id) {
        return session == null || id == null || id.equals(0L);
    }

    private static void checkParameterForUpdate(Session session, JobDescription jobDescription, Long technology, Long project) {

        List<Technology> technologies = checkTechnology(session, technology);
        if (!(technologies.isEmpty())) {
            jobDescription.setTechnology(technologies.get(0));
        }

        List<Project> projects = checkProject(session, project);
        if (!(projects.isEmpty())) {
            jobDescription.setProject(projects.get(0));
        }
    }
}
