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

    public static Boolean insertOrUpdateJobDescription(Session session, Long id, Long technology, Long project)
            throws NoSuchElementException {
        if (session == null) {
            return false;
        }

        if (id == null) {
            //Create JobDescription
            JobDescription jobDescription = createJobDescription(session, technology, project);
            session.persist(jobDescription);
            SessionUtility.endSession(session);

            return true;
        } else {
            //Update JobDescription
            JobDescription jobDescription = getJobDescriptionById(session, id);
            if (jobDescription == null) {
                SessionUtility.endSession(session);
                return false;
            } else {

                checkParameterForUpdate(session, jobDescription, technology, project);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromJobDescription(Session session, Long id) throws NoSuchElementException {
        if ((checkInputParameter(session, id))) {
            SessionUtility.endSession(session);
            return false;
        }

        JobDescription jobDescription = getJobDescriptionById(session, id);

        if (jobDescription == null) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(jobDescription);
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static JobDescription createJobDescription(Session session, Long technologyId, Long projectID) {

        Technology technology = getTechnologyById(session, technologyId);
        if (technology == null) {
            return null;
        }

        Project project = getProjectById(session, projectID);
        if (project == null) {
            return null;
        }

        JobDescription jobDescription = new JobDescription();
        jobDescription.setTechnology(technology);
        jobDescription.setProject(project);

        return jobDescription;
    }

    private static JobDescription getJobDescriptionById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<JobDescription> cq = cb.createQuery(JobDescription.class);
        Root<JobDescription> root = cq.from(JobDescription.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<JobDescription> jobDescriptions = query.getResultList();
        if(jobDescriptions == null) {
            return null;
        } else if(jobDescriptions.isEmpty()) {
            return null;
        } else if(jobDescriptions.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        } else
            return  jobDescriptions.get(0);
    }

    private static Technology getTechnologyById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Technology> cq = cb.createQuery(Technology.class);
        Root<Technology> root = cq.from(Technology.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Technology> technologies = query.getResultList();
        if(technologies == null) {
            return null;
        } else if(technologies.isEmpty()) {
            return null;
        } else if(technologies.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        } else
            return  technologies.get(0);
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
    private static Boolean checkInputParameter(Session session, Long id) {
        return session == null || id == null || id.equals(0L);
    }

    private static void checkParameterForUpdate(Session session, JobDescription jobDescription, Long technologyId, Long projectId) {

        Technology technology = getTechnologyById(session, technologyId);
        if (technology != null) {
            jobDescription.setTechnology(technology);
        }

        Project project = getProjectById(session, projectId);
        if (project != null) {
            jobDescription.setProject(project);
        }
    }
}
