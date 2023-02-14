package cn.crowdos.kernel;

import cn.crowdos.kernel.algorithms.*;
import cn.crowdos.kernel.resource.Participant;
import cn.crowdos.kernel.resource.Task;
import cn.crowdos.kernel.system.SystemResourceCollection;
import cn.crowdos.kernel.system.SystemResourceHandler;
import cn.crowdos.kernel.system.resource.AlgoContainer;
import cn.crowdos.kernel.system.resource.Resource;
import cn.crowdos.kernel.system.resource.TaskPool;

import java.util.ArrayList;
import java.util.List;

public class Scheduler implements Resource<Scheduler> {
    // A private variable that is used to store the participant selection algorithm.
    private final ParticipantSelectionAlgo participantSelectionAlgo;
    // A private variable that is used to store the task recommendation algorithm.
    private final TaskRecommendationAlgo taskRecommendationAlgo;
    // A private variable that is used to store the task assignment algorithm.
    private final TaskAssignmentAlgo taskAssignmentAlgo;
    // A private variable that is used to store the algorithm factory.

    // Used to store the resource collection.
    private final SystemResourceCollection resourceCollection;

    // The constructor of the Scheduler class. It is used to initialize the scheduler.

    AbstractAlgoFactory psFactory;
    AbstractAlgoFactory trFactory;
    AbstractAlgoFactory taFactory;

    public Scheduler(SystemResourceCollection collection){
        this.resourceCollection = collection;
        SystemResourceHandler<AbstractAlgoFactory> resourceHandler = collection.getResourceHandler(AlgoContainer.class);
        this.psFactory = resourceHandler.getResource();
        this.trFactory = resourceHandler.getResource();
        this.taFactory = resourceHandler.getResource();

        participantSelectionAlgo = (ParticipantSelectionAlgo) psFactory.getAlgo();
        taskRecommendationAlgo = (TaskRecommendationAlgo) trFactory.getAlgo();
        taskAssignmentAlgo = (TaskAssignmentAlgo) taFactory.getAlgo();
    }

    /**
     *  For each task in the task pool, recommend a list of participants to complete the task
     *
     * @return A list of lists of participants.
     */
    public List<List<Participant>> recommendTasks(){
        TaskPool resourceView = resourceCollection.getResourceHandler(TaskPool.class).getResourceView();
        List<List<Participant>> recommendScheme = new ArrayList<>(resourceView.size());
        for (Task task : resourceView) {
            recommendScheme.add(taskRecommendation(task));
        }
        return recommendScheme;
    }

    /**
     *  For each task in the task pool, assign a list of participants to it
     *
     * @return A list of lists of participants.
     */
    public List<List<Participant>> assignTasks(){
        TaskPool resourceView = resourceCollection.getResourceHandler(TaskPool.class).getResourceView();
        List<List<Participant>> assignmentScheme = new ArrayList<>(resourceView.size());
        for (Task task : resourceView) {
            assignmentScheme.add(taskAssignment(task));
        }
        return assignmentScheme;
    }

    /**
     * The participant selection algorithm is used to get the candidates for a task.
     *
     * @param task The task for which the participants are to be selected.
     * @return A list of participants.
     */
    public List<Participant> participantSelection(Task task){
        return participantSelectionAlgo.getResults(task);
    }

    /**
     * This function returns a list of participants that are recommended for a given task.
     *
     * @param task The task for which you want to get the recommendations.
     * @return A list of participants.
     */
    public List<Participant> taskRecommendation(Task task){
        return taskRecommendationAlgo.getResults(task);
    }

    /**
     * This function returns a list of participants that are assigned to a task.
     *
     * @param task The task object that needs to be assigned to participants.
     * @return A list of participants.
     */
    public List<Participant> taskAssignment(Task task){
        return taskAssignmentAlgo.getResults(task);
    }

    /**
     * This function returns the AlgoFactory object that was created in the constructor.
     *
     * @return The algoFactory object.
     */

//    public AlgoFactory getAlgoFactory() {
//        return (AlgoFactory) algoFactory;
//    }
    public AbstractAlgoFactory getPsFactory(){
        return psFactory;
    }
    public AbstractAlgoFactory getTrFactory(){
        return trFactory;
    }
    public AbstractAlgoFactory getTaFactory(){
        return taFactory;
    }


    @Override
    // A method that returns a SystemResourceHandler object.
    public SystemResourceHandler<Scheduler> getHandler() {
        Scheduler scheduler = this;
        return new SystemResourceHandler<Scheduler>() {
            @Override
            public Scheduler getResourceView() {
                return scheduler;
            }

            @Override
            public Scheduler getResource() {
                return scheduler;
            }
        };
    }
}
