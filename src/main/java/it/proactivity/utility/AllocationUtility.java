package it.proactivity.utility;

import it.proactivity.model.Allocation;
import it.proactivity.model.HumanResource;
import it.proactivity.model.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class AllocationUtility {

    public static Boolean insertOrUpdateAllocation(Session session, Long id, HumanResource humanResource,
                                                   Project project) {
        if (session == null || humanResource == null || project == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            Allocation allocation = createAnAllocation(humanResource, project);
            session.persist(allocation);
            endSession(session);
            return true;
        } else {
            Allocation allocation = getAllocation(session, id);
            if (allocation == null) {
                endSession(session);
                return null;
            } else {
                checkForUpdate(allocation, humanResource, project);
                session.persist(session);
                endSession(session);
                return true;
            }
        }
    }
    public static Boolean deleteAnAllocation(Session session, Long id){
        if (session == null || id == null) {
            return false;
        }
        checkSession(session);

        final String query = "SELECT a " +
                "FROM Allocation a " +
                "WHERE a.id = :id";
        Query<Allocation> allocationQuery = session.createQuery(query).setParameter("id", id);
        List<Allocation> allocationList = allocationQuery.getResultList();
        if (allocationList.size() > 1 || allocationList == null) {
            endSession(session);
            return false;
        }
        session.delete(allocationList.get(0));
        endSession(session);
        return true;
    }
    private static Allocation createAnAllocation(HumanResource humanResouerceId, Project projectId) {
        Allocation Allocation = new Allocation();
        Allocation.setHumanResource(humanResouerceId);
        Allocation.setProject(projectId);
        return Allocation;
    }

    private static Allocation getAllocation(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Allocation> cr = cb.createQuery(Allocation.class);
        Root<Allocation> root = cr.from(Allocation.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<Allocation> query = session.createQuery(cr);
        List<Allocation> allocationsList = query.getResultList();

        if (allocationsList == null) {
            return null;
        } else if (allocationsList.isEmpty()) {
            return null;
        } else if (allocationsList.size() > 1) {
            throw new NoSuchElementException("There are more than one result");
        } else
            return allocationsList.get(0);
    }

    private static void checkForUpdate(Allocation allocation, HumanResource humanResource, Project project) {
        if (humanResource == null) {
            allocation.setHumanResource(humanResource);
        }
        if (project == null) {
            allocation.setProject(project);
        }
    }


}
