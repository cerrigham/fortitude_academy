package it.proactivity.methods;


import it.proactivity.model.Customer;
import it.proactivity.model.JobDescription;
import it.proactivity.model.Project;
import it.proactivity.model.Technology;
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

public class JobDescriptionMethod {

    public static Boolean createOrUpdateJobDescription(Session session, Long id, Long technologyId, Long projectId)
            throws NoSuchElementException {
        if (session == null) {
            return false;
        }

        if (id == null) {
            //Create JobDescription
            if (technologyId == null || projectId == null) {
                SessionUtility.endSession(session);
                return false;
            }
            JobDescription jobDescription = createJobDescription(session, technologyId, projectId);
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

                setParametersForUpdate(session, jobDescription, technologyId, projectId);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromJobDescription(Session session, Long id) throws NoSuchElementException {
       if (session == null) {
           return false;
       }
       if (id == null) {
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

    public static JobDescription findJobDescriptionFromId(Session session, Long id) throws NoSuchElementException {
        if (session == null) {
            return null;
        }
        if (id == null) {
            SessionUtility.endSession(session);
            return null;
        }

        JobDescription jobDescription = (JobDescription) Utility.findObjectFromLong(session, id,"JobDescription");
        if (jobDescription == null) {
            SessionUtility.endSession(session);
            return null;
        }else {
            SessionUtility.endSession(session);
            return jobDescription;
        }
    }

    public static JobDescription findJobDescriptionFromIdWithCriteria(Session session, Long id) throws NoSuchElementException {
        if (session == null) {
            return null;
        }
        if (id == null || id.equals(0l)) {
            SessionUtility.endSession(session);
            return null;
        }

        Object o = Utility.findObjectFromLongWithCriteria(session,id, JobDescription.class);
        SessionUtility.endSession(session);

        if(o == null) {
            SessionUtility.endSession(session);
            return null;
        }

        JobDescription jobDescription = (JobDescription) o;
        return jobDescription;
    }

    public static List<JobDescription> findJobDescriptionFromTechnology(Session session, Long technologyId) {
        if (session == null) {
            return null;
        }
        if (technologyId == null || technologyId.equals(0l)) {
            SessionUtility.endSession(session);
            return null;
        }

        Technology technology = getTechnologyById(session, technologyId);
        if (technology == null) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objects = Utility.findObjectFromObject(session,"technology",technology,
                "JobDescription");
        if (objects == null || objects.isEmpty()) {
            return null;
        }
        List<JobDescription> jobDescriptions = new ArrayList<>();

        objects.stream()
                .forEach(e -> jobDescriptions.add((JobDescription) e));

        jobDescriptions.stream()
                .sorted(Comparator.comparing(JobDescription::getId));

        return jobDescriptions;

    }

    public static List<JobDescription> findJobdescriptionFromTechnologyWithCriteria(Session session, Long technologyId) {
        if (session == null) {
            return null;
        }
        if (technologyId == null || technologyId.equals(0l)) {
            SessionUtility.endSession(session);
            return null;
        }
        Technology technology = getTechnologyById(session, technologyId);
        if (technology == null) {
            SessionUtility.endSession(session);
            return null;
        }
        List<Object> objects = Utility.findObjectFromObjectWithCriteria(session, "technology", technology,
                JobDescription.class);

        if (objects == null || objects.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        SessionUtility.endSession(session);

        List<JobDescription> jobDescriptions = new ArrayList<>();

        objects.stream()
                .forEach(e -> jobDescriptions.add((JobDescription) e));

        jobDescriptions.stream()
                .sorted(Comparator.comparing(JobDescription::getId));

        return jobDescriptions;
    }
    public static List<JobDescription> findJobDescriptionFromProject(Session session, Long projectId) {
        if (session == null) {
            return null;
        }
        if (projectId == null || projectId.equals(0l)) {
            SessionUtility.endSession(session);
            return null;
        }

        Project project = getProjectById(session, projectId);
        if (project == null) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objects = Utility.findObjectFromObject(session,"project",project,
                "JobDescription");
        if (objects == null || objects.isEmpty()) {
            return null;
        }
        List<JobDescription> jobDescriptions = new ArrayList<>();

        objects.stream()
                .forEach(e -> jobDescriptions.add((JobDescription) e));

        jobDescriptions.stream()
                .sorted(Comparator.comparing(JobDescription::getId));

        return jobDescriptions;

    }

    public static List<JobDescription> findJobDescriptionsFromProjectWithCriteria(Session session, Long projectId) {
        if (session == null) {
            return null;
        }
        if (projectId == null || projectId.equals(0l)) {
            SessionUtility.endSession(session);
            return null;
        }
        Project project = getProjectById(session, projectId);
        if (project == null) {
            SessionUtility.endSession(session);
            return null;
        }
        List<Object> objects = Utility.findObjectFromObjectWithCriteria(session, "project", project,
                JobDescription.class);

        if (objects == null || objects.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        SessionUtility.endSession(session);

        List<JobDescription> jobDescriptions = new ArrayList<>();

        objects.stream()
                .forEach(e -> jobDescriptions.add((JobDescription) e));

        jobDescriptions.stream()
                .sorted(Comparator.comparing(JobDescription::getId));

        return jobDescriptions;
    }

    private static JobDescription createJobDescription(Session session, Long technologyId, Long projectID) {

        Technology technology = getTechnologyById(session, technologyId);
        if (technology == null) {
            SessionUtility.endSession(session);
            return null;
        }

        Project project = getProjectById(session, projectID);
        if (project == null) {
            SessionUtility.endSession(session);
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

    private static void setParametersForUpdate(Session session, JobDescription jobDescription, Long technologyId, Long projectId) {

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
